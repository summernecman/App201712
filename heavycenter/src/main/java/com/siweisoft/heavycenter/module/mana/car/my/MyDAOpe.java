package com.siweisoft.heavycenter.module.mana.car.my;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.status.StopCarReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.status.StopCarResBean;

import java.util.ArrayList;

public class MyDAOpe extends AppDAOpe {



    public MyDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void Cars(String moudle,NetI<CarsResBean> adapter){
        CarsReqBean carsReqBean = new CarsReqBean();
        carsReqBean.setIsApp(1);
        carsReqBean.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        carsReqBean.setPageIndex(0);
        carsReqBean.setPageSize(1000);
        carsReqBean.setWhat(moudle);
        NetDataOpe.Mana.Car.Cars(getActivity(),carsReqBean,adapter);
    }


    public void statusCar(int carid, int status, NetI<StopCarResBean> adapter){
        StopCarReqBean reqBean = new StopCarReqBean();
        reqBean.setEditer(LocalValue.get登录返回信息().getUserId());
        reqBean.setId(carid);
        reqBean.setStatus(status== CarsResBean.CarInfoRes.STATUS_OFF? CarsResBean.CarInfoRes.STATUS_ON: CarsResBean.CarInfoRes.STATUS_OFF);
        NetDataOpe.Mana.Car.statusCar(getActivity(),reqBean,adapter);
    }

}
