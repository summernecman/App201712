package com.siweisoft.heavycenter;

//by summer on 2017-07-28.

import android.content.Context;

import com.android.lib.aplication.LibAplication;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.exception.exception.CrashHander;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.NetGet;
import com.android.lib.network.news.NetI;
import com.android.lib.network.newsf.NetFAdapter;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.view.refresh.MyClassicsFooter;
import com.android.lib.view.refresh.MyClassicsHeader;
import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.siweisoft.heavycenter.data.netd.crash.CrashNetOpe;

import cn.jpush.android.api.JPushInterface;

public class HCApp extends LibAplication implements OnFinishListener{


    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_base, android.R.color.white);//全局设置主题颜色
                return new MyClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_base, android.R.color.white);//全局设置主题颜色
                //指定为经典Footer，默认是 BallPulseFooter
                return new MyClassicsFooter(context);
            }
        });
    }


    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        new Thread(new Runnable() {@Override public void run() {Glide.get(getApplicationContext()).clearDiskCache();}}).start();
        CrashHander.getInstance().init(getBaseContext(),this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        FragManager2.getInstance().clear();

        if(true){
            NetGet.test = true;
            NetAdapter.cache = true;
            NetFAdapter.cache = true;
            new Test().init();
        }else{
            NetGet.test = false;
            NetAdapter.cache = false;
            NetFAdapter.cache = false;
        }


    }

    @Override
    public void onFinish(Object o) {
        CrashNetOpe.setCrash(this,o.toString());
    }
}
