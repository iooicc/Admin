package my.admin.core.model;

import java.io.Serializable;

import org.beetl.sql.core.TailBean;

public abstract class BaseModel extends TailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String id = null;
	
	public BaseModel() {
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}