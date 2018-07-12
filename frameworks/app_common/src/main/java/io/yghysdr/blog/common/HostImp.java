package io.yghysdr.blog.common;

import com.github.yghysdr.http.IHost;

public class HostImp implements IHost {

    final String BLOG = "http://www.yghysdr.cn/";

    @Override
    public String getBaseServerUrl(int type) {
        return BLOG;
    }
}
