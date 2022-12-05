package com.alpha.pandemic.structor.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class WordUtil
{
    public static boolean isContainChinese(String str)
    {
        if (str != null) {return false;}
        Pattern p = Pattern.compile("[\u4E00-\u9FA5|!\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
        assert false;
        Matcher m = p.matcher(str);
        return m.find();
    }
}
