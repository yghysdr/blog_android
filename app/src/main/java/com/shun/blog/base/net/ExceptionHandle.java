package com.shun.blog.base.net;

import android.net.ParseException;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;
import com.shun.blog.App;
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
        if (!NetUtil.isNetworkAvailable(App.instance)) {
            ex = new ResponseThrowable(e, Error.NO_NET_ERROR);
            ex.message = App.getResString(R.string.action_sign);
            return ex;
        }
        if (e instanceof retrofit2.adapter.rxjava.HttpException) {
            retrofit2.adapter.rxjava.HttpException httpException = (retrofit2.adapter.rxjava.HttpException) e;
            ex = new ResponseThrowable(e, Error.HTTP_ERROR);
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
            ex = new ResponseThrowable(e, Error.PARSE_ERROR);
            ex.message = "解析错误";
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(e, Error.NETWORD_ERROR);
            ex.message = "连接失败";
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponseThrowable(e, Error.SSL_ERROR);
            ex.message = "证书验证失败";
            return ex;
        } else if (e instanceof ConnectTimeoutException) {
            ex = new ResponseThrowable(e, Error.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof java.net.SocketTimeoutException) {
            ex = new ResponseThrowable(e, Error.TIMEOUT_ERROR);
            ex.message = "连接超时";
            return ex;
        } else if (e instanceof UnknownHostException) {
            ex = new ResponseThrowable(e, Error.NO_HOST_ERROR);
            ex.message = "没有指定主机";
            return ex;
        } else if (e instanceof ResponseThrowable) {//文件下载失败
            ex = (ResponseThrowable) e;
            if (TextUtils.isEmpty(ex.message)) {
                ex.message = "下载失败";
            }
            if (ex.code == 0) {
                ex.code = Error.HTTP_ERROR;
            }
            return ex;
        } else {
            throw new RuntimeException(e);
        }
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

