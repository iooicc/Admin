package my.admin.model;
import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;

import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-10-17
*/
public class SysFile extends my.admin.core.model.BaseModel {
	
	//编号
	@AssignID
	private String fileId ;
	//附件的业务类型。默认0,0，无；1 申请；2 模型；
	private String boType ;
	private Long fileSize ;
	//是否是step:0,否；1，是
	private Integer isStep ;
	//外键，通用编号
	private String boId ;
	//租户代码
	private String corpCode ;
	//租户名称
	private String corpName ;
	private String createBy ;
	//扩展名
	private String fileExt ;
	//文件名
	private String fileName ;
	//文件URL
	private String filePath ;
	//文件类型
	private String fileType ;
	//备注信息
	private String remarks ;
	//更新者
	private String updateBy ;
	private Date createDate ;
	//更新时间
	private Date updateDate ;
	
	private Integer isThumb;
	
	private String isStepSupport;
	
	private String resultMsg;
	
	public SysFile() {
	}
	
	/**编号
	*@return 
	*/
	public String getFileId(){
		return  fileId;
	}
	/**编号
	*@param  fileId
	*/
	public void setFileId(String fileId ){
		this.fileId = fileId;
	}
	
	/**附件的业务类型。默认0,0，无；1 申请；2 模型；
	*@return 
	*/
	public String getBoType(){
		return  boType;
	}
	/**附件的业务类型。默认0,0，无；1 申请；2 模型；
	*@param  boType
	*/
	public void setBoType(String boType ){
		this.boType = boType;
	}
	
	public Long getFileSize(){
		return  fileSize;
	}
	public void setFileSize(Long fileSize ){
		this.fileSize = fileSize;
	}
	
	/**是否是step:0,否；1，是
	*@return 
	*/
	public Integer getIsStep(){
		return  isStep;
	}
	/**是否是step:0,否；1，是
	*@param  isStep
	*/
	public void setIsStep(Integer isStep ){
		this.isStep = isStep;
	}
	
	/**外键，通用编号
	*@return 
	*/
	public String getBoId(){
		return  boId;
	}
	/**外键，通用编号
	*@param  boId
	*/
	public void setBoId(String boId ){
		this.boId = boId;
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
	
	public String getCreateBy(){
		return  createBy;
	}
	public void setCreateBy(String createBy ){
		this.createBy = createBy;
	}
	
	/**扩展名
	*@return 
	*/
	public String getFileExt(){
		return  fileExt;
	}
	/**扩展名
	*@param  fileExt
	*/
	public void setFileExt(String fileExt ){
		this.fileExt = fileExt;
	}
	
	/**文件名
	*@return 
	*/
	public String getFileName(){
		return  fileName;
	}
	/**文件名
	*@param  fileName
	*/
	public void setFileName(String fileName ){
		this.fileName = fileName;
	}
	
	/**文件URL
	*@return 
	*/
	public String getFilePath(){
		return  filePath;
	}
	/**文件URL
	*@param  filePath
	*/
	public void setFilePath(String filePath ){
		this.filePath = filePath;
	}
	
	/**文件类型
	*@return 
	*/
	public String getFileType(){
		return  fileType;
	}
	/**文件类型
	*@param  fileType
	*/
	public void setFileType(String fileType ){
		this.fileType = fileType;
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
	
	public Date getCreateDate(){
		return  createDate;
	}
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

	public Integer getIsThumb() {
		return isThumb;
	}

	public void setIsThumb(Integer isThumb) {
		this.isThumb = isThumb;
	}

	

	public String getIsStepSupport() {
		return isStepSupport;
	}

	public void setIsStepSupport(String isStepSupport) {
		this.isStepSupport = isStepSupport;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	
	

}
