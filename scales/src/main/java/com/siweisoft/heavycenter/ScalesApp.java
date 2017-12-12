package com.siweisoft.heavycenter;

//by summer on 2017-07-28.

import com.android.lib.aplication.LibAplication;
import com.bumptech.glide.Glide;

public class ScalesApp extends LibAplication {

    @Override
    public void onCreate() {
        super.onCreate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(getApplicationContext()).clearDiskCache();
            }
        }).start();


    }
}
