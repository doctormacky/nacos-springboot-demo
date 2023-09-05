package com.panda.nacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.pool2.factory.PoolConfig;
import org.springframework.ldap.pool2.factory.PooledContextSource;
import org.springframework.ldap.pool2.validation.DefaultDirContextValidator;
import org.springframework.ldap.transaction.compensating.manager.TransactionAwareContextSourceProxy;

/**  
 * @Title: DynamicActiveDirectoryConfiguration.java
 *
 * @Description: configuration for ad server.
 *
 * @author Macky liuyunsh@cn.ibm.com
 *
 * @Copyright: 2022-2099 IBM All rights reserved.
 *
 * @date 2023-09-05 11:08:45 
 */
@Configuration(proxyBeanMethods = false)
public class DynamicActiveDirectoryConfiguration {
	
	
	private final ActiveDirectoryProperties properties;
	
	 public DynamicActiveDirectoryConfiguration(ActiveDirectoryProperties properties) {
	        this.properties = properties;
	 }
	 
	 @Bean
	 ActiveDirectoryDataSource dataSource() {
		 LdapContextSource dataSource = properties.initLdapContextSource();
		 return new ActiveDirectoryDataSource(dataSource);
	}
	 
		@Bean
		public PooledContextSource poolContextSource(LdapContextSource contextSource,
				DefaultDirContextValidator contextValidator) {
			PoolConfig pc = new PoolConfig();
			pc.setTestOnBorrow(true);
			pc.setTestWhileIdle(true);
			PooledContextSource cs = new PooledContextSource(pc);
			cs.setContextSource(contextSource);
			cs.setDirContextValidator(contextValidator);
			return cs;
		}

		@Bean
		public DefaultDirContextValidator contextValidator() {
			return new DefaultDirContextValidator();
		}
		
		@Bean
		public LdapTemplate ldapTemplate(PooledContextSource contextSource) {
			TransactionAwareContextSourceProxy contextSourceProxy = new TransactionAwareContextSourceProxy(contextSource);
			LdapTemplate lt = new LdapTemplate(contextSourceProxy);
			lt.setIgnorePartialResultException(true);
			return lt;
		}

}
