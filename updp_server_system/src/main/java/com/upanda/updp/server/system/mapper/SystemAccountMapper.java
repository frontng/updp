package com.upanda.updp.server.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.upanda.updp.server.system.api.entity.SystemAccount;

/**
 * 后台用户账号Mapper
 * @date 2017年9月6日
 * @author Front Ng
 *
 */
@Mapper
public interface SystemAccountMapper {
	
	/**
	 * 根据账号查询后台用户信息
	 * @param loginAccount
	 * @return
	 */
	@Select("select * from system_account where login_account = #{loginAccount}")
	SystemAccount findByLoginAccount(@Param("loginAccount") String loginAccount);
	
}
