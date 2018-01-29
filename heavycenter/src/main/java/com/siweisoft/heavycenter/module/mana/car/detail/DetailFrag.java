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
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarRes;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadReqBean;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadResBean;

import java.io.File;

import butterknife.OnClick;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {


    public static final String TYPE_NEW = "TYPE_NEW";

    public static final String TYPE_DETAIL = "TYPE_DETAIL";

    @Override
    public void initData() {
        super.initData();
        getP().getD().setType(getArguments().getString(ValueConstant.DATA_DATA2));
        getP().getU().init(getP().getD().getType());
        switch (getP().getD().getType()){
            case TYPE_NEW:
                break;
            case TYPE_DETAIL:
                getP().getD().setCarinfo((CarsResBean.CarInfoRes) getArguments().getSerializable(ValueConstant.DATA_DATA));
                getP().getD().infoCar(getP().getD().getCarInfoReq(getP().getD().getCarinfo()), new UINetAdapter<CarsResBean.CarInfoRes>(getActivity()) {
                    @Override
                    public void onResult(boolean success, String msg, CarsResBean.CarInfoRes o) {
                        super.onResult(success, msg, o);
                        if(success){
                            getP().getD().setCarinfo(o);
                            getP().getU().initData(getP().getD().getCarinfo());
                            getP().getU().initRecycle();
                            getP().getU().LoadListData(getP().getD().getData());
                        }
                    }
                });
                break;
        }
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
                switch (getP().getD().getType()){
                    case TYPE_NEW:
                        if(getP().getU().canNewGo()){
                            getP().getD().newCar(getP().getU().getCarNewReqBean(getP().getD().getCarNewReqBean(getP().getD().getCarinfo())), new UINetAdapter<CarNewResBean>(getActivity()) {
                                @Override
                                public void onResult(boolean success, String msg, CarNewResBean o) {
                                    super.onResult(success, msg, o);
                                    if(success){
                                        getArguments().putBoolean(ValueConstant.FARG_TYPE,true);
                                        getBaseUIActivity().onBackPressed();
                                    }
                                }
                            });
                        }
                        break;
                    case TYPE_DETAIL:
                        if(getP().getU().canGo()){
                            getP().getD().updateCar(getP().getU().getUpdateCarReq(getP().getD().getUpdateCarReq(getP().getD().getCarinfo())), new UINetAdapter<UpdateCarRes>(getContext()) {
                                @Override
                                public void onResult(boolean success, String msg, UpdateCarRes o) {
                                    super.onResult(success, msg, o);
                                    if(success){
                                        getArguments().putBoolean(ValueConstant.FARG_TYPE,true);
                                        getBaseUIActivity().onBackPressed();
                                    }
                                }
                            });
                        }
                        break;
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
                    String s = baseResBean.getResult().toString();
                    if(s!=null){
                        if(s.trim().startsWith("[")){
                            s= s.substring(1,s.length()-1).trim();
                        }
                        switch (requestCode){
                            case 01:
                                getP().getD().getCarinfo().setVehicleLicensePhoto(s);
                                break;
                            case 02:
                                getP().getD().getCarinfo().setVehiclePhoto(s);
                                break;
                        }
                        getP().getU().initPhoto(getP().getD().getCarinfo().getVehiclePhoto(),getP().getD().getCarinfo().getVehicleLicensePhoto());
                    }
                }
            }
        });


    }
}
