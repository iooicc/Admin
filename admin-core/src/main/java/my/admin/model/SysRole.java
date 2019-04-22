package my.admin.model;
import java.math.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.AssignID;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-09-21
*/
public class SysRole extends my.admin.core.model.BaseModel {
	
	@AssignID
	//角色编码
	private String roleCode ;
	//租户代码
	private String corpCode ;
	//租户名称
	private String corpName ;
	//创建者
	private String createBy ;
	//数据范围设置（0未设置  1全部数据 2自定义数据）
	private String dataScope ;
	//系统内置（1是 0否）
	private String isSys ;
	//备注信息
	private String remarks ;
	//角色名称
	private String roleName ;
	//角色排序（升序）
	private Long roleSort ;
	//角色分类（高管、中层、基层、其它）
	private String roleType ;
	//状态（0正常 1删除 2停用）
	private String status ;
	//更新者
	private String updateBy ;
	//用户类型（employee员工 member会员）
	private String userType ;
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	private List<SysRoleMenu> roleMenuList = new ArrayList<SysRoleMenu>();
	private List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
	
	private Integer isBuilt;
	
	private List<SysRoleDataScope> roleDataScopeList = new ArrayList<SysRoleDataScope>();
	
	public SysRole() {
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
	
	/**租户代码
	*@return 
	*/
	public String getCorpCode(){
		return  corpCode;
	}
	/**租户代码
	*@param  corpCode
	*/
	public void setCorpCode(String corpCode ){
		this.corpCode = corpCode;
	}
	
	/**租户名称
	*@return 
	*/
	public String getCorpName(){
		return  corpName;
	}
	/**租户名称
	*@param  corpName
	*/
	public void setCorpName(String corpName ){
		this.corpName = corpName;
	}
	
	/**创建者
	*@return 
	*/
	public String getCreateBy(){
		return  createBy;
	}
	/**创建者
	*@param  createBy
	*/
	public void setCreateBy(String createBy ){
		this.createBy = createBy;
	}
	
	/**数据范围设置（0未设置  1全部数据 2自定义数据）
	*@return 
	*/
	public String getDataScope(){
		return  dataScope;
	}
	/**数据范围设置（0未设置  1全部数据 2自定义数据）
	*@param  dataScope
	*/
	public void setDataScope(String dataScope ){
		this.dataScope = dataScope;
	}
	
	/**系统内置（1是 0否）
	*@return 
	*/
	public String getIsSys(){
		return  isSys;
	}
	/**系统内置（1是 0否）
	*@param  isSys
	*/
	public void setIsSys(String isSys ){
		this.isSys = isSys;
	}
	
	/**备注信息
	*@return 
	*/
	public String getRemarks(){
		return  remarks;
	}
	/**备注信息
	*@param  remarks
	*/
	public void setRemarks(String remarks ){
		this.remarks = remarks;
	}
	
	/**角色名称
	*@return 
	*/
	public String getRoleName(){
		return  roleName;
	}
	/**角色名称
	*@param  roleName
	*/
	public void setRoleName(String roleName ){
		this.roleName = roleName;
	}
	
	/**角色排序（升序）
	*@return 
	*/
	public Long getRoleSort(){
		return  roleSort;
	}
	/**角色排序（升序）
	*@param  roleSort
	*/
	public void setRoleSort(Long roleSort ){
		this.roleSort = roleSort;
	}
	
	/**角色分类（高管、中层、基层、其它）
	*@return 
	*/
	public String getRoleType(){
		return  roleType;
	}
	/**角色分类（高管、中层、基层、其它）
	*@param  roleType
	*/
	public void setRoleType(String roleType ){
		this.roleType = roleType;
	}
	
	/**状态（0正常 1删除 2停用）
	*@return 
	*/
	public String getStatus(){
		return  status;
	}
	/**状态（0正常 1删除 2停用）
	*@param  status
	*/
	public void setStatus(String status ){
		this.status = status;
	}
	
	/**更新者
	*@return 
	*/
	public String getUpdateBy(){
		return  updateBy;
	}
	/**更新者
	*@param  updateBy
	*/
	public void setUpdateBy(String updateBy ){
		this.updateBy = updateBy;
	}
	
	/**用户类型（employee员工 member会员）
	*@return 
	*/
	public String getUserType(){
		return  userType;
	}
	/**用户类型（employee员工 member会员）
	*@param  userType
	*/
	public void setUserType(String userType ){
		this.userType = userType;
	}
	
	/**创建时间
	*@return 
	*/
	public Date getCreateDate(){
		return  createDate;
	}
	/**创建时间
	*@param  createDate
	*/
	public void setCreateDate(Date createDate ){
		this.createDate = createDate;
	}
	
	/**更新时间
	*@return 
	*/
	public Date getUpdateDate(){
		return  updateDate;
	}
	/**更新时间
	*@param  updateDate
	*/
	public void setUpdateDate(Date updateDate ){
		this.updateDate = updateDate;
	}
	
	public List<SysRoleMenu> getRoleMenuList() {
		return this.roleMenuList;
	}
	public void setRoleMenuListJson(String jsonString) {
		List<String> list = JSONUtil.toBean(jsonString, List.class);
		if (list != null) {
			for(int i=0;i<list.size();i++) {
				String menuCode = list.get(i);
				
				SysRoleMenu ii = new SysRoleMenu();
				ii.setRoleCode(this.roleCode);
				ii.setMenuCode(menuCode);
				this.roleMenuList.add(ii);
			}
		}
	}
	
	public List<SysUserRole> getUserRoleList() {
		return this.userRoleList;
	}
	public void setUserRoleString(String userCodes) {
		String[] arr = StrUtil.split(userCodes, ",");
		if (arr!=null && arr.length>0) {
			for(int i=0;i<arr.length;i++) {
				String userCode = arr[i];
				
				SysUserRole ii = new SysUserRole();
				ii.setUserCode(userCode);
				ii.setRoleCode(this.roleCode);
				this.userRoleList.add(ii);
			}
		}
	}

	public Integer getIsBuilt() {
		return isBuilt;
	}

	public void setIsBuilt(Integer isBuilt) {
		this.isBuilt = isBuilt;
	}
	
	public List<SysRoleDataScope> getRoleDataScopeList() {
		return this.roleDataScopeList;
	}
	public void setRoleDataScopeListJson(String jsonString) {
		this.roleDataScopeList = new JSONArray(jsonString).toList(SysRoleDataScope.class) ;
		
		for(int i=0;i<this.roleDataScopeList.size();i++) {
			SysRoleDataScope ii = this.roleDataScopeList.get(i);
			
			ii.setRoleCode(this.roleCode);
		}
	}
}
