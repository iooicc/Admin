package my.admin.model;

import java.math.*;
import java.util.Date;
import org.beetl.sql.core.annotatoin.AssignID;

/* 
* 
* gen by beetlsql 2018-09-12
*/
public class SysOrg extends my.admin.core.model.BaseModel {
	
	//机构编码
	@AssignID
	private String orgCode ;
	//联系地址
	private String address ;
	//创建者
	private String createBy ;
	//电子邮箱
	private String email ;
	//机构全称
	private String fullName ;
	//负责人
	private String leader ;
	//机构名称
	private String orgName ;
	//机构类型
	private String orgType ;
	//父级编号
	private String parentCode ;
	//所有父级编号
	private String parentCodes ;
	//办公电话
	private String phone ;
	//备注信息
	private String remarks ;
	//状态（0正常 1删除 2停用）
	private String status ;
	//是否最末级
	private String treeLeaf ;
	//层次级别
	private Integer treeLevel ;
	//全节点名
	private String treeNames ;
	//本级排序号（升序）
	private Long treeSort ;
	//所有级别排序号
	private String treeSorts ;
	//更新者
	private String updateBy ;
	//邮政编码
	private String zipCode ;
	
	private String viewCode ;
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	private String corpCode;
	
	private SysOrg parent;
	
	public SysOrg() {
	}
	
	/**机构编码
	*@return 
	*/
	public String getOrgCode(){
		return  orgCode;
	}
	/**机构编码
	*@param  orgCode
	*/
	public void setOrgCode(String orgCode ){
		this.orgCode = orgCode;
	}
	
	/**联系地址
	*@return 
	*/
	public String getAddress(){
		return  address;
	}
	/**联系地址
	*@param  address
	*/
	public void setAddress(String address ){
		this.address = address;
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
	
	/**电子邮箱
	*@return 
	*/
	public String getEmail(){
		return  email;
	}
	/**电子邮箱
	*@param  email
	*/
	public void setEmail(String email ){
		this.email = email;
	}
	
	/**机构全称
	*@return 
	*/
	public String getFullName(){
		return  fullName;
	}
	/**机构全称
	*@param  fullName
	*/
	public void setFullName(String fullName ){
		this.fullName = fullName;
	}
	
	/**负责人
	*@return 
	*/
	public String getLeader(){
		return  leader;
	}
	/**负责人
	*@param  leader
	*/
	public void setLeader(String leader ){
		this.leader = leader;
	}
	
	/**机构名称
	*@return 
	*/
	public String getOrgName(){
		return  orgName;
	}
	/**机构名称
	*@param  orgName
	*/
	public void setOrgName(String orgName ){
		this.orgName = orgName;
	}
	
	/**机构类型
	*@return 
	*/
	public String getOrgType(){
		return  orgType;
	}
	/**机构类型
	*@param  orgType
	*/
	public void setOrgType(String orgType ){
		this.orgType = orgType;
	}
	
	/**父级编号
	*@return 
	*/
	public String getParentCode(){
		return  parentCode;
	}
	/**父级编号
	*@param  parentCode
	*/
	public void setParentCode(String parentCode ){
		this.parentCode = parentCode;
	}
	
	/**所有父级编号
	*@return 
	*/
	public String getParentCodes(){
		return  parentCodes;
	}
	/**所有父级编号
	*@param  parentCodes
	*/
	public void setParentCodes(String parentCodes ){
		this.parentCodes = parentCodes;
	}
	
	/**办公电话
	*@return 
	*/
	public String getPhone(){
		return  phone;
	}
	/**办公电话
	*@param  phone
	*/
	public void setPhone(String phone ){
		this.phone = phone;
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
	
	/**是否最末级
	*@return 
	*/
	public String getTreeLeaf(){
		return  treeLeaf;
	}
	/**是否最末级
	*@param  treeLeaf
	*/
	public void setTreeLeaf(String treeLeaf ){
		this.treeLeaf = treeLeaf;
	}
	
	/**层次级别
	*@return 
	*/
	public Integer getTreeLevel(){
		return  treeLevel;
	}
	/**层次级别
	*@param  treeLevel
	*/
	public void setTreeLevel(Integer treeLevel ){
		this.treeLevel = treeLevel;
	}
	
	/**全节点名
	*@return 
	*/
	public String getTreeNames(){
		return  treeNames;
	}
	/**全节点名
	*@param  treeNames
	*/
	public void setTreeNames(String treeNames ){
		this.treeNames = treeNames;
	}
	
	/**本级排序号（升序）
	*@return 
	*/
	public Long getTreeSort(){
		if(treeSort==null) {
			treeSort = 0L;
		}
		return  treeSort;
	}
	/**本级排序号（升序）
	*@param  treeSort
	*/
	public void setTreeSort(Long treeSort ){
		this.treeSort = treeSort;
	}
	
	/**所有级别排序号
	*@return 
	*/
	public String getTreeSorts(){
		return  treeSorts;
	}
	/**所有级别排序号
	*@param  treeSorts
	*/
	public void setTreeSorts(String treeSorts ){
		this.treeSorts = treeSorts;
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
	
	/**邮政编码
	*@return 
	*/
	public String getZipCode(){
		return  zipCode;
	}
	/**邮政编码
	*@param  zipCode
	*/
	public void setZipCode(String zipCode ){
		this.zipCode = zipCode;
	}
	
	public String getViewCode() {
		return viewCode;
	}

	public void setViewCode(String viewCode) {
		this.viewCode = viewCode;
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

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public SysOrg getParent() {
		return parent;
	}

	public void setParent(SysOrg parent) {
		this.parent = parent;
	}
	
	
}
