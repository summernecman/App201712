package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationData;
import com.siweisoft.heavycenter.databinding.FragTransBinding;

public class TransUIOpe extends BaseUIOpe<FragTransBinding>{

    public static final int 地图状态_DESTROY = 0;

    public static final int 地图状态_ONRESUME = 1;

    public static final int 地图状态_ONPAUSE = 2;



    public TransUIOpe(Context context) {
        super(context);
    }

    public void 设置地图状态(int status){
        switch (status){
            case 地图状态_DESTROY:
                bind.bmapView.onDestroy();
                break;
            case 地图状态_ONRESUME:
                bind.bmapView.onResume();
                break;
            case 地图状态_ONPAUSE:
                bind.bmapView.onPause();
                break;
        }
    }

}
