package my.admin.model;
import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;

import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-10-26
*/
public class SysConfig extends my.admin.core.model.BaseModel {
	
	//编号
	@AssignID
	private String id ;
	//参数键
	private String configKey ;
	//名称
	private String configName ;
	//参数值
	private String configValue ;
	//创建者
	private String createBy ;
	//系统内置（1是 0否）
	private String isSys ;
	//备注信息
	private String remarks ;
	//更新者
	private String updateBy ;
	//创建时间
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	public SysConfig() {
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
	
	/**参数键
	*@return 
	*/
	public String getConfigKey(){
		return  configKey;
	}
	/**参数键
	*@param  configKey
	*/
	public void setConfigKey(String configKey ){
		this.configKey = configKey;
	}
	
	/**名称
	*@return 
	*/
	public String getConfigName(){
		return  configName;
	}
	/**名称
	*@param  configName
	*/
	public void setConfigName(String configName ){
		this.configName = configName;
	}
	
	/**参数值
	*@return 
	*/
	public String getConfigValue(){
		return  configValue;
	}
	/**参数值
	*@param  configValue
	*/
	public void setConfigValue(String configValue ){
		this.configValue = configValue;
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
