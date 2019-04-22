package my.utils;

import java.util.List;

public class ListUtil {
	/**
     * 是否包含字符串
     * @param str 验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */ 	
    public static boolean inString(String str, List<String> strs){
		if (str != null && strs != null){
        	for (String s : strs){
        		if (str.equals(s)){
        			return true;
        		}
        	}
    	}
    	return false;
    }
}
