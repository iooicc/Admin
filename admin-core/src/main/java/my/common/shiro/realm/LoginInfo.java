package my.common.shiro.realm;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import my.admin.model.SysUser;

public class LoginInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Map<String, Object> params;
	
	public LoginInfo() {
	}
	
	public LoginInfo(SysUser user, Map<String, Object> var2) {
		this.id = user.getUserCode();
		this.name = user.getUserName();
		this.params = var2;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String toString() {
		return this.id;
	}
	
	public int hashCode() {
		return Objects.hashCode(this.id);
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			LoginInfo a = (LoginInfo) obj;
			if (this.getId() == null) {
				if (a.getId() != null) {
					return false;
				}
			} else if (!this.getId().equals(a.getId())) {
				return false;
			}

			return true;
		}
	}

}
