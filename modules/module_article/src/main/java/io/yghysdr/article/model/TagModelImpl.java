package io.yghysdr.article.model;

import java.util.List;

import io.blog.res.BaseResponse;
import io.blog.res.bean.Tag;
import io.reactivex.Observable;
import io.yghysdr.article.contract.TagContract;

/**
 * Created by yghysdr on 2017/07/04
 */

public class TagModelImpl implements TagContract.Model {

    @Override
    public Observable<BaseResponse<List<Tag>>> requestData() {
        return null;
    }
}