package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.android.lib.util.NullUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.bind.BindCarReq;
import com.siweisoft.heavycenter.data.netd.mana.car.bind.BindCarRes;
import com.siweisoft.heavycenter.data.netd.mana.car.info.CarInfoReq;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarReq;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarRes;
import com.siweisoft.heavycenter.data.netd.unit.dirvers.DriverRes;
import com.siweisoft.heavycenter.data.netd.unit.dirvers.DriversReq;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadReqBean;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadResBean;

import org.xutils.common.util.KeyValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DetailDAOpe extends AppDAOpe {

    private CarsResBean.CarInfoRes carinfo = new CarsResBean.CarInfoRes();

    private UpdateCarReq updateCarReq = new UpdateCarReq();

    private CarInfoReq carInfoReq = new CarInfoReq();

    CarNewReqBean carNewReqBean = new CarNewReqBean();

    private String type = CarDetailValue.查看车辆;

    private ArrayList<DriverRes> driverRes = new ArrayList<>();


    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        return data;
    }

    public CarsResBean.CarInfoRes getCarinfo() {
        return carinfo;
    }

    public void setCarinfo(CarsResBean.CarInfoRes carinfo) {
        this.carinfo = carinfo;
    }


    public void updateHead(String path,String type, NetI<UpdateHeadResBean> adapter){
        List<KeyValue> keyValues = new ArrayList<>();
        keyValues.add(new KeyValue(UpdateHeadReqBean.用户id, LocalValue.get登录返回信息().getUserId()));
        keyValues.add(new KeyValue(UpdateHeadReqBean.文件类型,type));
        keyValues.add(new KeyValue(UpdateHeadReqBean.文件路径,new File(path)));
        NetDataOpe.User.uploadPhoto(getActivity(),keyValues,adapter);
    }

    public void updateCar(UpdateCarReq updateCarReq,NetI<UpdateCarRes> adapter){
        NetDataOpe.Mana.Car.updateCar(getActivity(),updateCarReq,adapter);
    }

    public UpdateCarReq getUpdateCarReq(CarsResBean.CarInfoRes carinfo) {
        updateCarReq.setId(carinfo.getVehicleId());
        updateCarReq.setCarLicenseNo(carinfo.getCarLicenseNo());
        updateCarReq.setVehiclePhoto(carinfo.getVehiclePhoto());
        updateCarReq.setVehicleLicensePhoto(carinfo.getVehicleLicensePhoto());
        updateCarReq.setEditer(LocalValue.get登录返回信息().getUserId());
        return updateCarReq;
    }


    public CarNewReqBean getCarNewReqBean(CarsResBean.CarInfoRes carinfo) {
        carNewReqBean.setVehicleLicensePhoto(carinfo.getVehicleLicensePhoto());
        carNewReqBean.setVehiclePhoto(carinfo.getVehiclePhoto());
        carNewReqBean.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        carNewReqBean.setCreater(LocalValue.get登录返回信息().getUserId());
        return carNewReqBean;
    }

    public void newCar(CarNewReqBean carNewReqBean, NetI<CarNewResBean> adapter){
        NetDataOpe.Mana.Car.newCar(getActivity(),carNewReqBean,adapter);
    }


    public void infoCar(CarInfoReq carInfoReq, NetI<CarsResBean.CarInfoRes> adapter){
        NetDataOpe.Mana.Car.infoCar(getActivity(),carInfoReq,adapter);
    }


    public void infoCar(int currentdriverid, NetI<CarsResBean.CarInfoRes> adapter){
//        BindCarReq bindCarReq = new BindCarReq();
//        bindCarReq.setEditer(LocalValue.get登录返回信息().getUserId());
//        bindCarReq.setCurrentDriver(currentdriverid);
//        bindCarReq.setId();
//        NetDataOpe.Mana.Car.bindCar(getActivity(),carInfoReq,adapter);
    }

    public void drvers(String type,CarsResBean.CarInfoRes info,NetI<ArrayList<DriverRes>> adapter){
        DriversReq driversReq = new DriversReq();
        driversReq.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        if(type.endsWith(CarDetailValue.新建车辆)){
            driversReq.setVehicleId(0);
        }else{
            driversReq.setVehicleId(info.getVehicleId());
        }
        NetDataOpe.Unit.drvers(getActivity(),driversReq,adapter);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if(NullUtil.isStrEmpty(type)){
            return;
        }
        this.type = type;
    }

    public CarInfoReq getCarInfoReq(CarsResBean.CarInfoRes carinfo) {
        carInfoReq.setId(carinfo.getVehicleId());
        carInfoReq.setCarLicenseNo(carinfo.getCarLicenseNo());
        carInfoReq.setIsApp(1);
        return carInfoReq;
    }

    public void bindCar(int carid,int currentdriverid,NetI<BindCarRes> adapter){
        BindCarReq bindCarReq = new BindCarReq();
        bindCarReq.setEditer(LocalValue.get登录返回信息().getUserId());
        bindCarReq.setId(carid);
        bindCarReq.setCurrentDriver(currentdriverid);
        NetDataOpe.Mana.Car.bindCar(getActivity(),bindCarReq,adapter);
    }


    public ArrayList<DriverRes> getDriverRes() {
        return driverRes;
    }
}
