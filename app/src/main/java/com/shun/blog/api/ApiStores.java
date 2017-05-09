package com.shun.blog.api;

import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.HomeBean;
import com.shun.blog.bean.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yghysdr on 16/11/26.
 * 存放网络请求相关的url
 */

public interface ApiStores {


    @POST("login")
    Observable<BaseResponse<User>> login(@Body User user);

    @GET("list?type=0")
    Observable<BaseResponse<List<HomeBean>>> getHomeList(@Query("page") int page, @Query("size") int size);

}
