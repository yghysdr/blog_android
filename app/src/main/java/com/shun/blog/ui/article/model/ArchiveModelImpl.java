package com.shun.blog.ui.article.model;

import com.shun.blog.api.AppWebClient;
import com.shun.blog.api.HostType;
import com.shun.blog.base.ui.BaseModel;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.Archive;
import com.shun.blog.ui.article.contract.ArchiveContract;

import java.util.List;

import rx.Observable;

/**
 * Created by yghysdr on 2017/06/29
 */

public class ArchiveModelImpl extends BaseModel
        implements ArchiveContract.Model {

    @Override
    public Observable<BaseResponse<List<Archive>>> requestData(
            int page, int pageSize) {
        return AppWebClient
                .getApiStores(HostType.BLOG)
                .getArchive();
    }
}