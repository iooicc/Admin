package my.adminTest.test.model;

import org.beetl.sql.core.annotatoin.Table;

@Table(name = "user")
public class Hellow extends my.admin.core.model.BaseModel{

	private String uid;
	private String data;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
