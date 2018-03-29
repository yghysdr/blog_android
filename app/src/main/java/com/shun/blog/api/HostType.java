package com.shun.blog.api;

/**
 * Created by yghysdr on 2016/10/13.
 * 存放主机host。
 */

public class HostType {
    public static int TYPE_COUNT = 3;

    /**
     * 天气host
     */
//    public static final String HOST_BLOG = "http://www.yghysdr.cn/api/";
    public static final String HOST_BLOG = "http://127.0.0.1:5000/api/v1/";
    public static final int BLOG = 1;

    /**
     * 豆瓣host
     */
    public static final String HOST_DOU_BAN = "https://api.douban.com/";
    public static final int TYPE_DOU_BAN = 2;

    public static final String HOST_WAN_DOU_JIA = "http://www.wandoujia.com/";
    public static final int TYPE_WAN_DOU_JIA = 3;


    public static String getHost(int type) {
        switch (type) {
            case BLOG:
                return HOST_BLOG;
            case TYPE_WAN_DOU_JIA:
                return HOST_WAN_DOU_JIA;
            case TYPE_DOU_BAN:
                return HOST_DOU_BAN;
            default:
                return HOST_BLOG;
        }
    }
}
