package com.siweisoft.heavycenter.module.mana.car.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewResBean;
import com.siweisoft.heavycenter.data.netd.unit.dirvers.DriverRes;
import com.siweisoft.heavycenter.data.netd.unit.dirvers.DriversReq;

public class NewDAOpe extends AppDAOpe {

    CarNewReqBean carNewReqBean = new CarNewReqBean();


    public CarNewReqBean getCarNewReqBean() {
        carNewReqBean.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        carNewReqBean.setCreater(LocalValue.get登录返回信息().getUserId());
        return carNewReqBean;
    }

    public void newCar(CarNewReqBean carNewReqBean, NetI<CarNewResBean> adapter){
        NetDataOpe.Mana.Car.newCar(getActivity(),carNewReqBean,adapter);
    }



}
