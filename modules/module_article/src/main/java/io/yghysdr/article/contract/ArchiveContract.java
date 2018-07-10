package io.yghysdr.article.contract;

import java.util.List;

import io.blog.modle.BaseResponse;
import io.blog.modle.bean.Archive;
import io.reactivex.Observable;
import io.yghysdr.common.ICommonListView;


/**
 * Created by yghysdr on 2017/6/29.
 */

public class ArchiveContract {

    public interface View {
        void refreshTheme();
    }

    public interface Presenter {
        void getArchiveList(ICommonListView iCommonListView, int page, int pageSize);
    }

    public interface Model {
        Observable<BaseResponse<List<Archive>>> requestData(int page, int pageSize);
    }

}