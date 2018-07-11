package io.yghysdr.article;

import java.util.List;

import io.blog.modle.BaseResponse;
import io.blog.modle.bean.Archive;
import io.blog.modle.bean.Article;
import io.blog.modle.bean.Tag;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IArticleApi {

    String BASE_ARTICLE_URL = "http://www.yghysdr.cn/article#/";

    @GET("api/list")
    Observable<BaseResponse<List<Article>>> getHomeList(
            @Query("type") int type,
            @Query("page") int page,
            @Query("size") int size
    );

    @GET("api/article")
    Observable<BaseResponse<Article>> getArticle(
            @Query("id") int id
    );

    @GET("api/archive")
    Observable<BaseResponse<List<Archive>>> getArchive();


    @GET("api/label/list")
    Observable<BaseResponse<List<Tag>>> getTagData();
}
