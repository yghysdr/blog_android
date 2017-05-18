package com.shun.blog.ui.home.view;

import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.HomeBean;

import java.util.List;

import rx.Observable;

/**
 * Created by yghysdr on 2017/4/27.
 */

public class HomeContract {


    public interface View {
        void onSuccess(List<HomeBean> beanList);

        void onFailed(int errorNo, String errorMsg);
    }

    public interface Presenter {
        void requestData(int page, int pageSize);

        int haveMore();
    }

    public interface Model {
        Observable<BaseResponse<List<HomeBean>>> requestData(int page, int pageSize);
    }

}