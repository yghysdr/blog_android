package io.yghysdr.discover.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

import com.github.yghysdr.base.BaseFragment;
import com.github.yghysdr.theme.ThemeEvent;
import com.github.yghysdr.theme.ThemeUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.yghysdr.discover.R;
import io.yghysdr.discover.R2;
import io.yghysdr.discover.contract.DiscoverContract;
import io.yghysdr.mediator.discover.IConstantDiscover;


@Route(path = IConstantDiscover.DISCOVER_FRAGMENT_DISCOVER)
public class DiscoverFragment extends BaseFragment
        implements DiscoverContract.View {

    @BindView(R2.id.discover_fl)
    FrameLayout discoverFl;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;


    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventTheme(ThemeEvent event) {
        switch (event.intent) {
            case ThemeEvent.CHANGE:
                discoverFl.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.bg));
                toolbar.setBackgroundResource(ThemeUtil.getResId(ThemeUtil.primary));
                toolbar.setTitleTextColor(ThemeUtil.getColorId(getContext(), ThemeUtil.txtNav));
                break;
        }
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

    @Override
    protected void LazyLoad() {
        initToolBar(toolbar, "发现", false);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.discover_fragment_discover;
    }
}
