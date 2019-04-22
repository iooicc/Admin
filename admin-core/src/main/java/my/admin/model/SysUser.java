package my.admin.model;
import java.math.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.AssignID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.hutool.core.util.StrUtil;
import my.common.shiro.IUser;

import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2018-09-13
*/
public class SysUser extends my.admin.core.model.BaseModel implements IUser {
	

	public static final String STATUS_NORMAL = "0";
	public static final String STATUS_DISABLE = "2";
	
	@AssignID
	//用户编码
	private String userCode ;
	//头像路径
	private String avatar ;
	//租户代码
	private String corpCode ;
	//租户名称
	private String corpName ;
	//创建者
	private String createBy ;
	//电子邮箱
	private String email ;
	//冻结原因
	private String freezeCause ;
	//最后登陆IP
	private String lastLoginIp ;
	//登录账号
	private String loginCode ;
	//管理员类型（0内置管理员 1系统管理员  2二级管理员3非管理员）
	private String mgrType ;
	//手机号码
	private String mobile ;
	//绑定的手机串号
	private String mobileImei ;
	//登录密码
	private String password ;
	//办公电话
	private String phone ;
	//密保问题
	private String pwdQuestion ;
	//密保问题2
	private String pwdQuestion2 ;
	//密保问题3
	private String pwdQuestion3 ;
	//密保问题答案
	private String pwdQuestionAnswer ;
	//密保问题答案2
	private String pwdQuestionAnswer2 ;
	//密保问题答案3
	private String pwdQuestionAnswer3 ;
	//密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
	private Integer pwdSecurityLevel ;
	//密码修改记录
	private String pwdUpdateRecord ;
	//用户类型引用编号
	private String refCode ;
	//用户类型引用姓名
	private String refName ;
	//备注信息
	private String remarks ;
	//用户性别
	private String sex ;
	//个性签名
	private String sign ;
	//状态（0正常 1删除 2停用 3冻结）
	private String status ;
	//更新者
	private String updateBy ;
	//用户昵称
	private String userName ;
	//用户类型
	private String userType ;
	//用户权重（降序）
	private Integer userWeight ;
	//绑定的微信号
	private String wxOpenid ;
	//创建时间
	private Date createDate ;
	//冻结时间
	private Date freezeDate ;
	//最后登陆时间
	private Date lastLoginDate ;
	//密码问题修改时间
	private Date pwdQuestUpdateDate ;
	//密码最后更新时间
	private Date pwdUpdateDate ;
	//更新时间
	private Date updateDate ;
	
	private String salt;
	
	private String orgCode;
	
	private SysOrg org;
	
	private String avatarBase64;
	
	private List<SysUserRole> userRoleList = new ArrayList<SysUserRole>();
	
	private List<SysRole> roleList = new ArrayList<SysRole>();
	
	public SysUser() {
	}
	
	/**用户编码
	*@return 
	*/
	public String getUserCode(){
		return  userCode;
	}
	/**用户编码
	*@param  userCode
	*/
	public void setUserCode(String userCode ){
		this.userCode = userCode;
	}
	
