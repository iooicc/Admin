package my.common.wechat.api;

import cn.hutool.http.HttpRequest;
import my.common.wechat.ApiResult;

public class TemplateMsgApi {
	
	private static String sendApiUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
	
	/**
	 * 发送模板消息
	 * @param jsonStr json字符串
	 * @return {ApiResult}
	 */
	public static ApiResult send(String jsonStr) {
		String jsonResult = HttpRequest
			.get(sendApiUrl+AccessTokenApi.getAccessToken().getAccessToken())
			.form(jsonStr)
			.execute()
			.body();
		return new ApiResult(jsonResult);
	}
}
