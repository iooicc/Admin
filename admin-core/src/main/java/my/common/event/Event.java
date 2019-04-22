package my.common.event;

import org.springframework.context.ApplicationEvent;

public class Event extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	
	private Message message;

	public Event(Message message) {
		super(message);
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	public String getTopic() {
		if(message==null) {
			return null;
		}
		return message.getTopic();
	}
	
	public String getTag() {
		if(message==null) {
			return null;
		}
		return message.getTags();
	}
	
	public byte[] getBody() {
		if(message==null) {
			return null;
		}
		return message.getBody();
	}

}