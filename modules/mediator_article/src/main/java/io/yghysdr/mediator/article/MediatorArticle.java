package io.yghysdr.mediator.article;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class MediatorArticle {
    public static Fragment getArticleListFragment() {
        return (Fragment) ARouter
                .getInstance()
                .build(IConstantArticle.ARTICLE_FRAGMENT_ARTICLE)
                .navigation();
    }

    public static Fragment getTagFragment() {
        return (Fragment) ARouter
                .getInstance()
                .build(IConstantArticle.ARTICLE_FRAGMENT_TAG)
                .navigation();
    }

    public static Fragment getArchiveListFragment() {
        return (Fragment) ARouter
                .getInstance()
                .build(IConstantArticle.ARTICLE_FRAGMENT_ARCHIVE)
                .navigation();
    }

    public static void startArticleActivity(String articleId, String title) {
        ARouter.getInstance()
                .build(IConstantArticle.ARTICLE_ACTIVITY_ARTICLE)
                .withSerializable(IConstantArticle.ARG_ARTICLE_DETAIL_URL, articleId)
                .withSerializable(IConstantArticle.ARG_ARTICLE_DETAIL_TITLE, title)
                .navigation();
    }

}
