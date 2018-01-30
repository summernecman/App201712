package com.siweisoft.heavycenter.module.myce.unit.addr;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.two.FragManager2;
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
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.myce.unit.area.prov.ProvFrag;

import butterknife.OnClick;

public class AddrFrag extends AppFrag<AddrUIOpe,AddrDAOpe> implements ViewListener{



    @Override
    public void initData() {
        super.initData();
        getP().getD().getMapUtil().init(activity,true);
        getP().getD().getMapUtil().registerLocationListener(activity, new BDAbstractLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                getP().getU().bind.tvAddr.setText(StringUtil.getStr(bdLocation.getAddrStr()));
                getP().getU().bind.tvCity.setText(StringUtil.getStr(bdLocation.getCity()));
                getP().getD().getUnitInfo().setCompanyAddress(bdLocation.getAddrStr());
                getP().getD().getUnitInfo().setCompanyLat(bdLocation.getLatitude());
                getP().getD().getUnitInfo().setCompanyLng(bdLocation.getLongitude());
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
                getP().getD().getUnitInfo().setCompanyAddress(poiInfo.address);
                getP().getD().getUnitInfo().setCompanyLat(poiInfo.location.latitude);
                getP().getD().getUnitInfo().setCompanyLng(poiInfo.location.longitude);
                getArguments().putSerializable(ValueConstant.DATA_DATA,getP().getD().getUnitInfo());
                getBaseUIActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getP().getD().stopMap();
    }

    @OnClick({R.id.tv_addr,R.id.ll_local,R.id.tv_city})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_addr:
                if(NullUtil.isStrEmpty(getP().getU().bind.tvAddr.getText().toString())){
                    ToastUtil.getInstance().showShort(getActivity(),"当前地址为空 请重新定位");
                    return;
                }
                getArguments().putSerializable(ValueConstant.DATA_DATA,getP().getD().getUnitInfo());
                getBaseUIActivity().onBackPressed();
                break;
            case R.id.ll_local:
                getP().getD().getMapUtil().init(activity,true);
                getP().getD().getMapUtil().registerLocationListener(activity, new BDAbstractLocationListener() {
                    @Override
                    public void onReceiveLocation(BDLocation bdLocation) {
                        getP().getU().bind.tvAddr.setText(StringUtil.getStr(bdLocation.getAddrStr()));
                    }
                });
                getP().getD().startMap();
                break;
            case R.id.tv_city:
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,1);
                bundle.putString(ValueConstant.DATA_DATA,ProvFrag.选择一个城市);
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,new ProvFrag(),bundle);
                break;
        }
    }

    @Override
    public void onRestart(int res, Bundle bundle) {
        super.onRestart(res, bundle);
        switch (res){
            case 1:
                if(bundle==null|| bundle.getString(ValueConstant.DATA_RES)==null){
                    return;
                }
               getP().getU().bind.tvCity.setText(StringUtil.getStr( bundle.getString(ValueConstant.DATA_RES)));
                break;
        }
    }
}
