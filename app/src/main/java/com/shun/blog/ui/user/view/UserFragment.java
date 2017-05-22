package com.shun.blog.ui.user.view;

import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shun.blog.Jump;
import com.shun.blog.R;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.bean.User;
import com.shun.blog.ui.user.presenter.UserPresenterImpl;
import com.shun.blog.utils.UserData;
import com.shun.blog.weights.MyDialog;

import butterknife.BindView;
import butterknife.OnClick;


public class UserFragment extends BaseFragment<UserPresenterImpl> {

    @BindView(R.id.user_avatar_iv)
    ImageView userAvatarIv;
    @BindView(R.id.user_nick)
    TextView userNick;
    @BindView(R.id.user_des)
    TextView userDes;
    @BindView(R.id.user_exit_tv)
    TextView userExitTv;
    @BindView(R.id.user_info_ll)
    LinearLayout userInfoLl;
    @BindView(R.id.user_exit_cv)
    CardView userExitCv;

    private User mCurUser;

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

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        initUserInfo();
    }


    public void initUserInfo() {
        mCurUser = UserData.getCurrentUser();
        if (mCurUser == null) {
            userNick.setText("未登入");
            userDes.setText("登入查看");
            userExitCv.setVisibility(View.GONE);
            return;
        }
        userExitCv.setVisibility(View.VISIBLE);
        if (TextUtils.isEmpty(mCurUser.nick)) {
            userNick.setText(getString(R.string.comm_nick));
        } else {
            userNick.setText(mCurUser.nick);
        }
        if (TextUtils.isEmpty(mCurUser.des)) {
            userDes.setText(getString(R.string.comm_des));
        } else {
            userDes.setText(mCurUser.des);
        }
    }

    @OnClick({R.id.user_exit_tv,
            R.id.user_info_ll
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_exit_tv:
                exit();
                break;
            case R.id.user_info_ll:
                if (mCurUser == null) {
                    Jump.login(mActivity);
                }
                break;
        }
    }

    public void exit() {
        new MyDialog.Builder(mActivity)
                .setTitle("确定退出？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserData.deleteCurrentUser();
                        initUserInfo();
                    }
                })
                .show();
    }
}
