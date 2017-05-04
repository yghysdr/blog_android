package com.shun.blog.bean;

import com.shun.blog.base.BaseBean;

/**
 * Created by yghysdr on 2017/4/26.
 */

public class User extends BaseBean {
    public String phone;
    public String token;
    public String uid;
    public String avatar;
    public String password;

    @Override
    public String toString() {
        return "User{" +
                "phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", uid='" + uid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
