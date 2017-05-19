package com.shun.blog.api;

import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.ArticleBean;
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


    int list_home = 0;
    int list_android = 1;
    int list_web = 2;

    @GET("list")
    Observable<BaseResponse<List<ArticleBean>>> getHomeList(
            @Query("type") int type,
            @Query("page") int page,
            @Query("size") int size
    );

}
