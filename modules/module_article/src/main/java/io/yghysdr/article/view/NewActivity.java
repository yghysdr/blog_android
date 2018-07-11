package io.yghysdr.article.view;

import com.github.yghysdr.base.ui.BaseActivity;
import io.yghysdr.article.R;
import io.yghysdr.article.contract.NewContract;

public class NewActivity extends BaseActivity
        implements NewContract.View {

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_new;
    }
}
