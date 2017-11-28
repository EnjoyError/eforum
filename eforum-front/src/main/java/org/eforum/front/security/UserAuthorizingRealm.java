package org.eforum.front.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.eforum.entity.User;
import org.eforum.service.SecurityService;
import org.eforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Configurable
public class UserAuthorizingRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String username = (String) getAvailablePrincipal(principalCollection);
		System.out.println(username);
		//权限
		Set<String> s = new HashSet<String>();
		s.add("printer:print");
		s.add("document:read]");
		authorizationInfo.setStringPermissions(s);
		//角色
		List<String> roleCodes = securityService.getRoleCodeByUsername(username);
		Set<String> r = new HashSet<String>();
		r.addAll(roleCodes);
		authorizationInfo.setRoles(r);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		String username = (String) authenticationToken.getPrincipal();
		User user = userService.findUserByName(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(),
				getName());
		return authenticationInfo;
	}
}
