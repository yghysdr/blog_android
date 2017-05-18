package com.shun.blog.ui.user.view;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.ui.BaseActivity;
import com.shun.blog.bean.User;
import com.shun.blog.ui.user.contract.LoginContract;
import com.shun.blog.ui.user.presenter.LoginPresenterImpl;
import com.shun.blog.utils.MD5;
import com.shun.blog.utils.UserData;
import com.shun.blog.utils.Validation;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登入
 */
public class LoginActivity extends BaseActivity<LoginPresenterImpl>
        implements LoginContract.View {

    @BindView(R.id.phone)
    AutoCompleteTextView mPhoneView;
    @BindView(R.id.password)
    EditText mPasswordView;
    private User mTempUser;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        super.init();
        mPhoneView.setText(UserData.getExitPhone());
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }

    private void attemptLogin() {
        mPhoneView.setError(null);
        mPasswordView.setError(null);
        String email = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mPhoneView.setError(getString(R.string.error_field_required));
            focusView = mPhoneView;
            cancel = true;
        } else if (!Validation.isPhone(email)) {
            mPhoneView.setError(getString(R.string.error_invalid_email));
            focusView = mPhoneView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            mTempUser = new User();
            mTempUser.phone = email;
            mTempUser.password = MD5.md5(password);
            mPresenter.login(mTempUser);
        }
    }

    @OnClick({R.id.email_sign_in_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.email_sign_in_button:
                attemptLogin();
                break;
        }
    }

    @Override
    public void loginSuccess(User user) {
        user.phone = mTempUser.phone;
        UserData.saveCurrentUser(user);
        onBackPressed();
    }

    @Override
    public void loginFailed() {
        KLog.d("登入失败");
    }
}

