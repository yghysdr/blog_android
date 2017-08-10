package com.shun.blog.bean;

import com.yghysdr.srecycleview.BaseBean;

import java.util.List;

/**
 * Created by yghysdr on 2017/6/29.
 */

public class Archive extends BaseBean{
    public long timestamp;
    public List<Article> articleList;
}
