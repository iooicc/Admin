package my.admin.framework.controller;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hutool.core.util.RandomUtil;
import my.common.config.Global;
import my.common.wechat.ApiConfig;
import my.common.wechat.api.ApiConfigKit;
import my.common.wechat.api.JsTicketApi;
import my.common.wechat.api.JsTicketApi.JsApiType;

@Controller
@RequestMapping("/wechat")
public class WeChatController {
	
	private void getApiConfig() {
		ApiConfig apiConfig = new ApiConfig(
			Global.getProperty("project.wechat.token")
    		, Global.getProperty("project.wechat.appId")
    		, Global.getProperty("project.wechat.appSecret")
    	);
		
		ApiConfigKit.putApiConfig(apiConfig);
	}
	
	
	
	
	
	@RequestMapping({"jsApiConfig"})
	@ResponseBody
	public Map jsApiConfig(
		String url
	) {
		
		getApiConfig();
		
		try {
			String jsapi_ticket = JsTicketApi.getTicket(JsApiType.jsapi).getTicket();
			String nonceStr = RandomUtil.randomString(32);
			Long timestamp = System.currentTimeMillis()/1000;
			String signature = getSignature(jsapi_ticket, nonceStr, timestamp, url);
			
			Map<String, Object> result = new LinkedHashMap<String, Object>();
			result.put("appId", ApiConfigKit.getAppId());
			result.put("timestamp", timestamp);
			result.put("nonceStr", nonceStr);
			result.put("signature", signature);
			
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	private String getSignature(String jsapi_ticket, String nonceStr, Long timestamp, String url) {
		String signature = "jsapi_ticket=" + jsapi_ticket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url;
		
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(signature.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch(Exception e) {
			signature = null;
		}
		return signature;
	}
	
	private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
