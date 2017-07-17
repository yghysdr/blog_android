package com.shun.blog.ui.home.contract;

import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.Tag;

import java.util.List;

import rx.Observable;

/**
 * Created by yghysdr on 2017/7/4.
 */

public class TagContract {

    public interface View {
        void addTags(List<Tag> tags);

        void refreshTheme();
    }

    public interface Presenter {
        void requestTag();
    }

    public interface Model {
        Observable<BaseResponse<List<Tag>>> requestData();
    }

}