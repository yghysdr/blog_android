package io.yghysdr.discover.view;

import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import com.github.yghysdr.base.ui.BaseFragment;
import io.yghysdr.common.common.util.ThemeUtil;
import io.yghysdr.discover.R;
import io.yghysdr.discover.R2;
import io.yghysdr.discover.contract.DiscoverContract;
import io.yghysdr.mediator.discover.IConstantDiscover;

@Route(path = IConstantDiscover.DISCOVER_FRAGMENT_DISCOVER)
public class DiscoverFragment extends BaseFragment
        implements DiscoverContract.View {

    @BindView(R2.id.discover_fl)
    FrameLayout discoverFl;


    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }


    @Override
    public void refreshTheme() {
        discoverFl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
    }

    @Override
    protected void LazyLoad() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_discover;
    }
}
