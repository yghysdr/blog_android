package com.shun.blog.event;

import com.shun.blog.bean.Tag;

/**
 * Created by yghysdr on 2017/6/30.
 */

public class JumpEvent {
    public static final String ARTICLE = "com.shun.blog.jump.event.article";
    public static final String SORT = "com.shun.blog.jump.event.sort";

    public String order;

    public int articleId;
    public Tag tag;
}
