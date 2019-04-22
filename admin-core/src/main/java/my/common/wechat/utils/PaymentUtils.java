package my.common.wechat.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import my.common.codec.EncodeUtils;

public class PaymentUtils {

    /**
     * 组装签名的字段
     * @param params 参数
     * @param urlEncoder 是否urlEncoder
     * @return String
     */
    public static String packageSign(Map<String, String> params, boolean urlEncoder) {
        // 先将参数以其参数名的字典序升序进行排序
        TreeMap<String, String> sortedParams = new TreeMap<String, String>(params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Entry<String, String> param : sortedParams.entrySet()) {
            String value = param.getValue();
            if (StrUtil.isBlank(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=");
            if (urlEncoder) {
                try { value = urlEncode(value); } catch (UnsupportedEncodingException e) {}
            }
            sb.append(value);
        }
        return sb.toString();
    }

    /**
     * urlEncode
     * @param src 微信参数
     * @return String
     * @throws UnsupportedEncodingException 编码错误
     */
    public static String urlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, "UTF-8").replace("+", "%20");
    }

    /**
     * 生成签名
     * @param params 参数
     * @param paternerKey 支付密钥
     * @return sign
     */
    public static String createSign(Map<String, String> params, String paternerKey) {
        // 生成签名前先去除sign
        params.remove("sign");
        String stringA = packageSign(params, false);
        String stringSignTemp = stringA + "&key=" + paternerKey;
        return EncodeUtils.md5Hex(stringSignTemp).toUpperCase();
    }

    /**
     * 支付异步通知时校验sign
     * @param params 参数
     * @param paternerKey 支付密钥
     * @return {boolean}
     */
    public static boolean verifyNotify(Map<String, String> params, String paternerKey){
        String sign = params.get("sign");
        String localSign = createSign(params, paternerKey);
        return sign.equals(localSign);
    }

    /**
     * 微信下单，map to xml
     * @param params 参数
     * @return String
     */
    public static String toXml(Map<String, String> params) {
        StringBuilder xml = new StringBuilder();
        xml.append("<xml>");
        for (Entry<String, String> entry : params.entrySet()) {
            String key   = entry.getKey();
            String value = entry.getValue();
            // 略过空值
            if (StrUtil.isBlank(value)) continue;
            xml.append("<").append(key).append(">");
                xml.append(entry.getValue());
            xml.append("</").append(key).append(">");
        }
        xml.append("</xml>");
        return xml.toString();
    }

    /**
     * 针对支付的xml，没有嵌套节点的简单处理
     * @param xmlStr xml字符串
     * @return map集合
     */
    public static Map<String, String> xmlToMap(String xmlStr) {
    	Map<String, Object> map1 = XmlUtil.xmlToMap(xmlStr);
    	if(map1==null||map1.size()==0) {
    		return new HashMap<String, String>();
    	}
    	
    	Map<String, String> map2 = new HashMap<String, String>();
    	Iterator<String> ite = map1.keySet().iterator();
    	while(ite.hasNext()) {
    		String k = ite.next();
    		String v = StrUtil.toString(map1.get(k));
    		
    		map2.put(k, v);
    	}
    	
        return map2;
    }

}
