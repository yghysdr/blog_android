package com.yghysdr.srecycleview;

/**
 * Created by yghysdr on 2017/5/8.
 */

public interface IFooterHolder {
    /**
     * 0 没有更多
     * 1 加载中
     *
     * @param status
     */
    void setStatus(int status);

    void getStatus();
}
