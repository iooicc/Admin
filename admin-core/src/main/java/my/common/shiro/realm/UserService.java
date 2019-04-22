package my.common.shiro.realm;

import my.common.shiro.IUser;

public interface UserService {
	IUser loadUserByUsername(String username);
}
