package my.common.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hutool.core.util.StrUtil;
import my.common.shiro.IUser;

public class AuthorizingRealm extends org.apache.shiro.realm.AuthorizingRealm {
	
	@Autowired
	UserService userService;

	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		Set<String> roles = new HashSet<String>();
		roles.add("user");
		simpleAuthorizationInfo.setRoles(roles);
		
		Set<String> permissions = new HashSet<String>();
		simpleAuthorizationInfo.setStringPermissions(permissions);
		
		return simpleAuthorizationInfo;
	}

	/**
	 * 获取身份验证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		
		if(authenticationToken==null || !(authenticationToken instanceof UsernamePasswordToken)) {
			return null;
		}
		
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		
		//获取用户信息
		String username = token.getUsername();
		
		if(StrUtil.isBlank(username)) {
			throw new AuthenticationException("msg:账号不能为空.");
		}
		
        IUser user = userService.loadUserByUsername(username);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
        	if(!user.isEnabled()) {
        		throw new AuthenticationException("msg:账户被停用.");
        	}
        	
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
	}
}
