package com.example.administrator.yyqianghongb;

import android.app.Application;
import android.util.Log;


import com.example.administrator.yyqianghongb.common.GlobalVariable;
import com.example.administrator.yyqianghongb.common.HttpUrl;
import com.example.administrator.yyqianghongb.view.ToastBlackStyle;
import com.hjq.toast.ToastUtils;
import com.tencent.smtt.sdk.QbSdk;
import com.zhouyou.http.EasyHttp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

public  class MyApplication extends Application implements LifecycleOwner {

    private final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);

    @Override
    public void onCreate() {
        super.onCreate();
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE);
        initSdk(this);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycle;
    }

    /**
     * 初始化一些第三方框架
     */
    public void initSdk(Application application) {
        ToastUtils.init(application);
        ToastUtils.initStyle(new ToastBlackStyle(application));
        initHttp();
        x5();
    }


    //初始化网络请求设置
    private void initHttp() {
        EasyHttp.init(this);//默认初始化
        //全局设置请求参数
//        HttpParams params = new HttpParams();
//        params.put("appId", AppConstant.APPID);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        EasyHttp.getInstance()
                //可以全局统一设置全局URL
                //.setBaseUrl(CommonConfig.url)//设置全局URL  url只能是域名 或者域名+端口号
                .setBaseUrl(HttpUrl.url)
                // 打开该调试开关并设置TAG,不需要就不要加入该行
                // 最后的true表示是否打印内部异常，一般打开方便调试错误
                .debug("MyEasyHttp", GlobalVariable.isLog)

                //如果使用默认的60秒,以下三行也不需要设置
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 100)
                .setConnectTimeout(60 * 100)

                //可以全局统一设置超时重连次数,默认为3次,那么最差的情况会请求4次(一次原始请求,三次重连请求),
                //不需要可以设置为0
                .setRetryCount(3)//网络不好自动重试3次
                //可以全局统一设置超时重试间隔时间,默认为500ms,不需要可以设置为0
                .setRetryDelay(500)//每次延时500ms重试
                //可以全局统一设置超时重试间隔叠加时间,默认为0ms不叠加
                .setRetryIncreaseDelay(500)//每次延时叠加500ms

                //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体请看CacheMode
//                .setCacheMode(CacheMode.NO_CACHE)
                //可以全局统一设置缓存时间,默认永不过期
//                .setCacheTime(-1)//-1表示永久缓存,单位:秒 ，Okhttp和自定义RxCache缓存都起作用
                //全局设置自定义缓存保存转换器，主要针对自定义RxCache缓存
//                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                //全局设置自定义缓存大小，默认50M
//                .setCacheMaxSize(100 * 1024 * 1024)//设置缓存大小为100M
                //设置缓存版本，如果缓存有变化，修改版本后，缓存就不会被加载。特别是用于版本重大升级时缓存不能使用的情况
//                .setCacheVersion(1)//缓存版本为1
                //.setHttpCache(new Cache())//设置Okhttp缓存，在缓存模式为DEFAULT才起作用

                //可以设置https的证书,以下几种方案根据需要自己设置
                .setCertificates();                                  //方法一：信任所有证书,不安全有风险
        //.setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
        //配置https的域名匹配规则，不需要就不要加入，使用不当会导致https握手失败
        //.setHostnameVerifier(new SafeHostnameVerifier())
        //.addConverterFactory(GsonConverterFactory.create(gson))//本框架没有采用Retrofit的Gson转化，所以不用配置
//                .addCommonHeaders(headers);//设置全局公共头
//                .addCommonParams(params);//设置全局公共参数
        //.addNetworkInterceptor(new NoCacheInterceptor())//设置网络拦截器
        //.setCallFactory()//局设置Retrofit对象Factory
        //.setCookieStore()//设置cookie
        //.setOkproxy()//设置全局代理
        //.setOkconnectionPool()//设置请求连接池
        //.setCallbackExecutor()//全局设置Retrofit callbackExecutor
        //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
        //.addInterceptor(new GzipRequestInterceptor())//开启post数据进行gzip后发送给服务器
//                .addInterceptor(new CustomSignInterceptor());//添加参数签名拦截器
    }

    private void x5(){
        //下载x5内核，可以不需要，因为会共用其他软件的x5内核，比如微信、QQ等
        QbSdk.setDownloadWithoutWifi(true);

        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.e("snow", "========onCoreInitFinished===");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //加载x5内核成功返回值为true，否则返回false，加载失败会调用系统的webview
                Log.e("snow", "x5初始化结果====" + b);
            }
        });
    }
}