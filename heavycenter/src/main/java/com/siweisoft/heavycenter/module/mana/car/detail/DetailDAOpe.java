package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarReq;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarRes;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadReqBean;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadResBean;

import org.xutils.common.util.KeyValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DetailDAOpe extends AppDAOpe {

    private CarsResBean.ResultsBean carinfo;

    private UpdateCarReq updateCarReq = new UpdateCarReq();

    public DetailDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        return data;
    }

    public CarsResBean.ResultsBean getCarinfo() {
        return carinfo;
    }

    public void setCarinfo(CarsResBean.ResultsBean carinfo) {
        getUpdateCarReq().setCarLicenseNo(carinfo.getCarLicenseNo());
        getUpdateCarReq().setId(carinfo.getVehicleId());
        this.carinfo = carinfo;
    }


    public void updateHead(String path,String type, NetI<UpdateHeadResBean> adapter){
        List<KeyValue> keyValues = new ArrayList<>();
        keyValues.add(new KeyValue(UpdateHeadReqBean.用户id, LocalValue.getLoginInfo().getUserId()));
        keyValues.add(new KeyValue(UpdateHeadReqBean.文件类型,type));
        keyValues.add(new KeyValue(UpdateHeadReqBean.文件路径,new File(path)));
        NetDataOpe.User.uploadPhoto(getActivity(),keyValues,adapter);
    }

    public void updateCar(UpdateCarReq updateCarReq,NetI<UpdateCarRes> adapter){
        NetDataOpe.Mana.Car.updateCar(getActivity(),updateCarReq,adapter);
    }

    public UpdateCarReq getUpdateCarReq() {
        updateCarReq.setEditer(LocalValue.getLoginInfo().getUserId());
        return updateCarReq;
    }
}
