package my.admin.model;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-12-17
*/
public class SysRoleDataScope extends my.admin.core.model.BaseModel {
	
	//控制数据
	private String ctrlData ;
	//控制权限
	private String ctrlPermi ;
	//控制类型
	private String ctrlType ;
	//控制角色编码
	private String roleCode ;
	
	public SysRoleDataScope() {
	}
	
	/**控制数据
	*@return 
	*/
	public String getCtrlData(){
		return  ctrlData;
	}
	/**控制数据
	*@param  ctrlData
	*/
	public void setCtrlData(String ctrlData ){
		this.ctrlData = ctrlData;
	}
	
	/**控制权限
	*@return 
	*/
	public String getCtrlPermi(){
		return  ctrlPermi;
	}
	/**控制权限
	*@param  ctrlPermi
	*/
	public void setCtrlPermi(String ctrlPermi ){
		this.ctrlPermi = ctrlPermi;
	}
	
	/**控制类型
	*@return 
	*/
	public String getCtrlType(){
		return  ctrlType;
	}
	/**控制类型
	*@param  ctrlType
	*/
	public void setCtrlType(String ctrlType ){
		this.ctrlType = ctrlType;
	}
	
	/**控制角色编码
	*@return 
	*/
	public String getRoleCode(){
		return  roleCode;
	}
	/**控制角色编码
	*@param  roleCode
	*/
	public void setRoleCode(String roleCode ){
		this.roleCode = roleCode;
	}
	

}
