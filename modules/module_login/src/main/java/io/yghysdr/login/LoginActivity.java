package io.yghysdr.login;

import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.blog.res.bean.User;
import io.yghysdr.mediator.login.IConstantLogin;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.yghysdr.base.BaseActivity;
import com.github.yghysdr.http.HttpException;
import com.github.yghysdr.util.MD5;
import com.github.yghysdr.util.UIUtils;
import com.github.yghysdr.util.Validation;


/**
 * 登入
 */
@Route(path = IConstantLogin.LOGIN_ACTIVITY_LOGIN)
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R2.id.phone)
    AutoCompleteTextView mPhoneView;
    @BindView(R2.id.password)
    AutoCompleteTextView mPasswordView;

    private User mTempUser;
    private LoginPresenterImp mLoginPresenter;


    @Override
    protected int provideContentViewId() {
        return R.layout.login_activity_login;
    }

    @Override
    public void initData() {
        super.initData();
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == R.id.login || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });
    }

    @Override
    public void addPresenter(List list) {
        super.addPresenter(list);
        mLoginPresenter = new LoginPresenterImp();
        list.add(mLoginPresenter);
    }

    private void attemptLogin() {
        mPhoneView.setError(null);
        mPasswordView.setError(null);
        String phone = mPhoneView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean cancel = false;
        View focusView = null;
        if (!Validation.isPhone(phone)) {
            mPhoneView.setError(getString(R.string.error_invalid_email));
            focusView = mPhoneView;
            cancel = true;
        } else if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            mTempUser = new User();
            mTempUser.phone = phone;
            mTempUser.password = MD5.md5(password);
            mLoginPresenter.login(this, mTempUser, this);
        }
    }

    @OnClick({R2.id.email_sign_in_button})
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.email_sign_in_button) {
            attemptLogin();
        }
    }


    @Override
    public void loginSuccess(User user) {
        user.phone = mTempUser.phone;
        onBackPressed();
    }

    @Override
    public void loginFailed(HttpException exception) {
        UIUtils.showToast(this, exception.message);
    }
}

