package io.yghysdr.article.contract;

import java.util.List;

import io.blog.res.BaseResponse;
import io.blog.res.bean.Archive;
import io.reactivex.Observable;
import io.yghysdr.blog.common.ICommonListView;


/**
 * Created by yghysdr on 2017/6/29.
 */

public class ArchiveContract {

    public interface View {
    }

    public interface Presenter {
        void getArchiveList(ICommonListView iCommonListView, int page, int pageSize);
    }

    public interface Model {
        Observable<BaseResponse<List<Archive>>> requestData(int page, int pageSize);
    }

}