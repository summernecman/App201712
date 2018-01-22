package com.siweisoft.heavycenter.module.myce.unit.addr;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;

public class AddrFrag extends AppFrag<AddrUIOpe,AddrDAOpe> implements ViewListener{



    @Override
    public void initData() {
        super.initData();

        getP().getD().getMapUtil().init(activity,true);
        getP().getD().getMapUtil().registerLocationListener(activity, new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                getP().getU().bind.tvAddr.setText(StringUtil.getStr(bdLocation.getAddrStr()));
            }
        });
        getP().getD().startMap();
        getP().getU().LoadListData(getP().getD().getAddrs(),AddrFrag.this);
        getP().getU().initInput(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getMapUtil().searchNeayBy("上海市",o.toString() ,new OnGetPoiSearchResultListener() {
                    @Override
                    public void onGetPoiResult(PoiResult poiResult) {
                        if(poiResult!=null&&poiResult.getAllPoi()!=null){
                            getP().getD().setAddrs(poiResult.getAllPoi());
                            getP().getU().notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
                        LogUtil.E("");
                    }

                    @Override
                    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                        LogUtil.E("");
                    }
                });
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                PoiInfo poiInfo = (PoiInfo) v.getTag(R.id.data);
                getArguments().putString(ValueConstant.DATA_DATA,poiInfo.address);
                getBaseUIActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getP().getD().getMapUtil().getPoiSearch().destroy();
        getP().getD().stopMap();
    }
}
