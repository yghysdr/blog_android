package com.shun.blog.ui.home.presenter;

import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.rx.RxSchedulers;
import com.shun.blog.base.ui.BasePresenter;
import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.bean.Tag;
import com.shun.blog.event.ThemeEvent;
import com.shun.blog.ui.home.contract.TagContract;
import com.shun.blog.ui.home.model.TagModelImpl;
import com.shun.blog.ui.home.view.TagFragment;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by yghysdr on 2017/07/04
 */

public class TagPresenterImpl extends BasePresenter<TagFragment, TagModelImpl>
        implements TagContract.Presenter {

    @Override
    public void requestTag() {
        mMode.requestData()
                .compose(RxSchedulers.<BaseResponse<List<Tag>>>io_main())
                .subscribe(new Action1<BaseResponse<List<Tag>>>() {
                    @Override
                    public void call(BaseResponse<List<Tag>> listBaseResponse) {
                        mView.addTags(listBaseResponse.data);
                    }
                });
    }

    @Override
    public void addRxBus() {
        super.addRxBus();
        mRxManage.addAsync(RxBus.getDefault()
                .toObservable(ThemeEvent.class)
                .subscribe(new Action1<ThemeEvent>() {
                    @Override
                    public void call(ThemeEvent themeEvent) {
                        mView.refreshTheme();
                    }
                }));
    }
}