package com.shun.blog.api;

import com.shun.blog.base.BaseResponse;
import com.shun.blog.bean.User;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by yghysdr on 16/11/26.
 * 存放网络请求相关的url
 */

public interface ApiStores {
    @POST("login")
    Observable<BaseResponse<User>> login(@Body User user);

}
