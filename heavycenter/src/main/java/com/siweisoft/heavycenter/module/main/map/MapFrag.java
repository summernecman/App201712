package com.siweisoft.heavycenter.module.main.map;

//by summer on 2017-12-11.

import android.content.Intent;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

public class MapFrag extends AppFrag<MapUIOpe,MapDAOpe> {


    @Override
    public void lazyInit() {
        super.lazyInit();
        getP().getD().getMapUtil().init(getActivity());
        getP().getD().getMapUtil().registerLocationListener(getActivity(), new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                getP().getD().getMapUtil().animateMapStatus(getP().getU().bind.map.getMap(),bdLocation);
                getP().getD().getMapUtil().setMyLocationData(getP().getU().bind.map.getMap(),bdLocation);
            }
        });
        getP().getD().startMap();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)activity).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    MainAct mainAct = (MainAct) getActivity();
                    Intent intent = new Intent(mainAct, CaptureActivity.class);
                    activity.startActivityForResult(intent, ValueConstant.CODE_REQUSET);
                }
                break;
        }
    }

    public void local(BDLocation bdLocation){
        getP().getD().getMapUtil().animateMapStatus2(getP().getU().bind.map.getMap(),bdLocation);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getP().getD().stopMap();
    }
}
