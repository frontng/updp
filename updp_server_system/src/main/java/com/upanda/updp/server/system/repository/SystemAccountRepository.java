package com.upanda.updp.server.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.upanda.updp.server.system.api.entity.SystemAccount;

/**
 * 
 * @author Front Ng
 *
 */
public interface SystemAccountRepository extends JpaRepository<SystemAccount, Long>,JpaSpecificationExecutor<SystemAccount>{
	
}
