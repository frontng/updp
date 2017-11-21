package com.upanda.updp.server.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upanda.updp.common.util.MD5Utils;
import com.upanda.updp.server.system.api.SystemAccountService;
import com.upanda.updp.server.system.api.entity.SystemAccount;
import com.upanda.updp.server.system.api.vo.LoginVO;
import com.upanda.updp.server.system.mapper.SystemAccountMapper;
import com.upanda.updp.server.system.repository.SystemAccountRepository;

/**
 * 系统服务-后台账号业务逻辑
 * 
 * @date 2017年9月5日
 * @author Front Ng
 *
 */
@Service
@RequestMapping
public class SystemAccountServiceImpl implements SystemAccountService{

	@Autowired
	private SystemAccountMapper systemAccountMapper;
	
	@Autowired
	private SystemAccountRepository systemAccountRepository;
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Boolean login(@RequestBody LoginVO vo) {
		SystemAccount account = systemAccountMapper.findByLoginAccount(vo.getLoginAccount().toLowerCase());
		if (account == null) {
			return false;
		}
		if (account.getPassword().equals(vo.getPassword())) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/findByLoginAccount")
	@ResponseBody
	public SystemAccount findByLoginAccount(String loginAccount) {
		return systemAccountMapper.findByLoginAccount(loginAccount.toLowerCase());
	}

	@RequestMapping(value = "/createAccount")
	@ResponseBody
	public SystemAccount createAccount(String loginAccount, String md5Password, String name) {
		SystemAccount systemAccount=new SystemAccount();
		systemAccount.setIsDisable(false);
		systemAccount.setIsLock(false);
		systemAccount.setLoginAccount(loginAccount);
		String salt=MD5Utils.createRandomSaltValue();
		systemAccount.setPassword(MD5Utils.md5AndSalt(md5Password, salt));
		systemAccount.setSalt(salt);
		systemAccount.setName(name);
		systemAccount=systemAccountRepository.save(systemAccount);
		return systemAccount;
	}
	
}
