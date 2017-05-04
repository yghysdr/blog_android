package com.shun.blog.base;

/**
 * Created by yghysdr on 16/11/26.
 * 基本的数据模型。
 */

public class BaseResponse<D> {
    public D data;
    public int code;
    public String msg;
    public boolean haveMore;
}
