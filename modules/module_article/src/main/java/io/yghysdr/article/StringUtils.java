package io.yghysdr.article;

import android.text.TextUtils;

/**
 * Created by yghysdr on 2016/12/28.
 */

public class StringUtils {
    /*
  * 判断字符串是否包含一些字符 indexOf
  */
    public static boolean indexOfString(String src, String dest) {
        boolean flag = false;
        if (src.indexOf(dest) != -1) {
            flag = true;
        }
        return flag;
    }

    public static String getArticleDes(String des) {
        if (TextUtils.isEmpty(des)) {
            return "";
        }
        des = des.trim();
        if (des.startsWith(">")) {
            des = des.replaceFirst(">", "");
        }
        return des.trim();
    }

    public static String getArticleContent(String content) {
        if (TextUtils.isEmpty(content)) {
            return "";
        }
        return content.trim();
    }
}