	/**头像路径
	*@return 
	*/
	public String getAvatar(){
		return  avatar;
	}
	/**头像路径
	*@param  avatar
	*/
	public void setAvatar(String avatar ){
		this.avatar = avatar;
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
	
	/**冻结原因
	*@return 
	*/
	public String getFreezeCause(){
		return  freezeCause;
	}
	/**冻结原因
	*@param  freezeCause
	*/
	public void setFreezeCause(String freezeCause ){
		this.freezeCause = freezeCause;
	}
	
	/**最后登陆IP
	*@return 
	*/
	public String getLastLoginIp(){
		return  lastLoginIp;
	}
	/**最后登陆IP
	*@param  lastLoginIp
	*/
	public void setLastLoginIp(String lastLoginIp ){
		this.lastLoginIp = lastLoginIp;
	}
	
	/**登录账号
	*@return 
	*/
	public String getLoginCode(){
		return  loginCode;
	}
	/**登录账号
	*@param  loginCode
	*/
	public void setLoginCode(String loginCode ){
		this.loginCode = loginCode;
	}
	
	/**管理员类型（0非管理员 1系统管理员  2二级管理员）
	*@return 
	*/
	public String getMgrType(){
		return  mgrType;
	}
	/**管理员类型（0非管理员 1系统管理员  2二级管理员）
	*@param  mgrType
	*/
	public void setMgrType(String mgrType ){
		this.mgrType = mgrType;
	}
	
	/**手机号码
	*@return 
	*/
	public String getMobile(){
		return  mobile;
	}
	/**手机号码
	*@param  mobile
	*/
	public void setMobile(String mobile ){
		this.mobile = mobile;
	}
	
	/**绑定的手机串号
	*@return 
	*/
	public String getMobileImei(){
		return  mobileImei;
	}
	/**绑定的手机串号
	*@param  mobileImei
	*/
	public void setMobileImei(String mobileImei ){
		this.mobileImei = mobileImei;
	}
	
	/**登录密码
	*@return 
	*/
	public String getPassword(){
		return  password;
	}
	/**登录密码
	*@param  password
	*/
	public void setPassword(String password ){
		this.password = password;
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
	
	/**密保问题
	*@return 
	*/
	public String getPwdQuestion(){
		return  pwdQuestion;
	}
	/**密保问题
	*@param  pwdQuestion
	*/
	public void setPwdQuestion(String pwdQuestion ){
		this.pwdQuestion = pwdQuestion;
	}
	
	/**密保问题2
	*@return 
	*/
	public String getPwdQuestion2(){
		return  pwdQuestion2;
	}
	/**密保问题2
	*@param  pwdQuestion2
	*/
	public void setPwdQuestion2(String pwdQuestion2 ){
		this.pwdQuestion2 = pwdQuestion2;
	}
	
	/**密保问题3
	*@return 
	*/
	public String getPwdQuestion3(){
		return  pwdQuestion3;
	}
	/**密保问题3
	*@param  pwdQuestion3
	*/
	public void setPwdQuestion3(String pwdQuestion3 ){
		this.pwdQuestion3 = pwdQuestion3;
	}
	
	/**密保问题答案
	*@return 
	*/
	public String getPwdQuestionAnswer(){
		return  pwdQuestionAnswer;
	}
	/**密保问题答案
	*@param  pwdQuestionAnswer
	*/
	public void setPwdQuestionAnswer(String pwdQuestionAnswer ){
		this.pwdQuestionAnswer = pwdQuestionAnswer;
	}
	
	/**密保问题答案2
	*@return 
	*/
	public String getPwdQuestionAnswer2(){
		return  pwdQuestionAnswer2;
	}
	/**密保问题答案2
	*@param  pwdQuestionAnswer2
	*/
	public void setPwdQuestionAnswer2(String pwdQuestionAnswer2 ){
		this.pwdQuestionAnswer2 = pwdQuestionAnswer2;
	}
	
	/**密保问题答案3
	*@return 
	*/
	public String getPwdQuestionAnswer3(){
		return  pwdQuestionAnswer3;
	}
	/**密保问题答案3
	*@param  pwdQuestionAnswer3
	*/
	public void setPwdQuestionAnswer3(String pwdQuestionAnswer3 ){
		this.pwdQuestionAnswer3 = pwdQuestionAnswer3;
	}
	
	/**密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
	*@return 
	*/
	public Integer getPwdSecurityLevel(){
		return  pwdSecurityLevel;
	}
	/**密码安全级别（0初始 1很弱 2弱 3安全 4很安全）
	*@param  pwdSecurityLevel
	*/
	public void setPwdSecurityLevel(Integer pwdSecurityLevel ){
		this.pwdSecurityLevel = pwdSecurityLevel;
	}
	
	/**密码修改记录
	*@return 
	*/
	public String getPwdUpdateRecord(){
		return  pwdUpdateRecord;
	}
	/**密码修改记录
	*@param  pwdUpdateRecord
	*/
	public void setPwdUpdateRecord(String pwdUpdateRecord ){
		this.pwdUpdateRecord = pwdUpdateRecord;
	}
	
	/**用户类型引用编号
	*@return 
	*/
	public String getRefCode(){
		return  refCode;
	}
	/**用户类型引用编号
	*@param  refCode
	*/
	public void setRefCode(String refCode ){
		this.refCode = refCode;
	}
	
	/**用户类型引用姓名
	*@return 
	*/
	public String getRefName(){
		return  refName;
	}
	/**用户类型引用姓名
	*@param  refName
	*/
	public void setRefName(String refName ){
		this.refName = refName;
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
	
	/**用户性别
	*@return 
	*/
	public String getSex(){
		return  sex;
	}
	/**用户性别
	*@param  sex
	*/
	public void setSex(String sex ){
		this.sex = sex;
	}
	
	/**个性签名
	*@return 
	*/
	public String getSign(){
		return  sign;
	}
	/**个性签名
	*@param  sign
	*/
	public void setSign(String sign ){
		this.sign = sign;
	}
	
	/**状态（0正常 1删除 2停用 3冻结）
	*@return 
	*/
	public String getStatus(){
		return  status;
	}
	/**状态（0正常 1删除 2停用 3冻结）
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
	
	/**用户昵称
	*@return 
	*/
	public String getUserName(){
		return  userName;
	}
	/**用户昵称
	*@param  userName
	*/
	public void setUserName(String userName ){
		this.userName = userName;
	}
	
	/**用户类型
	*@return 
	*/
	public String getUserType(){
		return  userType;
	}
	/**用户类型
	*@param  userType
	*/
	public void setUserType(String userType ){
		this.userType = userType;
	}
	
	/**用户权重（降序）
	*@return 
	*/
	public Integer getUserWeight(){
		return  userWeight;
	}
	/**用户权重（降序）
	*@param  userWeight
	*/
	public void setUserWeight(Integer userWeight ){
		this.userWeight = userWeight;
	}
	
	/**绑定的微信号
	*@return 
	*/
	public String getWxOpenid(){
		return  wxOpenid;
	}
	/**绑定的微信号
	*@param  wxOpenid
	*/
	public void setWxOpenid(String wxOpenid ){
		this.wxOpenid = wxOpenid;
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
	
	/**冻结时间
	*@return 
	*/
	public Date getFreezeDate(){
		return  freezeDate;
	}
	/**冻结时间
	*@param  freezeDate
	*/
	public void setFreezeDate(Date freezeDate ){
		this.freezeDate = freezeDate;
	}
	
	/**最后登陆时间
	*@return 
	*/
	public Date getLastLoginDate(){
		return  lastLoginDate;
	}
	/**最后登陆时间
	*@param  lastLoginDate
	*/
	public void setLastLoginDate(Date lastLoginDate ){
		this.lastLoginDate = lastLoginDate;
	}
	
	/**密码问题修改时间
	*@return 
	*/
	public Date getPwdQuestUpdateDate(){
		return  pwdQuestUpdateDate;
	}
	/**密码问题修改时间
	*@param  pwdQuestUpdateDate
	*/
	public void setPwdQuestUpdateDate(Date pwdQuestUpdateDate ){
		this.pwdQuestUpdateDate = pwdQuestUpdateDate;
	}
	
	/**密码最后更新时间
	*@return 
	*/
	public Date getPwdUpdateDate(){
		return  pwdUpdateDate;
	}
	/**密码最后更新时间
	*@param  pwdUpdateDate
	*/
	public void setPwdUpdateDate(Date pwdUpdateDate ){
		this.pwdUpdateDate = pwdUpdateDate;
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public SysOrg getOrg() {
		return org;
	}

	public void setOrg(SysOrg org) {
		this.org = org;
	}

	public String getAvatarBase64() {
		return avatarBase64;
	}

	public void setAvatarBase64(String avatarBase64) {
		this.avatarBase64 = avatarBase64;
	}
	
	public void setUserRoleString(String roleCodes) {
		
		String[] ids = null;
		if(!StrUtil.isBlank(roleCodes)
			&& (ids = StrUtil.split(roleCodes, ","))!=null
			&& ids.length>0
		) {
			for(int i=0;i<ids.length;i++) {
				SysUserRole ii = new SysUserRole();
				ii.setRoleCode(ids[i]);
				this.userRoleList.add(ii);
			}
		}
	}
	
	@JsonIgnore
	public List<SysUserRole> getUserRoleList() {
		return this.userRoleList;
	}
	
	@JsonIgnore
	public List<SysRole> getRoleList() {
		return this.roleList;
	}
	
	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String getUsername() {
		return getLoginCode();
	}

	@Override
	public boolean isEnabled() {
		String status = getStatus();
		return !STATUS_DISABLE.equals(status);
	}
}
