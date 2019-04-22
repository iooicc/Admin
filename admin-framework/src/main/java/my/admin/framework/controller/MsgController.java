package my.admin.framework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import my.admin.dto.Page;
import my.admin.framework.service.MsgInnerRecordService;
import my.admin.framework.service.MsgInnerService;
import my.admin.framework.util.UserUtil;
import my.admin.model.MsgInner;
import my.admin.model.MsgInnerRecord;
import my.admin.model.SysUser;

@Controller
@RequestMapping({ "/msg" })
public class MsgController {

	@Autowired
	MsgInnerService msgInnerService;
	
	@Autowired
	MsgInnerRecordService msgInnerRecordService;
		
	
	@RequestMapping(value = "info")
	public String info(String msgInnerRecordId, Model model) {
		
		SysUser user = UserUtil.getUser();
		Map<String, Object> paras = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		MsgInnerRecord msgInnerRecord = msgInnerRecordService.single(MsgInnerRecord.class, msgInnerRecordId);		
		String id=msgInnerRecord.getMsgInnerId();
		MsgInner msgInner = msgInnerService.single(MsgInner.class, id);
		//该条消息是否未读？
		if("1".equals(msgInnerRecord.getReadStatus())) {
			paras.put("msgInnerRecordId", msgInnerRecordId);
			msgInnerRecordService.executeUpdate(
					"update sys_msg_inner_record"
					+" set read_status=2"
					+" ,read_date= now()"
					+" where id=#msgInnerRecordId#"
					,paras
				);
		}
		data.put("msgInner", msgInner);
		data.put("msgInnerRecord", msgInnerRecord);		
		model.addAttribute("info", data);
		return "modules/framework/msg__info";
	}
	
	@RequestMapping({ "list_readT" })
	public String list_readT(MsgInnerRecord row, Model model) {
		model.addAttribute("row", row);
		return "modules/framework/msg__listreadT";
	}
	
	
	@RequestMapping({ "list_readF" })
	public String list_readF(MsgInnerRecord row, Model model) {
		model.addAttribute("row", row);
		return "modules/framework/msg__listreadF";
	}
	
	@RequestMapping({ "listData" })
	@ResponseBody
	public Page listData(
			MsgInnerRecord row,
		@RequestParam(defaultValue="1") Long pageNo
	) {
		
		SysUser user = UserUtil.getUser();
        row.setReceiveUserCode(user.getUserCode());
        
		PageQuery<MsgInnerRecord> query = new PageQuery<MsgInnerRecord>(pageNo, 20, row);
		msgInnerRecordService.page(query);
		
		return new Page(query);
	}
	
	
	@ResponseBody
	@RequestMapping({"unreadMsg"})
	public Page<MsgInnerRecord> unreadMsg(MsgInnerRecord row, @RequestParam(defaultValue="5") Integer pageSize){
		SysUser user = UserUtil.getUser();
        String userCode = user.getUserCode();
        row.setReceiveUserCode(userCode);
        row.setReadStatus("1");
		PageQuery<MsgInnerRecord> query = new PageQuery<MsgInnerRecord>(1,pageSize, row);
		msgInnerRecordService.page(query);	
	    return new Page(query);	
  }
	
	@ResponseBody
	@RequestMapping({"pullPoolMsg"})
	public List<MsgInnerRecord> pullPoolMsg(MsgInnerRecord row, @RequestParam(defaultValue="5") Integer pageSize){
		SysUser user = UserUtil.getUser();
        String userCode = user.getUserCode();
        Map<String, Object> paras = new HashMap<String, Object>();
        paras.put("userCode", userCode);
        List<MsgInnerRecord> list = msgInnerRecordService.execute(
        		"select"
				+" t1.*"
				+" from sys_msg_inner_record t1"
				+" where t1.receive_user_code=#userCode#"
				+" and t1.read_status=1"
				+" and t1.is_push <> 1"
				+" ORDER BY t1.create_date DESC"
				+" LIMIT 0,5"
        		,MsgInnerRecord.class
        		,paras
        );
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
				msgInnerRecordService.executeUpdate(
						"update sys_msg_inner_record"
						+" set is_push=1"
						+" where id=#id#"
						,paras
					);
				
			}
			//按照内容级别（1普通 2一般 3紧急）降序
			list.sort((MsgInnerRecord h1, MsgInnerRecord h2) -> h1.getMsgInner().getContentLevel().compareTo(h2.getMsgInner().getContentLevel()));
		}
        
	    return list;
	
  }
	

}
