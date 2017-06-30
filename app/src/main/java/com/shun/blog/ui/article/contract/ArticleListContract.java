package com.shun.blog.ui.article.contract;

import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.ArticleBean;

import java.util.List;

import rx.Observable;

/**
 * Created by yghysdr on 2017/4/27.
 */

public class ArticleListContract {

    public interface View {

        void refreshTheme();
    }

    public interface Presenter {

    }

    public interface Model {
        Observable<BaseResponse<List<ArticleBean>>> requestData(int type, int page, int pageSize);
    }

}