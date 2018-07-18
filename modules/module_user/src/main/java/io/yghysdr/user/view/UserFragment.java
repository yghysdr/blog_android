package io.yghysdr.user.view;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.yghysdr.base.BaseApp;
import com.github.yghysdr.base.BaseFragment;
import com.github.yghysdr.image.ImageLoadUtils;
import com.github.yghysdr.theme.ThemeEvent;
import com.github.yghysdr.theme.ThemeUtil;
import com.github.yghysdr.util.PreUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import io.blog.res.bean.User;
import io.yghysdr.mediator.login.MediatorLogin;
import io.yghysdr.mediator.user.IContentUser;
import io.yghysdr.mediator.user.MediatorUser;
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
    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    private User mCurUser;

    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override
    protected void LazyLoad() {
        initToolBar(toolbar, getString(R.string.title_my), false);
        initListener();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if (isVisible) {
            initUserInfo();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initUserInfo();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.user_fragment_user;
    }


    private void initListener() {
        userNightSC.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mActivity.setTheme(R.style.AppThemeNight);
                ThemeUtil.initTheme(mActivity, R.style.AppThemeNight);
                refreshUI();
                PreUtils.putBoolean(BaseApp.getContext(), ThemeUtil.THEME_NORMAL, false);
            } else {
                mActivity.setTheme(R.style.AppTheme);
                ThemeUtil.initTheme(mActivity, R.style.AppTheme);
                PreUtils.putBoolean(BaseApp.getContext(), ThemeUtil.THEME_NORMAL, true);
                refreshUI();
            }
        });
    }

    public void initToolBar(Toolbar toolbar, CharSequence title, boolean haveBack) {
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            toolbar.setTitle(title);
            if (haveBack) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    public void initUserInfo() {
        if (!MediatorLogin.getLoginProvider().isLogin()) {
            userNick.setText("未登入");
            userDes.setText("登入查看");
            userExitCv.setVisibility(View.GONE);
        } else {
            mCurUser = UserProviderImp.getInstance().getUser();
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
            ImageLoadUtils.load(getContext(), mCurUser.avatar, userAvatarIv);
        }
        userNightSC.setChecked(!PreUtils.getBoolean(BaseApp.getContext(), ThemeUtil.THEME_NORMAL, true));
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
                MediatorLogin.startLogin();
            }
        } else if (i == R.id.user_edit_cv) {
            if (mCurUser == null) {
                MediatorLogin.startLogin();
            }
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
        userNick.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtTitle));
        userDes.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtContent));
        userNightTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtTitle));
        userExitTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtWarning));
        userEditTv.setTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtTitle));
        userLine1V.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.lineHov));
        toolbar.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
        toolbar.setTitleTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtNav));

        ThemeEvent event = new ThemeEvent(ThemeEvent.CHANGE);
        EventBus.getDefault().post(event);
    }

    public void exit() {
        new AlertDialog.Builder(getActivity())
                .setTitle("确定退出？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog, which) -> {
                    MediatorLogin.getLoginProvider().exitLogin();
                    MediatorUser.getUserProvider().clearUser();
                    initUserInfo();
                })
                .show();
    }
}
