package io.yghysdr.article.contract;


import java.util.List;

import io.blog.modle.BaseResponse;
import io.blog.modle.bean.Article;
import io.reactivex.Observable;
import io.yghysdr.common.ICommonListView;

/**
 * Created by yghysdr on 2017/4/27.
 */

public class ArticleListContract {

    public interface View {
        void refreshTheme();
    }

    public interface Presenter {
        void requestList(ICommonListView commonListView, int page, int pageSize, int articleType);
    }

    public interface Model {
        Observable<BaseResponse<List<Article>>> requestData(int type, int page, int pageSize);
    }

}