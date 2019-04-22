package my.admin.framework.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.admin.core.service.BaseServiceImpl;
import my.admin.framework.dao.MsgInnerRecordDao;
import my.admin.framework.util.UserUtil;
import my.admin.model.MsgInner;
import my.admin.model.MsgInnerRecord;
import my.admin.model.SysUser;
import my.common.idgen.IdGenerate;

@Service
public class MsgInnerRecordServiceImpl extends BaseServiceImpl<MsgInnerRecord, MsgInnerRecordDao> implements MsgInnerRecordService {
	
	
	private final static Logger log = LoggerFactory.getLogger(MsgInnerRecordServiceImpl.class); 
	
	@Autowired
	SQLManager sqlManager;
	@Autowired
	MsgInnerService msgInnerService;
	
	@Override
	public void page(PageQuery page) {
		
		Map<String, Object> paras = new HashMap<String, Object>();
		
		pageQuery("msgInnerRecord.findList", MsgInnerRecord.class, page);
		
		List<MsgInnerRecord> list = page.getList();
		if(list!=null && list.size()>0) {			
			for(int i=0;i<list.size();i++) {
				MsgInnerRecord ii = list.get(i);
				String msgInnerId = ii.getMsgInnerId();
				MsgInner single = msgInnerService.single(MsgInner.class, msgInnerId);
				ii.setMsgInner(single);	
				String id=ii.getId();
				paras.clear();
				paras.put("id", id);
				//修改推送字段
				executeUpdate(
						"update sys_msg_inner_record"
						+" set is_push=1"
						+" where id=#id#"
						,paras
					);
			}					
		}
	}
	
	
	/**
	 * 发送系统消息
	 * receiveUserCode:接受者用户编码
	 * receiveUserName:接受者用户姓名
	 * msgTitle:消息标题
	 * msgContent:消息内容
	 * contentLevel:内容级别（1普通 2一般 3紧急）
	 */
	@Async("tasksExecutor")	
	@Transactional
	@Override	
	public void sendSySMsg(String receiveUserCode,String receiveUserName,String msgTitle,String msgContent,String contentLevel) {
		log.debug(" sendSySMsg start...");
		String msgInnerId = IdGenerate.nextId();		
		MsgInner mi=new MsgInner();
		mi.setId(msgInnerId);
		mi.setMsgTitle(msgTitle);
		mi.setMsgContent(msgContent);
		mi.setContentLevel(contentLevel);
		mi.setContentType("4");
		mi.setSendDate(new Date());
		mi.setSendUserName("系统通知");
		mi.setSendUserCode("0");
		mi.setStatus("0");
		mi.setCreateDate(new Date());
		mi.setReceiveType("1");
		mi.setNotifyTypes("PC");
		mi.setReceiveCodes(receiveUserCode);
		mi.setReceiveNames(receiveUserName);
		msgInnerService.insertTemplate(mi);
		
		
		String msgInnerRecordId = IdGenerate.nextId();		
		//创建接受者
		MsgInnerRecord mir=new MsgInnerRecord();		
		mir.setId(msgInnerRecordId);
		mir.setReadStatus("1");
		mir.setReceiveUserCode(receiveUserCode);
		mir.setReceiveUserName(receiveUserName);
		mir.setCreateDate(new Date());
		mir.setMsgInnerId(msgInnerId);		
		insertTemplate(mir);
		
		
		log.debug(" sendSySMsg end...");	
		
	}
	
	
	
	
}