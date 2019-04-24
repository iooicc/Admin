package my.adminTest.test.model;

import org.beetl.sql.core.annotatoin.Table;

@Table(name = "sys_user")
public class Hellow extends my.admin.core.model.BaseModel{

	private String uid;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	
	
}
