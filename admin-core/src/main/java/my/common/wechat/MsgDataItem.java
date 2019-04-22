package my.common.wechat;

public class MsgDataItem {
	public String value = "";
	public String color = "#000";
	
	public MsgDataItem(String value, String color) {
		this.value = value;
		this.color = color;
	}
	
	public MsgDataItem(String value) {
		this.value = value;
	}
}