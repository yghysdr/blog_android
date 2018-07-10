package com.github.yghysdr.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;



/**
 * 统一处理错误。
 */
public class NetExceptionHandler implements INetExceptionHandler {


    /**
     * 未知错误
     */
    public static final int ERROR_UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int ERROR_PARSE = 1001;
    /**
     * 网络错误
     */
    public static final int ERROR_NETWORK = 1002;
    /**
     * 协议出错
     */
    public static final int ERROR_HTTP = 1003;
    /**
     * 证书出错
     */
    public static final int ERROR_SSL = 1005;

    /**
     * 连接超时
     */
    public static final int ERROR_TIMEOUT = 1006;
    /**
     * 没有网络
     */
    public static final int ERROR_NO_NET = 9999;
    /**
     * 没有指定主机
     */
    public static final int ERROR_NO_HOST = 1008;


    @Override
    public HttpException handleException(Throwable e) {
        HttpException ex = new HttpException(e);
        if (!isNetworkAvailable(HttpConfiguration.getBuilder().getContext())) {
            ex.code = ERROR_NO_NET;
            ex.message = "没有网络，请检查网络设置";
            return ex;
        }
        if (e instanceof retrofit2.HttpException) {
            retrofit2.HttpException httpException = (retrofit2.HttpException) e;
            ex.code = ERROR_HTTP;
            switch (httpException.code()) {
                case NOT_FOUND:
                    ex.message = "资源不存在";
                    break;
                case UNAUTHORIZED:
                    ex.message = "用户未认证";//用户未认证
                    break;
                case FORBIDDEN:
                    ex.message = "用户无权限访问该资源";
                    break;
                case REQUEST_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    ex.message = "请求超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    ex.message = "服务器失联了";
                    break;
                case SERVICE_UNAVAILABLE:
                    ex.message = "服务器失联了";
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof MalformedJsonException
                || e instanceof ParseException) {
            ex.code = ERROR_PARSE;
            ex.message = "解析错误";//
            return ex;
        } else if (e instanceof ConnectException) {
            ex.code = ERROR_NETWORK;
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException
                || e instanceof javax.net.ssl.SSLException) {
            ex.code = ERROR_SSL;
            ex.message = "证书验证失败";//
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex.code = ERROR_TIMEOUT;
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex.code = ERROR_TIMEOUT;
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex.code = ERROR_NO_HOST;
            ex.message = "服务器失联了";//没有指定主机
            return ex;
        } else {
            if (BuildConfig.DEBUG) {
                throw new RuntimeException(e);
            } else {
                ex.code = ERROR_UNKNOWN;
                ex.message = "服务器出了点小差";
                return ex;
            }
        }
    }

    /**
     * 测试网络是否可用
     */
    private static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

}

