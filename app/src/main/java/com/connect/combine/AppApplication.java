package com.connect.combine;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.connect.base.utils.SPUtils;
import com.connect.combine.constant.AppConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppConstant.GLOBAL_CONTEXT = getApplicationContext();
        // OkHttpClient build = new OkHttpClient.Builder().cookieJar(new CookieJarImpl(new MemoryCookieStore())).build();
        initOkGo();
        initLanguage();
    }

    private void initLanguage() {
        SPUtils spUtils = new SPUtils(getApplicationContext(), AppConstant.SP);
        String sta = spUtils.getString("Language", "en");
        if(sta.equals("en"))
        {
            AppConstant.CURRENT_LANGUAGE=1;
        }else
        {
            AppConstant.CURRENT_LANGUAGE=0;
        }
    }

    private void initOkGo() {
        OkGo init = OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        builder.hostnameVerifier(HttpsUtils.UnSafeHostnameVerifier);
        init.setOkHttpClient(builder.build());
    }
}
