package com.shun.blog.ui.article.model;

import com.shun.blog.api.AppWebClient;
import com.shun.blog.api.HostType;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.ArticleBean;

import rx.Observable;

/**
 * Created by yghysdr on 2017/04/27
 */

public class ArticleModel {

    public Observable<BaseResponse<ArticleBean>> requestData(int articleId) {
        return AppWebClient
                .getApiStores(HostType.BLOG)
                .getArticle(articleId);
    }

}