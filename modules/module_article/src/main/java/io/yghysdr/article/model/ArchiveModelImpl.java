package io.yghysdr.article.model;

import java.util.List;

import io.blog.modle.BaseResponse;
import io.blog.modle.bean.Archive;
import io.reactivex.Observable;
import io.yghysdr.article.contract.ArchiveContract;


/**
 * Created by yghysdr on 2017/06/29
 */

public class ArchiveModelImpl
        implements ArchiveContract.Model {

    @Override
    public Observable<BaseResponse<List<Archive>>> requestData(int page, int pageSize) {
        return null;
    }
}