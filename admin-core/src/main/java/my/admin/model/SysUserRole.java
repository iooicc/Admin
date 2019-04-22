package my.admin.model;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-10-09
*/
public class SysUserRole extends my.admin.core.model.BaseModel {
	
	//角色编码
	private String roleCode ;
	//用户编码
	private String userCode ;
	
	public SysUserRole() {
	}
	
	/**角色编码
	*@return 
	*/
	public String getRoleCode(){
		return  roleCode;
	}
	/**角色编码
	*@param  roleCode
	*/
	public void setRoleCode(String roleCode ){
		this.roleCode = roleCode;
	}
	
	/**用户编码
	*@return 
	*/
	public String getUserCode(){
		return  userCode;
	}
	/**用户编码
	*@param  userCode
	*/
	public void setUserCode(String userCode ){
		this.userCode = userCode;
	}
	

}
