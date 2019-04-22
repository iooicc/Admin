package my.admin.model;
import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;

import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-10-25
*/
public class SysDictType extends my.admin.core.model.BaseModel {
	
	//编号
	@AssignID
	private String id ;
	//创建者
	private String createBy ;
	//字典名称
	private String dictName ;
	//字典类型
	private String dictType ;
	//是否系统字典
	private String isSys ;
	//备注信息
	private String remarks ;
	//状态（0正常 1删除 2停用）
	private String status ;
	//更新者
	private String updateBy ;
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	public SysDictType() {
	}
	
	/**编号
	*@return 
	*/
	public String getId(){
		return  id;
	}
	/**编号
	*@param  id
	*/
	public void setId(String id ){
		this.id = id;
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
	
	/**字典名称
	*@return 
	*/
	public String getDictName(){
		return  dictName;
	}
	/**字典名称
	*@param  dictName
	*/
	public void setDictName(String dictName ){
		this.dictName = dictName;
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
	
	/**是否系统字典
	*@return 
	*/
	public String getIsSys(){
		return  isSys;
	}
	/**是否系统字典
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
	

}
