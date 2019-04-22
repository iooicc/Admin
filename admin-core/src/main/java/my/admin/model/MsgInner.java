package my.admin.model;

import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import my.admin.model.SysUser;

/* 
* 
* gen by beetlsql 2018-10-19
*/
@Table(name="sys_msg_inner")
public class MsgInner extends my.admin.core.model.BaseModel {
	
	//编号
	@AssignID
	private String id ;
	//内容级别（1普通 2一般 3紧急）
	private String contentLevel ;
	//内容类型（1公告 2新闻 3会议  4系统 5其它）
	private String contentType ;
	//创建者
	private String createBy ;
	//是否有附件
	private String isAttac ;
	//消息内容
	private String msgContent ;
	//消息标题
	private String msgTitle ;
	//通知类型（PC APP 短信 邮件 微信）多选
	private String notifyTypes ;
	//接受者字符串
	private String receiveCodes ;
	//接受者名称字符串
	private String receiveNames ;
	//接受者类型（1用户 2部门 3角色 4岗位）
	private String receiveType ;
	//备注信息
	private String remarks ;
	//发送者用户编码
	private String sendUserCode ;
	//发送者用户姓名
	private String sendUserName ;
	//状态（0正常 1删除 4审核 5驳回 9草稿）
	private String status ;
	//更新者
	private String updateBy ;
	//创建时间
	private Date createDate ;
	//发送时间
	private Date sendDate ;
	//更新时间
	private Date updateDate ;
	
	private MsgInner parent;
		
	private SysUser user;
	
	public MsgInner() {
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
	
	/**内容级别（1普通 2一般 3紧急）
	*@return 
	*/
	public String getContentLevel(){
		return  contentLevel;
	}
	/**内容级别（1普通 2一般 3紧急）
	*@param  contentLevel
	*/
	public void setContentLevel(String contentLevel ){
		this.contentLevel = contentLevel;
	}
	
	/**内容类型（1公告 2新闻 3会议 4其它）
	*@return 
	*/
	public String getContentType(){
		return  contentType;
	}
	/**内容类型（1公告 2新闻 3会议 4其它）
	*@param  contentType
	*/
	public void setContentType(String contentType ){
		this.contentType = contentType;
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
	
	/**是否有附件
	*@return 
	*/
	public String getIsAttac(){
		return  isAttac;
	}
	/**是否有附件
	*@param  isAttac
	*/
	public void setIsAttac(String isAttac ){
		this.isAttac = isAttac;
	}
	
	/**消息内容
	*@return 
	*/
	public String getMsgContent(){
		return  msgContent;
	}
	/**消息内容
	*@param  msgContent
	*/
	public void setMsgContent(String msgContent ){
		this.msgContent = msgContent;
	}
	
	/**消息标题
	*@return 
	*/
	public String getMsgTitle(){
		return  msgTitle;
	}
	/**消息标题
	*@param  msgTitle
	*/
	public void setMsgTitle(String msgTitle ){
		this.msgTitle = msgTitle;
	}
	
	/**通知类型（PC APP 短信 邮件 微信）多选
	*@return 
	*/
	public String getNotifyTypes(){
		return  notifyTypes;
	}
	/**通知类型（PC APP 短信 邮件 微信）多选
	*@param  notifyTypes
	*/
	public void setNotifyTypes(String notifyTypes ){
		this.notifyTypes = notifyTypes;
	}
	
	/**接受者字符串
	*@return 
	*/
	public String getReceiveCodes(){
		return  receiveCodes;
	}
	/**接受者字符串
	*@param  receiveCodes
	*/
	public void setReceiveCodes(String receiveCodes ){
		this.receiveCodes = receiveCodes;
	}
	
	/**接受者名称字符串
	*@return 
	*/
	public String getReceiveNames(){
		return  receiveNames;
	}
	/**接受者名称字符串
	*@param  receiveNames
	*/
	public void setReceiveNames(String receiveNames ){
		this.receiveNames = receiveNames;
	}
	
	/**接受者类型（1公告 2新闻 3会议  4系统 5其它）
	*@return 
	*/
	public String getReceiveType(){
		return  receiveType;
	}
	/**接受者类型（1公告 2新闻 3会议  4系统 5其它）
	*@param  receiveType
	*/
	public void setReceiveType(String receiveType ){
		this.receiveType = receiveType;
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
	
	/**发送者用户编码
	*@return 
	*/
	public String getSendUserCode(){
		return  sendUserCode;
	}
	/**发送者用户编码
	*@param  sendUserCode
	*/
	public void setSendUserCode(String sendUserCode ){
		this.sendUserCode = sendUserCode;
	}
	
	/**发送者用户姓名
	*@return 
	*/
	public String getSendUserName(){
		return  sendUserName;
	}
	/**发送者用户姓名
	*@param  sendUserName
	*/
	public void setSendUserName(String sendUserName ){
		this.sendUserName = sendUserName;
	}
	
	/**状态（0正常 1删除 4审核 5驳回 9草稿）
	*@return 
	*/
	public String getStatus(){
		return  status;
	}
	/**状态（0正常 1删除 4审核 5驳回 9草稿）
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
	
	/**发送时间
	*@return 
	*/
	public Date getSendDate(){
		return  sendDate;
	}
	/**发送时间
	*@param  sendDate
	*/
	public void setSendDate(Date sendDate ){
		this.sendDate = sendDate;
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

	public MsgInner getParent() {
		return parent;
	}

	public void setParent(MsgInner parent) {
		this.parent = parent;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
	

}
