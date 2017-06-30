package com.shun.blog.ui.article.contract;

import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.Archive;

import java.util.List;

import rx.Observable;

/**
 * Created by yghysdr on 2017/6/29.
 */

public class ArchiveContract {

    public interface View {
    }

    public interface Presenter {
    }

    public interface Model {
        Observable<BaseResponse<List<Archive>>> requestData(int page, int pageSize);
    }

}