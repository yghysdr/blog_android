package com.github.yghysdr.theme;

import io.blog.res.BaseEvent;

public class ThemeEvent extends BaseEvent {
    public final static String CHANGE = "ThemeEvent.change";

    public ThemeEvent(String intent) {
        super(intent);
    }
}
