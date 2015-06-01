package com.toread.common.util;
public class QueryUtils {
    public static String processSqlQueryLike(String string){
        if(org.springframework.util.StringUtils.hasText(string)){
            string = string.replace("%","\\%").replace("_","\\_");
            string = "%"+string+"%";
        }
        return string;
    }
}
