package com.shun.blog.ui.home.contract;

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

        void onFailed();
    }

    public interface Presenter {
        void requestData(int page, int pageSize);

        boolean haveMore();
    }

    public interface Model {
        Observable<BaseResponse<List<HomeBean>>> requestData(int page, int pageSize);
    }

}