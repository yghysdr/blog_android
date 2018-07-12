# 组件化开发
- 模块功能划分
- module单独编译和集成编译
  + build.gradle配置
  + 清单文件的配置
- 模块间调用
- Application初始化第三方的问题
- A业务模块的aa功能被B模块引用的讨论
- ButterKnife使用
- 模块或者中间件的快捷生成shell脚本

### 项目结构:
- frameworks 框架层
    - kit_http 网络请求框架
    - kit_base 基础框架
    - kit_res 数据模型组件,颜色表等项目基础资源
    - sdk_jpush 对极光模块的封装
- modules 业务层
    - app壳
      + module_app
    - 业务组件
      + module_main
      + module_login
      + module_user
    - 中间件
      + mediator_login
      + mediator_user

#### kit_http
- 网络框架层，网络请求的封装（其实就是一个第三方库），尽量避免直接对第三方框架的直接依赖
- 不能运行
- 需要抽取库放在仓库中
- 会依赖其他网络框架

#### Kit_modle
- 包含bean等数据模型
- 不依赖第三方库

#### module_login与mediator_login
- module_login通过mediator_login模块对外暴露的功能
- mediator_login不实现具体功能，只是module_login功能对外的暴露
- module_login 实现具体功能
- mediator_login不能运行，module_login是否可以运行取决于isLibrary
- 尽量减少mediator_login对第三方库的依赖，即使依赖一些库也应该使用implementation而不是api
- mediator_login包含一些module_login需要对外暴露的常量，功能实现，调起Activity或者获得fragment
- module_login可以依赖mediator_login，反之不可以。mediator_login只能通过Arouter获取module_login的服务实现

#### module_app
- 打包,配置签名,混淆
- 一直可以运行
- 依赖所有的业务模块（不包含中间件）


### module单独编译和集成编译
> 第一步配置build.gradle，第二部设置AndroidManifest

#### Android中的Module主要有两种属性application和library
- application属性，可以独立运行的Android程序，也就是我们的APP；
- library属性，不可以独立运行，一般是Android程序依赖的库文件；

在Module下的build.gradle文件下配置apply plugin
```
if (isLibrary.toBoolean()) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```
其中的isLibrary是在gradle.properties中定义一个常量值
```
# isLibrary==true的时候是库，只能运行app；false模块单独运行
isLibrary=true
```

#### 清单文件
- app壳 只有一个
- 业务模块 有两个
- 中间件 只有一个

当Module属性是library的时候，业务组件的表单是绝对不能拥有自己的Application和launch的Activity的（main_module除外，main_module需要有一个launch的Activity，），也不能声明APP名称、图标等属性，总之app壳工程有的属性，业务组件都不能有，当是application的时候和普通的App一样。清单文件切换方法。
```
sourceSets {
        main {
            if (isLibrary.toBoolean()) {
                manifest.srcFile 'src/main/module/AndroidManifest.xml'
                //集成开发模式下排除debug文件夹中的所有Java文件
                java {
                    exclude 'debug/**'
                }
            } else {
                manifest.srcFile 'src/main/AndroidManifest.xml'
            }
        }
    }
```

##### App壳
```
//app壳
<application
    android:name="com.github.yghysdr.base.BaseApp"
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:theme="@style/AppTheme" />
```
##### 业务模块
isLibrary == false，可以单独运行
```
<application
    android:name="com.github.yghysdr.base.BaseApp"
    android:allowBackup="true"
    android:icon="@mipmap/ic_launcher"
    android:label="@string/app_name"
    android:supportsRtl="true"
    android:theme="@style/AppTheme">
    <activity
        android:name=".debug.DebugActivity"
        android:configChanges="screenSize|orientation"
        android:screenOrientation="portrait">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />

            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
</application>
```
isLibrary == true，不能单独运行（在main/module文件夹下面）
```
<application>
    <activity android:name="io.yghysdr.login.LoginActivity" />
</application>
```
#### 模块间调用
> 原则上业务模块不能相互依赖，但是一定存在模块间通讯，模块间的通讯通过依赖对应模块的中间件

其他模块需要需要拿到用户模块的User信息或者调起登入模块的界面的时候都是通过中间件来实现，从而屏蔽掉登入模块。
通过Arouter可以很容易拿到Activity和Fragment，但是当需要用到一个模块的方法的时候比较麻烦（Arouter的暴露服务功能）。

