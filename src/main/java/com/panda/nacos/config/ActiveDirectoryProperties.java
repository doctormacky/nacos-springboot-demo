package com.panda.nacos.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.core.support.DefaultTlsDirContextAuthenticationStrategy;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;

/**  
 * @Title: ActiveDirectoryProperties.java
 *
 * @Description: Active Directory Properties
 *
 * @author Macky liuyunsh@cn.ibm.com
 *
 * @Copyright: 2022-2099 IBM All rights reserved.
 *
 * @date 2023-09-05 11:10:38 
 */
@Primary
@Component
@NacosConfigurationProperties(dataId = "adconfig", groupId = "DEFAULT_GROUP", autoRefreshed = true)
public class ActiveDirectoryProperties implements java.io.Serializable{

	private static final long serialVersionUID = -601324700717824066L;
	
	public LdapContextSource initLdapContextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		String []array = StringUtils.split(url, ":");
		String host = StringUtils.substringAfterLast(array[1], "/"); 
		contextSource.setUrl(url);
		contextSource.setBase(getBase());
		contextSource.setUserDn(getUsername());
		contextSource.setPassword(getPassword());
		contextSource.setReferral("follow");
		contextSource.setCacheEnvironmentProperties(true);
		contextSource.setAnonymousReadOnly(false);
		// https://github.com/spring-projects/spring-ldap/issues/351#issuecomment-586551591
		// contextSource.setPooled(true); I do not see that anywhere that we have this
		// attribute setting when using pool.

		// Enable LDAP over TLS, see
		// https://docs.spring.io/spring-ldap/docs/2.3.0.BUILD-SNAPSHOT/reference/html/configuration.html#tls
		DefaultTlsDirContextAuthenticationStrategy strategy = new DefaultTlsDirContextAuthenticationStrategy();
		strategy.setHostnameVerifier(new SkipHostnameVerifier(host, false));

		contextSource.setAuthenticationStrategy(strategy);
		//contextSource.afterPropertiesSet();

		return contextSource;
	}
	
	class SkipHostnameVerifier implements HostnameVerifier {

		private String hostName;

		private boolean skipHostNameVerification;

		SkipHostnameVerifier(String hostname, boolean skipHostNameVerification) {
			this.hostName = hostname;
			this.skipHostNameVerification = skipHostNameVerification;
		}

		@Override
		public boolean verify(String hostname, SSLSession sslSession) {
			return skipHostNameVerification || hostname.equals(this.hostName);
		}
	}
	
	
	private String url; // ldap://<ip:port>
	
	private String base; // DC=testsso,DC=test,DC=isops,DC=ibm,DC=com
	
	private String username;
	
	private String password;
	
	private String domain; // testsso.test.isops.ibm.com
	
//	@JsonProperty(value = "user_ou")
//	@JSONField(name="user_ou")
	private String userou; // OU=UserAccts

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUserou() {
		return userou;
	}

	public void setUserou(String userou) {
		this.userou = userou;
	}
	
	
}
