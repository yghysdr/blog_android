package com.shun.blog.base.net;

import android.net.ParseException;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.shun.blog.BaseApplication;
import com.shun.blog.R;
import com.shun.blog.utils.NetUtil;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

/**
 * 统一处理错误。
 */
public class ExceptionHandle {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable ex;
        if (!NetUtil.isNetworkAvailable(BaseApplication.instance)) {
            ex = new ResponseThrowable(e, ERROR.NO_NET_ERROR);
            ex.message = BaseApplication.getResString(R.string.action_sign_in);
            return ex;
        }
        if (e instanceof retrofit2.adapter.rxjava.HttpException) {
            retrofit2.adapter.rxjava.HttpException httpException = (retrofit2.adapter.rxjava.HttpException) e;
            ex = new ResponseThrowable(e, ERROR.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.message = "网络错误";
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof MalformedJsonException
                || e instanceof ParseException) {
            ex = new ResponseThrowable(e, ERROR.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponseThrowable(e, ERROR.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ResponseThrowable(e, ERROR.NO_HOST_ERROR);
            ex.message = "没有指定主机";
            return ex;
        } else if (e instanceof ResponseThrowable) {//文件下载失败
            ex = (ResponseThrowable) e;
            if (TextUtils.isEmpty(ex.message)) {
                ex.message = "下载失败";
            }
            if (ex.code == 0) {
                ex.code = ERROR.HTTP_ERROR;
            }
            return ex;
        } else {
            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
            ex.message = "未知错误";
            return ex;
        }
    }


    /**
     * 约定异常
     */
    class ERROR {
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
        public static final int NO_NET_ERROR = 1007;
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


    public static class ResponseThrowable extends Exception {
        public int code;
        public String message;

        public ResponseThrowable(Throwable throwable, int code) {
            super(throwable);
            this.code = code;
        }
    }
}

