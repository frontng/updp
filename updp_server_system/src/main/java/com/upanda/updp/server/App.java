package com.upanda.updp.server;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.upanda.updp.common.util.MD5Utils;
import com.upanda.updp.server.system.api.SystemAccountService;
import com.upanda.updp.server.system.api.entity.SystemAccount;

@SpringBootApplication
@EnableDiscoveryClient
//@ImportResource({ "classpath:*.xml" })
public class App implements CommandLineRunner{
	
	private static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		logger.info("updp系统服务-启动成功");
	}

	@Autowired
	private SystemAccountService systemAccountService;
	
	@Override
	public void run(String... args) throws Exception {
		String loginAccount="admin";
		SystemAccount account=systemAccountService.findByLoginAccount(loginAccount);
		if(account!=null) {
			return;
		}
		systemAccountService.createAccount(loginAccount, MD5Utils.md5("admin").toUpperCase(), "超级管理员");
	}

}
