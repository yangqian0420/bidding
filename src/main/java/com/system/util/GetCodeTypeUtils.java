package com.system.util;

public class GetCodeTypeUtils {

	
	//获取股票类型 sh上海 sz深圳
    public static String getCodeType(String code)
    {
        if ((code.startsWith("0") || code.startsWith("3")) && !code.equals("000001"))
        {
            return "sz";
        }
        else if (code.startsWith("6"))
        {
            return "sh";
        }
        else if (code.toLowerCase().startsWith(("sz")) || code.toLowerCase().startsWith(("sh")))
        {
            return code.toLowerCase().substring(0, 2);
        }
        else if (code.equals("000001")) {

            return "sh";
        }
        else if (code.equals("HSI"))
        {

            return "rt_hk";
        }
        else if (code.equals("399006"))
        {

            return "sz";
        }
        return "";
    }
}
