package com.shun.blog.utils;

/**
 * Created by yghysdr on 2017/1/5.
 */

public class MoneyConvert {

    public static String getMoney(double temp) {
        int tempInt = (int) (temp * 10000);
        if (tempInt % 10000 == 0) {
            return ((int) temp) + "";
        } else {
            return temp + "";
        }
    }
}
