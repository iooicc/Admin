package my.admin.model;
import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;

import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-10-23
*/
public class SysDictData extends my.admin.core.model.BaseModel {
	
	//字典编码
	@AssignID
	private String dictCode ;
	//租户代码
	private String corpCode ;
	//租户名称
	private String corpName ;
	//创建者
	private String createBy ;
	//css类名（如：red）
	private String cssClass ;
	//css样式（如：color:red)
	private String cssStyle ;
	//字典描述
	private String description ;
	//字典标签
	private String dictLabel ;
	//字典类型
	private String dictType ;
	//字典键值
	private String dictValue ;
	//系统内置（1是 0否）
	private String isSys ;
	//父级编号
	private String parentCode ;
	//所有父级编号
	private String parentCodes ;
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
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	private SysDictData parent;
	
	public SysDictData() {
	}
	
	/**字典编码
	*@return 
	*/
	public String getDictCode(){
		return  dictCode;
	}
	/**字典编码
	*@param  dictCode
	*/
	public void setDictCode(String dictCode ){
		this.dictCode = dictCode;
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
	
	/**css类名（如：red）
	*@return 
	*/
	public String getCssClass(){
		return  cssClass;
	}
	/**css类名（如：red）
	*@param  cssClass
	*/
	public void setCssClass(String cssClass ){
		this.cssClass = cssClass;
	}
	
	/**css样式（如：color:red)
	*@return 
	*/
	public String getCssStyle(){
		return  cssStyle;
	}
	/**css样式（如：color:red)
	*@param  cssStyle
	*/
	public void setCssStyle(String cssStyle ){
		this.cssStyle = cssStyle;
	}
	
	/**字典描述
	*@return 
	*/
	public String getDescription(){
		return  description;
	}
	/**字典描述
	*@param  description
	*/
	public void setDescription(String description ){
		this.description = description;
	}
	
	/**字典标签
	*@return 
	*/
	public String getDictLabel(){
		return  dictLabel;
	}
	/**字典标签
	*@param  dictLabel
	*/
	public void setDictLabel(String dictLabel ){
		this.dictLabel = dictLabel;
	}
	
	/**字典类型
	*@return 
	*/
	public String getDictType(){
		return  dictType;
	}
	/**字典类型
	*@param  dictType
	*/
	public void setDictType(String dictType ){
		this.dictType = dictType;
	}
	
	/**字典键值
	*@return 
	*/
	public String getDictValue(){
		return  dictValue;
	}
	/**字典键值
	*@param  dictValue
	*/
	public void setDictValue(String dictValue ){
		this.dictValue = dictValue;
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

	public SysDictData getParent() {
		return parent;
	}

	public void setParent(SysDictData parent) {
		this.parent = parent;
	}
	

}
