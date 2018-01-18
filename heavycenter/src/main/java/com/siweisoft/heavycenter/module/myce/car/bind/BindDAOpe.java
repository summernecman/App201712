package com.siweisoft.heavycenter.module.myce.car.bind;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.bind.BindCarReq;
import com.siweisoft.heavycenter.data.netd.mana.car.bind.BindCarRes;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;

import java.util.ArrayList;

public class BindDAOpe extends AppDAOpe {

    BindCarReq bindCarReq  = new BindCarReq();

    public BindDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void Cars(NetI<CarsResBean> adapter){
        CarsReqBean carsReqBean = new CarsReqBean();
        carsReqBean.setIsApp(1);
        carsReqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        carsReqBean.setPageIndex(0);
        carsReqBean.setPageSize(1000);
        carsReqBean.setWhat(CarsReqBean.WHAT_MY);
        NetDataOpe.Mana.Car.Cars(getActivity(),carsReqBean,adapter);
    }

    public BindCarReq getBindCarReq() {
        return bindCarReq;
    }

    public void bindCar(int carid,NetI<BindCarRes> adapter){
        getBindCarReq().setCurrentDriver(LocalValue.getLoginInfo().getUserId());
        getBindCarReq().setId(carid);
        getBindCarReq().setEditer(LocalValue.getLoginInfo().getUserId());
        NetDataOpe.Mana.Car.bindCar(getActivity(),getBindCarReq(),adapter);
    }

}
