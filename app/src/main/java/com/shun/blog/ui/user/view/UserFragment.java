package com.shun.blog.ui.user.view;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.user.presenter.UserPresenterImpl;


public class UserFragment extends BaseFragment<UserPresenterImpl> {


    public UserFragment() {
        // Required empty public constructor
        mReUse = true;
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_user;
    }

}
