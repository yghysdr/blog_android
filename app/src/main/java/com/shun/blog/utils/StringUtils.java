package com.shun.blog.utils;

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
}
