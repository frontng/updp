package com.upanda.updp.server.system.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.upanda.updp.server.system.api.entity.SystemAccount;
import com.upanda.updp.server.system.api.vo.LoginVO;

/**
 * 系统服务-后台账号相关接口
 * @date 2017年9月5日
 * @author Front Ng
 *
 */
public interface SystemAccountService {
	
	/**
	 * 登录
	 * @param loginAccount
	 * @param password
	 * @return 
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Boolean login(@RequestBody LoginVO vo);
	
	/**
	 * 使用登录账号查找账号
	 * @param loginAccount
	 * @return
	 */
	@RequestMapping(value = "/findByLoginAccount")
	@ResponseBody
	public SystemAccount findByLoginAccount(@RequestParam("loginAccount") String loginAccount);
	
	/**
	 * 创建账号
	 * @param loginAccount 登录账号
	 * @param md5Password MD5加密过的密码
	 * @param name 姓名
	 * @return
	 */
	@RequestMapping(value = "/createAccount")
	@ResponseBody
	public SystemAccount createAccount(@RequestParam("loginAccount") String loginAccount,@RequestParam("md5Password") String md5Password,@RequestParam("name")String name);
	
}
