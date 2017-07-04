package com.shun.blog.ui.home.model;

import com.shun.blog.api.AppWebClient;
import com.shun.blog.api.HostType;
import com.shun.blog.base.ui.BaseModel;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.Tag;
import com.shun.blog.ui.home.contract.TagContract;

import java.util.List;

import rx.Observable;

/**
 * Created by yghysdr on 2017/07/04
 */

public class TagModelImpl extends BaseModel
        implements TagContract.Model {


    @Override
    public Observable<BaseResponse<List<Tag>>> requestData() {
        return AppWebClient.getApiStores(HostType.BLOG)
                .getTagData();
    }
}