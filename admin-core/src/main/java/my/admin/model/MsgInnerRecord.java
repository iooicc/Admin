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
@Table(name="sys_msg_inner_record")
public class MsgInnerRecord extends my.admin.core.model.BaseModel {
	
	//编号
	@AssignID
	private String id ;
	//是否标星
	private String isStar ;
	//所属消息
	private String msgInnerId ;
	//读取状态（0未送达 1未读 2已读）
	private String readStatus ;
	//接受者用户编码
	private String receiveUserCode ;
	//接受者用户姓名
	private String receiveUserName ;
	//阅读时间
	private Date readDate ;	
	//创建时间
	private Date createDate ;
	
	private SysUser user;
	
	private MsgInnerRecord parent;
	
	private MsgInner msgInner;
	
	private String isPush;
	
	public MsgInnerRecord() {
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
	
	/**是否标星
	*@return 
	*/
	public String getIsStar(){
		return  isStar;
	}
	/**是否标星
	*@param  isStar
	*/
	public void setIsStar(String isStar ){
		this.isStar = isStar;
	}
	
	/**所属消息
	*@return 
	*/
	public String getMsgInnerId(){
		return  msgInnerId;
	}
	/**所属消息
	*@param  msgInnerId
	*/
	public void setMsgInnerId(String msgInnerId ){
		this.msgInnerId = msgInnerId;
	}
	
	/**读取状态（0未送达 1未读 2已读）
	*@return 
	*/
	public String getReadStatus(){
		return  readStatus;
	}
	/**读取状态（0未送达 1未读 2已读）
	*@param  readStatus
	*/
	public void setReadStatus(String readStatus ){
		this.readStatus = readStatus;
	}
	
	/**接受者用户编码
	*@return 
	*/
	public String getReceiveUserCode(){
		return  receiveUserCode;
	}
	/**接受者用户编码
	*@param  receiveUserCode
	*/
	public void setReceiveUserCode(String receiveUserCode ){
		this.receiveUserCode = receiveUserCode;
	}
	
	/**接受者用户姓名
	*@return 
	*/
	public String getReceiveUserName(){
		return  receiveUserName;
	}
	/**接受者用户姓名
	*@param  receiveUserName
	*/
	public void setReceiveUserName(String receiveUserName ){
		this.receiveUserName = receiveUserName;
	}
	
	/**阅读时间
	*@return 
	*/
	public Date getReadDate(){
		return  readDate;
	}
	/**阅读时间
	*@param  readDate
	*/
	public void setReadDate(Date readDate ){
		this.readDate = readDate;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public MsgInnerRecord getParent() {
		return parent;
	}

	public void setParent(MsgInnerRecord parent) {
		this.parent = parent;
	}

	public MsgInner getMsgInner() {
		return msgInner;
	}

	public void setMsgInner(MsgInner msgInner) {
		this.msgInner = msgInner;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsPush() {
		return isPush;
	}

	public void setIsPush(String isPush) {
		this.isPush = isPush;
	}
	
	

}
