package my.admin.framework.event.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.ObjectUtil;
import my.common.event.Event;
import my.common.event.Message;

@Component
public class AccountEventProducer {
private final static Logger LOG = LoggerFactory.getLogger(AccountEventProducer.class);
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	public void onNew(String userCode) {
		
		Message message = new Message(
			"account"
			,"new"
			,ObjectUtil.serialize(userCode)
		);
		
		// 发布消息到达的事件，以便分发到每个tag的监听方法
    	publisher.publishEvent(new Event(message));
	}
}
