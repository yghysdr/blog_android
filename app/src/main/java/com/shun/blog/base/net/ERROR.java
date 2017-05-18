package com.shun.blog.base.net;

/**
 * Created by yghysdr on 2017/5/17.
 */

public class Error {
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORD_ERROR = 1002;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1003;

    /**
     * 证书出错
     */
    public static final int SSL_ERROR = 1005;

    /**
     * 连接超时
     */
    public static final int TIMEOUT_ERROR = 1006;
    /**
     * 没有网络
     */
    public static final int NO_NET_ERROR = 9999;
    /**
     * 没有指定主机
     */
    public static final int NO_HOST_ERROR = 1008;

    /**
     * 没有指定目录
     */
    public static final int NO_PATH_ERROR = 1009;
    public static final String NO_PATH_ERROR_PS = "没有指定目录";

    /**
     * 文件写入失败
     */
    public static final int WRITE_FILE_ERROR = 1010;
    public static final String WRITE_FILE_ERROR_PS = "文件写入失败";
}
