package com.shun.blog.ui.user.view;

import android.view.View;
import android.widget.Button;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.ui.user.presenter.UserPresenterImpl;
import com.shun.blog.utils.UserData;

import butterknife.BindView;
import butterknife.OnClick;


public class UserFragment extends BaseFragment<UserPresenterImpl> {

    @BindView(R.id.user_exit_btn)
    Button userExitBtn;

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


    @OnClick({R.id.user_exit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_exit_btn:
                UserData.deleteCurrentUser();
                break;
        }
    }
}
