package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.android.lib.util.NullUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewResBean;
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

    CarNewReqBean carNewReqBean = new CarNewReqBean();

    private String type = DetailFrag.TYPE_DETAIL;

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


    public CarNewReqBean getCarNewReqBean() {
        carNewReqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        carNewReqBean.setCreater(LocalValue.getLoginInfo().getUserId());
        return carNewReqBean;
    }

    public void newCar(CarNewReqBean carNewReqBean, NetI<CarNewResBean> adapter){
        NetDataOpe.Mana.Car.newCar(getActivity(),carNewReqBean,adapter);
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
}
