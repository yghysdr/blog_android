# blog_android
> blog Android版本

## init project
- 添加config-gradle
## 项目搭建1
## 项目搭建2
## 测试登入
## DialogCallBack

## RecycleViewHelper
RecycleView+SwipeRefreshLayout组合，实现上啦加载更多，下拉刷新的帮助类，
- 实现RecycleViewHelper的Helper借口
- 数据请求成功时调用 mHelper.addDataToView(...);
- 数据请求失败时调用 mHelper.stopRequest();
## RecycleViewHelper抽取成库
## SRecycleView 上传到jcenter
### 踩坑
```
* What went wrong:
A problem occurred evaluating project ':app'.
> java.lang.UnsupportedClassVersionError: com/android/build/gradle/AppPlugin : Unsupported major.minor version 52.0
```
解决，在gradle.properties添加
```
org.gradle.java.home=/Applications/Android Studio.app/Contents/jre/jdk/Contents/Home
```

[新版Bintray-极简上传Library到JCenter](https://juejin.im/post/5858cc37570c35006916b718)
## 处理网络请求状态

## 优化
### 懒加载
```
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected boolean mReUse = false;
    //懒加载
    private boolean isViewCreated, isUIVisible;


    //onAttach--onCreate--onCreateView--onViewCreated
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mReUse && mRootView != null) {
            return mRootView;
        }
        mRootView = inflater.inflate(getLayoutResource(), container, false);
        //在onCreateView判断试图是否加载时为了复用fragment时不重新判断懒加载isViewCreated
        isViewCreated = true;
        isLazyLoad();
        beforeReturn();
        return mRootView;
    }


    /**
     * 返回之前的操作，如果需要集成
     */
    public void beforeReturn() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isUIVisible = true;
            isLazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void isLazyLoad() {
        if (isViewCreated && isUIVisible) {
            lazyLoad();
            isViewCreated = false;
            isUIVisible = false;
        }
    }

    /**
     * 懒加载
     */
    protected void lazyLoad() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mReUse) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    protected abstract int getLayoutResource();
}
```
## 主页框架搭建
## 主页框架搭建优化
## home页面数据挂载
## rxbus
## 夜间模式
还未实现不许重启界面更改
activity设置主题必须在setContentView之前
## 夜间模式，
个人中心，不重启切换，夜间模式的抽取