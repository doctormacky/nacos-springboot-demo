package com.panda.nacos.config;

import java.util.concurrent.atomic.AtomicReference;

import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapName;

import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;

/**  
 * @Title: ActiveDirectoryDataSource.java
 *
 * @Description: Active Directory data source
 *
 * @author Macky liuyunsh@cn.ibm.com
 *
 * @Copyright: 2022-2099 IBM All rights reserved.
 *
 * @date 2023-09-05 10:50:15 
 */
@SuppressWarnings("deprecation")
public class ActiveDirectoryDataSource implements BaseLdapPathContextSource{
	
	
	private final AtomicReference<LdapContextSource> ldapContextReference;
	
	
	public ActiveDirectoryDataSource(LdapContextSource ldapContextSource) {
		this.ldapContextReference = new AtomicReference<>(ldapContextSource);
	}
	
	public LdapContextSource getAndSet(LdapContextSource ldapContextSource) {
		return ldapContextReference.getAndSet(ldapContextSource);
	}
	
	public LdapContextSource get() {
		return ldapContextReference.get();
	}

	/**  
	 * @MethodName: getReadOnlyContext
	 * @Description: read only context
	 * @author liuyunsh@cn.ibm.com
	 * @return
	 * @throws NamingException
	 * @see org.springframework.ldap.core.ContextSource#getReadOnlyContext()
	 * @date 2023-09-05 03:16:06 
	 */ 
	@Override
	public DirContext getReadOnlyContext() throws NamingException {
		return ldapContextReference.get().getReadOnlyContext();
	}

	/**  
	 * @MethodName: getReadWriteContext
	 * @Description: read write context
	 * @author liuyunsh@cn.ibm.com
	 * @return
	 * @throws NamingException
	 * @see org.springframework.ldap.core.ContextSource#getReadWriteContext()
	 * @date 2023-09-05 03:16:06 
	 */ 
	@Override
	public DirContext getReadWriteContext() throws NamingException {
		return ldapContextReference.get().getReadWriteContext();
	}

	/**  
	 * @MethodName: getContext
	 * @Description: context
	 * @author liuyunsh@cn.ibm.com
	 * @param principal
	 * @param credentials
	 * @return
	 * @throws NamingException
	 * @see org.springframework.ldap.core.ContextSource#getContext(java.lang.String, java.lang.String)
	 * @date 2023-09-05 03:16:06 
	 */ 
	@Override
	public DirContext getContext(String principal, String credentials) throws NamingException {
		return ldapContextReference.get().getContext(principal, credentials);
	}

	/**  
	 * @MethodName: getBaseLdapPath
	 * @Description: base ldap path
	 * @author liuyunsh@cn.ibm.com
	 * @return
	 * @deprecated
	 * @see org.springframework.ldap.core.support.BaseLdapPathSource#getBaseLdapPath()
	 * @date 2023-09-05 03:16:06 
	 */ 
	@Override
	public DistinguishedName getBaseLdapPath() {
		return ldapContextReference.get().getBaseLdapPath();
	}

	/**  
	 * @MethodName: getBaseLdapName
	 * @Description: base ldap name
	 * @author liuyunsh@cn.ibm.com
	 * @return
	 * @see org.springframework.ldap.core.support.BaseLdapPathSource#getBaseLdapName()
	 * @date 2023-09-05 03:16:06 
	 */ 
	@Override
	public LdapName getBaseLdapName() {
		return ldapContextReference.get().getBaseLdapName();
	}

	/**  
	 * @MethodName: getBaseLdapPathAsString
	 * @Description: base ldap path
	 * @author liuyunsh@cn.ibm.com
	 * @return
	 * @see org.springframework.ldap.core.support.BaseLdapPathSource#getBaseLdapPathAsString()
	 * @date 2023-09-05 03:16:06 
	 */ 
	@Override
	public String getBaseLdapPathAsString() {
		return ldapContextReference.get().getBaseLdapPathAsString();
	}
	
	

}
