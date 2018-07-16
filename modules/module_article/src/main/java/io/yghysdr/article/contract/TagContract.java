package io.yghysdr.article.contract;


import java.util.List;

import io.blog.res.BaseResponse;
import io.blog.res.bean.Tag;
import io.reactivex.Observable;

/**
 * Created by yghysdr on 2017/7/4.
 */

public class TagContract {

    public interface View {
        void addTags(List<Tag> tags);
    }

    public interface Presenter {
        void requestTag(View view);
    }

    public interface Model {
        Observable<BaseResponse<List<Tag>>> requestData();
    }

}