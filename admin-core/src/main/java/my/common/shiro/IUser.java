package my.common.shiro;

import java.io.Serializable;

public interface IUser extends Serializable {
	String getPassword();
	String getUsername();
	
	boolean isEnabled();
}
