package io.yghysdr.user.view;

import android.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.OnClick;
import io.blog.modle.bean.User;
import io.blog.modle.event.ThemeEvent;
import com.github.yghysdr.base.ui.BaseFragment;

import com.github.yghysdr.base.utils.PreUtils;
import io.yghysdr.common.AppConstants;
import io.yghysdr.common.Jump;
import io.yghysdr.common.RxBus;
import io.yghysdr.common.common.util.ThemeUtil;
import io.yghysdr.mediator.user.IContentUser;
import io.yghysdr.user.R;
import io.yghysdr.user.R2;
import io.yghysdr.user.presenter.UserProviderImp;


@Route(path = IContentUser.USER_FRAGMENT_USER)
public class UserFragment extends BaseFragment {

    @BindView(R2.id.user_avatar_iv)
    ImageView userAvatarIv;
    @BindView(R2.id.user_nick)
    TextView userNick;
    @BindView(R2.id.user_des)
    TextView userDes;
    @BindView(R2.id.user_exit_tv)
    TextView userExitTv;
    @BindView(R2.id.user_info_ll)
    LinearLayout userInfoLl;
    @BindView(R2.id.user_ll)
    LinearLayout userLl;
    @BindView(R2.id.user_exit_cv)
    CardView userExitCv;
    @BindView(R2.id.user_info_cv)
    CardView userInfoCv;
    @BindView(R2.id.user_night_tv)
    TextView userNightTv;
    @BindView(R2.id.user_night_cv)
    CardView userNightCv;
    @BindView(R2.id.user_night_sc)
    SwitchCompat userNightSC;
    @BindView(R2.id.user_edit_tv)
    TextView userEditTv;
    @BindView(R2.id.user_line_1_v)
    View userLine1V;
    @BindView(R2.id.user_line_1_fl)
    FrameLayout userLine1Fl;
    @BindView(R2.id.user_edit_cv)
    CardView userEditCv;

    private User mCurUser;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected void LazyLoad() {
//        initToolBar(getString(R.string.title_my));
        initUserInfo();
        initListener();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_user;
    }


    private void initListener() {
        userNightSC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mActivity.setTheme(R.style.AppThemeNight);
                    ThemeUtil.initTheme(mActivity, R.style.AppThemeNight);
                    refreshUI();
                    PreUtils.putBoolean(AppConstants.THEME_NORMAL, false);
                } else {
                    mActivity.setTheme(R.style.AppTheme);
                    ThemeUtil.initTheme(mActivity, R.style.AppTheme);
                    PreUtils.putBoolean(AppConstants.THEME_NORMAL, true);
                    refreshUI();
                }
            }
        });
    }

    public void initUserInfo() {
        mCurUser = UserProviderImp.getInstance().getUser();
        if (mCurUser == null) {
            userNick.setText("未登入");
            userDes.setText("登入查看");
            userExitCv.setVisibility(View.GONE);
        } else {
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
//            Glide.with(this)
//                    .load(mCurUser.avatar)
//                    .into(userAvatarIv);
        }
        userNightSC.setChecked(PreUtils.getBoolean(AppConstants.THEME_NORMAL, true));
    }

    @OnClick({R2.id.user_exit_cv,
            R2.id.user_info_ll,
            R2.id.user_edit_cv
    })
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.user_exit_cv) {
            exit();
        } else if (i == R.id.user_info_ll) {
            if (mCurUser == null) {
                Jump.login(getActivity());
            }
        } else if (i == R.id.user_edit_cv) {
            Jump.edit(getActivity());
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
        new AlertDialog.Builder(getActivity())
                .setTitle("确定退出？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    UserProviderImp.getInstance().clearUser();
                    initUserInfo();
                })
                .show();
    }

}
