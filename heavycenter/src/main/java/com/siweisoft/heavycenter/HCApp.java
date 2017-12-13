package com.siweisoft.heavycenter;

//by summer on 2017-07-28.

import com.android.lib.aplication.LibAplication;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.exception.exception.CrashHander;
import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.siweisoft.heavycenter.data.netd.crash.CrashNetOpe;

public class HCApp extends LibAplication implements OnFinishListener{



    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
        new Thread(new Runnable() {@Override public void run() {Glide.get(getApplicationContext()).clearDiskCache();}}).start();
        CrashHander.getInstance().init(getBaseContext(),this);
    }

    @Override
    public void onFinish(Object o) {
        CrashNetOpe.setCrash(this,o.toString());
    }
}
