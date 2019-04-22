package my.admin.model;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-10-08
*/
public class SysRoleMenu extends my.admin.core.model.BaseModel {
	
	//菜单编码
	private String menuCode ;
	//角色编码
	private String roleCode ;
	
	public SysRoleMenu() {
	}
	
	/**菜单编码
	*@return 
	*/
	public String getMenuCode(){
		return  menuCode;
	}
	/**菜单编码
	*@param  menuCode
	*/
	public void setMenuCode(String menuCode ){
		this.menuCode = menuCode;
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
	

}
