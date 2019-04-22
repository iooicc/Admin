package my.common.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String PROPERTY_TAGS = "TAGS";

    private String topic;
    private Map<String, String> properties;
    private byte[] body;

    public Message() {
    }
    
    public Message(String topic, String tags, byte[] body) {
    	this.topic = topic;
        this.body = body;

        if (tags != null && tags.length() > 0)
            this.setTags(tags);
    }
    
    void putProperty(final String name, final String value) {
        if (null == this.properties) {
            this.properties = new HashMap<String, String>();
        }

        this.properties.put(name, value);
    }
    
    public String getProperty(final String name) {
        if (null == this.properties) {
            this.properties = new HashMap<String, String>();
        }

        return this.properties.get(name);
    }
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
    
    public String getTags() {
    	return this.getProperty(PROPERTY_TAGS);
    }
    
    public void setTags(String tags) {
    	this.putProperty(PROPERTY_TAGS, tags);
    }
}
