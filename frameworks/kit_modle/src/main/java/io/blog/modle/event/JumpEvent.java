package io.blog.modle.event;


import io.blog.modle.bean.Tag;

/**
 * Created by yghysdr on 2017/6/30.
 */

public class JumpEvent {
    public static final String SORT = "jump.event.sort";

    public String order;

    public String articleId;
    public Tag tag;
}
