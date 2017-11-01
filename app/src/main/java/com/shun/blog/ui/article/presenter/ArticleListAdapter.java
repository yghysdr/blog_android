package com.shun.blog.ui.article.presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;

import com.shun.blog.ui.article.view.ArticleListHolder;
import com.yghysdr.srecycleview.BaseHolder;
import com.yghysdr.srecycleview.BaseRVAdapter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArticleListAdapter extends BaseRVAdapter {

    public ArticleListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected BaseHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleListHolder(mContext, parent);
    }

    public void server() {
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };
        Observable<String> observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {

                    }
                });

        observable
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber);
        observable.subscribe(
                new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });

        int drawbaleRes = 0;
        final Drawable drawable = null;
        Observable
                .create(new Observable.OnSubscribe<Drawable>() {

                    @Override
                    public void call(Subscriber<? super Drawable> subscriber) {
                        subscriber.onNext(drawable);
                    }
                })
                .subscribeOn(Schedulers.immediate())

                .subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) {

                    }
                });
        Observable<String> observable1 = Observable.just("a", "b");
        String[] strings = {"a", "b"};
        Observable<String> observable2 = Observable.from(strings);
        observable2
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return null;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {

                    }
                });

    }

}