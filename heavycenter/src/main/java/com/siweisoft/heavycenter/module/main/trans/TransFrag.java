package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.LogUtil;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;

public class TransFrag extends BaseUIFrag<TransUIOpe,TransDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getD().getMapUtil().init(activity.getApplicationContext());
        getP().getD().getMapUtil().registerLocationListener(activity, new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                TransFrag.this.getId();
                LogUtil.E(bdLocation.getLatitude()+":"+bdLocation.getLongitude()+":"+bdLocation.getAddrStr());
                getP().getD().getMapUtil().animateMapStatus(getP().getU().bind.bmapView.getMap(),bdLocation);
                getP().getD().getMapUtil().setMyLocationData(getP().getU().bind.bmapView.getMap(),bdLocation);
                getP().getD().getMapUtil().addOverlays(getP().getU().bind.bmapView.getMap(),new LatLng[]{new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude())});
            }
        });
        getP().getD().getMapUtil().getLocationClient().start();
        getP().getD().getMapUtil().getLocationClient().requestLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getP().getU().设置地图状态(TransUIOpe.地图状态_DESTROY);
        getP().getD().getMapUtil().getLocationClient().stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getP().getU().设置地图状态(TransUIOpe.地图状态_ONRESUME);

    }

    @Override
    public void onPause() {
        super.onPause();
        getP().getU().设置地图状态(TransUIOpe.地图状态_ONPAUSE);
    }
}
