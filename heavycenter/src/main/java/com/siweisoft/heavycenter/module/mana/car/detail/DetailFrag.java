package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import android.content.Intent;
import android.os.Environment;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.UriUtils;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarRes;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadReqBean;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadResBean;
import com.siweisoft.heavycenter.module.myce.MyceFrag;

import java.io.File;

import butterknife.OnClick;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {


    public static final String TYPE_NEW = "TYPE_NEW";

    @Override
    public void initData() {
        super.initData();
        getP().getD().setCarinfo((CarsResBean.ResultsBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initData(getP().getD().getCarinfo());
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData());
    }

    @OnClick({R.id.ll_vehicleLicensePhoto,R.id.ll_vehiclePhoto,R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ll_vehicleLicensePhoto:
                IntentUtil.getInstance().photoShowFromphone(this,01);
                break;
            case R.id.ll_vehiclePhoto:
                IntentUtil.getInstance().photoShowFromphone(this,02);
                break;
            case R.id.ftv_right2:
                if(getP().getU().canGo()){
                    getP().getD().updateCar(getP().getU().getUpdateCarReq(getP().getD().getUpdateCarReq()), new UINetAdapter<UpdateCarRes>(getContext()) {
                        @Override
                        public void onResult(boolean success, String msg, UpdateCarRes o) {
                            super.onResult(success, msg, o);

                        }
                    });
                }
                break;
        }

    }


    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        LogUtil.E(Environment.getDownloadCacheDirectory().getPath());
        File file = new File(UriUtils.getPath(getActivity(), data.getData()));
        LogUtil.E(file.exists());
        String s= "";
        switch (requestCode){
            case 01:
                s = UpdateHeadReqBean.驾驶证照片;
                break;
            case 02:
                s = UpdateHeadReqBean.车辆照片;
                break;
        }
        getP().getD().updateHead(UriUtils.getPath(getActivity(), data.getData()),s, new UINetAdapter<UpdateHeadResBean>(getActivity()) {
            @Override
            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                stopLoading();
                if(haveData){
                    LoginResBean loginResBean = LocalValue.getLoginInfo();
                    String s = baseResBean.getResult().toString();
                    if(s!=null){
                        if(s.trim().startsWith("[")){
                            s= s.substring(1,s.length()-1).trim();
                        }
                        switch (requestCode){
                            case 01:
                                getP().getD().getUpdateCarReq().setVehicleLicensePhoto(s);
                                break;
                            case 02:
                                getP().getD().getUpdateCarReq().setVehiclePhoto(s);
                                break;
                        }
                        getP().getU().initPhoto(getP().getD().getUpdateCarReq());
                    }
                }
            }
        });


    }
}
