package com.shun.blog.api;

/**
 * Created by HX on 2016/10/13.
 * 存放主机host。
 */

public class HostType {
    public static int TYPE_COUNT = 3;

    /**
     * 天气host
     */
    public static final String HOST_WEATHER = "http://www.weather.com.cn/";
    public static final int TYPE_WEATHER = 1;

    /**
     * 豆瓣host
     */
    public static final String HOST_DOU_BAN = "https://api.douban.com/";
    public static final int TYPE_DOU_BAN = 2;

    public static final String HOST_WAN_DOU_JIA = "http://www.wandoujia.com/";
    public static final int TYPE_WAN_DOU_JIA = 3;


    public static String getHost(int type) {
        switch (type) {
            case TYPE_WAN_DOU_JIA:
                return HOST_WAN_DOU_JIA;
            case TYPE_DOU_BAN:
                return HOST_DOU_BAN;
            default:
                return HOST_WEATHER;
        }
    }

    public static int getType(String url) {
        if (url.contains(HOST_WAN_DOU_JIA)) {
            return TYPE_WAN_DOU_JIA;
        } else if (url.contains(HOST_DOU_BAN)) {
            return TYPE_COUNT;
        } else {
            return TYPE_WEATHER;
        }
    }

}
