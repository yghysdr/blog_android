package com.shun.blog.ui.home.model;

import com.shun.blog.api.AppWebClient;
import com.shun.blog.api.HostType;
import com.shun.blog.base.ui.BaseModel;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.HomeBean;
import com.shun.blog.ui.home.contract.HomeContract;

import java.util.List;

import rx.Observable;

/**
 * Created by yghysdr on 2017/04/27
 */

public class HomeModelImpl extends BaseModel
        implements HomeContract.Model {


    @Override
    public Observable<BaseResponse<List<HomeBean>>> requestData(int page, int pageSize) {
        return AppWebClient.getApiStores(HostType.BLOG).getHomeList(page, pageSize);
    }

}