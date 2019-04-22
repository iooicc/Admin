package my.admin.framework.event.listener;

import java.util.Map;

import org.beetl.sql.core.SQLManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import my.admin.framework.util.ConfigUtil;
import my.admin.model.SysRole;
import my.admin.model.SysUserRole;
import my.common.event.Event;

@Component
public class AccountNewEventListener {
	private final static Logger LOG = LoggerFactory.getLogger(AccountNewEventListener.class);
	@Autowired
	SQLManager sqlManager;
	
	@EventListener(condition = "#event.topic=='account' && #event.tag=='new'")
	@Async
	public void onNew(Event event) {
		try {
			Map<String, Object> paras = MapUtil.newHashMap();
			
			byte[] body = event.getBody();
			String userCode = ObjectUtil.unserialize(body);
			LOG.info("AccountNewEventListener onNew start.[{}]", userCode);
			if(StrUtil.isBlank(userCode)) {
				LOG.error("userCode is blank.");
				return;
			}
			
			String roleCodes = ConfigUtil.getConfigString("sys.account.registerUser.roleList");
			String[] roleList = null;
			if(StrUtil.isBlank(roleCodes)
				|| (roleList=StrUtil.split(roleCodes, ","))==null
				|| roleList.length==0
			) {
				LOG.error("sys.account.registerUser.roleList is blank.");
				return;
			}
			
			for(int i=0;i<roleList.length;i++) {
				String roleCode = roleList[i];
				SysRole role = sqlManager.single(SysRole.class, roleCode);
				if(role==null) {
					continue;
				}
				
				SysUserRole sysUserRole = new SysUserRole();
				sysUserRole.setUserCode(userCode);
				sysUserRole.setRoleCode(roleCode);
				sqlManager.insertTemplate(sysUserRole);
				LOG.debug("[{}]roleCode={}", i, roleCode);
			}
		} catch (Exception e) {
		} finally {
			LOG.info("AccountNewEventListener onNew end.");
		}
		
		
	}
}