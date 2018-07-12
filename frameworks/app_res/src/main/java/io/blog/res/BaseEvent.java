package io.blog.res;

/**
 * Event基础类
 */

public class BaseEvent {

    public BaseEvent(String intent) {
        this.intent = intent;
    }

    public String intent;
}
