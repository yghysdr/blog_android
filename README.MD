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