- 首先在登入中间件（mediator_user）定义IUserInfo接口
```
public interface IUserProvider extends IProvider {
    User getUser();

    void updateUser(User user);

    void clearUser();
}
```
- 然后在登入模块（module_user）实现，
```
@Route(path = IContentUser.USER_SERVICE_USER_INFO)
public class UserProviderImp implements IUserProvider {
}
```
- 最后在登入中间件（mediator_user）拿到IUserInfo的实现
```
public class MediatorUser {
    public static Fragment getUserFragment() {
        return (Fragment) ARouter
                .getInstance()
                .build(IContentUser.USER_FRAGMENT_USER)
                .navigation();
    }

    static IUserProvider iUserProvider;
    
    //IUserInfo的实现,不一定是静态的需要根据集体业务，具体实现
    public static IUserProvider getUserProvider() {
        if (iUserProvider == null) {
            ARouter.getInstance()
                    .build(IContentUser.USER_SERVICE_USER_INFO)
                    .navigation();
        }
        return iUserProvider;
    }
}
```
#### Application初始化第三方的问题
> 通常会有一些第三方库需要在Application中初始化，但是一个项目只能配置一个Application，组件化之后，Application处于顶层为止，
业务模块（或者sdk模块）依赖顶层，所以无法在Application中初始化业务模块，只能在业务模块初始化。但是换个思路，如果可以在其他模块中知道
Application的生命周期的调用，就可以在这个时刻初始化我们需要的第三方库。


**多个模块都需要初始化在application中初始化，所以使用继承是不行的，业务模块不应该创建application，因为打包的时候不应该使用具体业务模块的任何类**

第一步：实现
```
//ClassUtils.getObjectsWithInterface()是获取单一路径下所有实现了接口的类对象。

public class BaseApp extends MultiDexApplication {

    private List<IApplicationDelegate> mAppDelegateList;

    @Override
    public void onCreate() {
        super.onCreate();
        String progress = getProcessName(this);
        if (progress != null) {
            if (getPackageName().equals(progress)) {
                initLibrary();
                mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, getPackageName());
                for (IApplicationDelegate delegate : mAppDelegateList) {
                    delegate.onCreate(this);
                }
            }
        }
    }
}
```
其他模块中使用 其他模块
```
public class BlogApp implements IApplicationDelegate {

    @Override
    public void onCreate(Application application) {
        HttpConfiguration.setBuilder(new HttpConfiguration
                .Builder()
                .setContext(application)
//                .setAuthenticator(new TokenAuthenticator())
                .setHost(new HostImp())
                .initStetho(application));
    }
}
```
### module_A的aa功能被module_B模块引用的讨论
目前使用的方案是属于A的相关功能都是在A业务模块中使用，其他模块引用都是通过A的中间件使用，此时带来的问题是，单独运行B模块（使用了aa功能，只依赖了A的中间件），
,可能在运行期间会出现问题，编译期间没问题。是因为module_B没有依赖module_A,编译期module_B并没有编译module_A模块，而是编译了mediator_A，mediator_A也不依赖module_A，
所以造成这个现象。

- 解决方案一：是在module_B的debug包下初始化mediator_A需要的数据（根据清单文件的配置可以知道，module_B是library的时候，debug下的文件不会被打包）。
- 解决方案二：调整module_app依赖的业务模块，只依赖A，B这样通过module_app打包出来apk是AB模块组合。

不过当业务模块较多时，可能存在C调用了几乎所有的业务模块。这是方案一明显不可行，方案二，module_app甚至需要的所有的业务模块，也不可行。但是这里认为没必要追求模块的单独运行期间的完整性，只需要保证模块单独编译的完整性即可（保证模块间是相互隔离的，从而实现高内聚低耦合的原则）。

#### ButterKnife
在module中使用的时候，需要用R2，点击事件需要用if代替switch。具体参考 [butterknife](http://jakewharton.github.io/butterknife/)

####  使用脚本生成模块或者中间件
> 这个shell脚本适用在Mac上的，根据脚本生成模块后，需要在settin.gradle添加模块名称

```
cd script
./config.sh 
```