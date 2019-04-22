package my.admin.framework.service;

import org.beetl.sql.core.engine.PageQuery;

import my.admin.core.service.BaseService;
import  my.admin.model.MsgInnerRecord;

public interface MsgInnerRecordService extends BaseService<MsgInnerRecord> {
	void page(PageQuery query);
	
	/**
	 * 
	 * @param receiveUserCode 接受者用户编码
	 * @param receiveUserName 接受者用户姓名
	 * @param msgTitle 消息标题
	 * @param msgContent 消息内容
	 * @param contentLevel 内容级别（1普通 2一般 3紧急）
	 */
	public void sendSySMsg(String receiveUserCode,String receiveUserName,String msgTitle,String msgContent,String contentLevel);
	
}
