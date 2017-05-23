package com.shun.blog.ui.user.view;

import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shun.blog.Jump;
import com.shun.blog.R;
import com.shun.blog.app.AppConstants;
import com.shun.blog.base.rx.RxBus;
import com.shun.blog.base.ui.BaseFragment;
import com.shun.blog.bean.User;
import com.shun.blog.event.ThemeEvent;
import com.shun.blog.ui.user.presenter.UserPresenterImpl;
import com.shun.blog.utils.SPUtils;
import com.shun.blog.utils.ThemeUtil;
import com.shun.blog.utils.UserData;
import com.shun.blog.weights.MyDialog;
import com.shun.blog.weights.MyToolbar;

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
    @BindView(R.id.user_ll)
    LinearLayout userLl;
    @BindView(R.id.user_exit_cv)
    CardView userExitCv;
    @BindView(R.id.user_info_cv)
    CardView userInfoCv;
    @BindView(R.id.user_night_tv)
    TextView userNightTv;
    @BindView(R.id.user_night_cv)
    CardView userNightCv;
    @BindView(R.id.user_tb)
    MyToolbar userTb;
    @BindView(R.id.user_night_sc)
    SwitchCompat userNightSC;
    @BindView(R.id.user_edit_tv)
    TextView userEditTv;
    @BindView(R.id.user_line_1_v)
    View userLine1V;
    @BindView(R.id.user_line_1_fl)
    FrameLayout userLine1Fl;
    @BindView(R.id.user_edit_cv)
    CardView userEditCv;

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
        initListener();
    }

    private void initListener() {
        userNightSC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mActivity.setTheme(R.style.AppThemeNight);
                    ThemeUtil.initTheme(mActivity, R.style.AppThemeNight);
                    refreshUI();
                    SPUtils.put(mActivity, AppConstants.THEME_NORMAL, false);
                } else {
                    mActivity.setTheme(R.style.AppTheme);
                    ThemeUtil.initTheme(mActivity, R.style.AppTheme);
                    SPUtils.put(mActivity, AppConstants.THEME_NORMAL, true);
                    refreshUI();
                }
            }
        });
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
        userNightSC.setChecked(!(Boolean) SPUtils.get(mActivity, AppConstants.THEME_NORMAL, true));
    }

    @OnClick({R.id.user_exit_cv,
            R.id.user_info_ll,
            R.id.user_edit_cv
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_exit_cv:
                exit();
                break;
            case R.id.user_info_ll:
                if (mCurUser == null) {
                    Jump.login(mActivity);
                }
                break;
            case R.id.user_edit_cv:
                Jump.edit(mActivity);
                break;
        }
    }

    /**
     * 刷新UI界面
     */
    private void refreshUI() {
        userExitCv.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));
        userInfoCv.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));
        userNightCv.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));
        userEditCv.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));
        userLine1Fl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bgItem));
        userLl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
        userTb.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
        userTb.setTitleTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
        userNick.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
        userDes.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtContent));
        userNightTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
        userExitTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtWarning));
        userEditTv.setTextColor(ThemeUtil.getColorId(ThemeUtil.txtTitle));
        userLine1V.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.lineHov));
        ThemeEvent event = new ThemeEvent();
        RxBus.getDefault().post(event);
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
