package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import android.content.Intent;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.UriUtils;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.mana.car.bind.BindCarRes;
import com.siweisoft.heavycenter.data.netd.mana.car.info.CarInfoReq;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarRes;
import com.siweisoft.heavycenter.data.netd.unit.dirvers.DriverRes;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadReqBean;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadResBean;
import com.siweisoft.heavycenter.module.main.MainAct;

import java.util.ArrayList;

import butterknife.OnClick;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> implements ViewListener{



    @Override
    public void initNow() {
        super.initNow();
        getP().getD().setType(getArguments().getString(ValueConstant.DATA_DATA2));
        getP().getU().init(getP().getD().getType());
    }

    @Override
    public void initdelay() {
        super.initdelay();
        switch (getP().getD().getType()){
            case CarDetailValue.新建车辆:

                break;
            case CarDetailValue.新建车辆并绑定:
                getP().getU().bind.title.getMidTV().setText(StringUtil.getStr(getArguments().getString(ValueConstant.DATA_DATA,"")));
                //getP().getU().bind.itemCarlicenseno.setMidEtTxt(StringUtil.getStr(getArguments().getString(ValueConstant.DATA_DATA,"")));
                break;
            case CarDetailValue.查看车辆:
                getP().getD().setCarinfo((CarsResBean.CarInfoRes) getArguments().getSerializable(ValueConstant.DATA_DATA));
                getP().getD().infoCar(getP().getD().getCarInfoReq(getP().getD().getCarinfo()), new UINetAdapter<CarsResBean.CarInfoRes>(this) {
                    @Override
                    public void onSuccess(CarsResBean.CarInfoRes o) {
                        getP().getD().setCarinfo(o);
                        getP().getU().initData(getP().getD().getCarinfo());
                        getP().getD().drvers(getP().getD().getType(), getP().getD().getCarinfo(), new UINetAdapter<ArrayList<DriverRes>>(getActivity()) {
                            @Override
                            public void onSuccess(ArrayList<DriverRes> o) {
                                super.onSuccess(o);
                                getP().getD().getDriverRes().clear();
                                if(o!=null){
                                    getP().getD().getDriverRes().addAll(o);
                                    getP().getU().LoadListData(getP().getD().getDriverRes(),DetailFrag.this);
                                }
                            }
                        });
                    }
                });
                break;
            case CarDetailValue.绑定车辆:
                getP().getD().setCarinfo((CarsResBean.CarInfoRes) getArguments().getSerializable(ValueConstant.DATA_DATA));
                getP().getD().infoCar(getP().getD().getCarInfoReq(getP().getD().getCarinfo()), new UINetAdapter<CarsResBean.CarInfoRes>(this) {
                    @Override
                    public void onSuccess(CarsResBean.CarInfoRes o) {
                        getP().getD().setCarinfo(o);
                        getP().getU().initData(getP().getD().getCarinfo());
//                            getP().getD().drvers(getP().getD().getType(), getP().getD().getCarinfo(), new UINetAdapter<ArrayList<DriverRes>>(getActivity()) {
//                                @Override
//                                public void onSuccess(ArrayList<DriverRes> o) {
//                                    super.onSuccess(o);
//                                    getP().getD().getDriverRes().clear();
//                                    if(o!=null){
//                                        getP().getD().getDriverRes().addAll(o);
//                                        getP().getU().LoadListData(getP().getD().getDriverRes(),DetailFrag.this);
//                                    }
//                                }
//                            });
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
                    case CarDetailValue.新建车辆:
                        if(getP().getU().canNewGo()){
                            getP().getD().newCar(getP().getU().getCarNewReqBean(getP().getD().getCarNewReqBean(getP().getD().getCarinfo())), new UINetAdapter<CarNewResBean>(this) {
                                @Override
                                public void onSuccess(CarNewResBean o) {
                                    getArguments().putBoolean(ValueConstant.FARG_TYPE,true);
                                    getBaseUIAct().onBackPressed();
                                }
                            });
                        }
                        break;
                    case CarDetailValue.查看车辆:
                        if(getP().getU().canGo()){
                            getP().getD().updateCar(getP().getU().getUpdateCarReq(getP().getD().getUpdateCarReq(getP().getD().getCarinfo())), new UINetAdapter<UpdateCarRes>(this) {
                                @Override
                                public void onSuccess(UpdateCarRes o) {
                                    getArguments().putBoolean(ValueConstant.FARG_TYPE,true);
                                    getBaseUIAct().onBackPressed();
                                }
                            });
                        }
                        break;
                    case CarDetailValue.绑定车辆:
                        if(getP().getU().canGo()){
                            getP().getD().updateCar(getP().getU().getUpdateCarReq(getP().getD().getUpdateCarReq(getP().getD().getCarinfo())), new UINetAdapter<UpdateCarRes>(getContext()) {
                                @Override
                                public void onResult(boolean success, String msg, UpdateCarRes o) {
                                    super.onResult(success, msg, o);
                                    if(success){
                                        getP().getD().bindCar(getP().getD().getCarinfo().getVehicleId(), LocalValue.get登录返回信息().getUserId(), new UINetAdapter<BindCarRes>(DetailFrag.this) {
                                            @Override
                                            public void onSuccess(BindCarRes o) {
                                                ((MainAct) getBaseUIAct()).getP().getD().getMyceFrag().initUINET();
                                                getBaseUIAct().onBackPressed();
                                            }
                                        });
                                    }
                                }
                            });
                        }
                        break;
                    case CarDetailValue.新建车辆并绑定:
                        if(getP().getU().canNewGo()){
                            getP().getD().newCar(getP().getU().getCarNewReqBean(getP().getD().getCarNewReqBean(getP().getD().getCarinfo())), new UINetAdapter<CarNewResBean>(this) {
                                @Override
                                public void onSuccess(CarNewResBean o) {
                                    CarInfoReq req = new CarInfoReq();
                                    req.setIsApp(1);
                                    req.setCarLicenseNo(StringUtil.getStr(getArguments().getString(ValueConstant.DATA_DATA)));
                                    getP().getD().infoCar(req, new UINetAdapter<CarsResBean.CarInfoRes>(DetailFrag.this) {
                                        @Override
                                        public void onSuccess(CarsResBean.CarInfoRes o) {
                                            getP().getD().bindCar(o.getVehicleId(), LocalValue.get登录返回信息().getUserId(), new UINetAdapter<BindCarRes>(DetailFrag.this) {
                                                @Override
                                                public void onSuccess(BindCarRes o) {
                                                    ((MainAct) getBaseUIAct()).getP().getD().getMyceFrag().initUINET();
                                                    getBaseUIAct().onBackPressed();
                                                }
                                            });
                                        }
                                    });
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

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                int pos = (int) v.getTag(R.id.position);
                DriverRes driverRes = (DriverRes) v.getTag(R.id.data);
                for(int i=0;getP().getD().getDriverRes()!=null&&i<getP().getD().getDriverRes().size();i++){
                    if(pos==i){
                        getP().getD().getDriverRes().get(i).setIsCurrentDriver(DriverRes.是当前驾驶员);
                        getP().getD().bindCar(getP().getD().getCarinfo().getVehicleId(), driverRes.getUserId(), new UINetAdapter<BindCarRes>(getActivity()) {
                            @Override
                            public void onSuccess(BindCarRes o) {
                                super.onSuccess(o);
                            }
                        });
                    }else{
                        getP().getD().getDriverRes().get(i).setIsCurrentDriver(DriverRes.不当前驾驶员);
                    }
                }
                getP().getU().notifyDataSetChanged();
                break;
        }
    }


    public void bindCar(int userid){
        if(getP().getD().getCarinfo().getVehicleId()==-1){
            return;
        }
        getP().getD().bindCar(getP().getD().getCarinfo().getVehicleId(), userid, new UINetAdapter<BindCarRes>(getActivity()) {
            @Override
            public void onSuccess(BindCarRes o) {
                super.onSuccess(o);
            }
        });
    }
}
