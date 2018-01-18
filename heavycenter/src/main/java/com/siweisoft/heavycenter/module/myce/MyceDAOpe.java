package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.status.StopCarReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.status.StopCarResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarReq;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarRes;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadReqBean;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadResBean;
import com.siweisoft.heavycenter.data.netd.user.info.UserInfoReqBean;

import org.xutils.common.util.KeyValue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyceDAOpe extends AppDAOpe {


    public MyceDAOpe(Context context) {
        super(context);
    }

    public void uploadPhoto(String path, String type, NetI<UpdateHeadResBean> adapter){
        List<KeyValue> keyValues = new ArrayList<>();
        keyValues.add(new KeyValue(UpdateHeadReqBean.用户id,LocalValue.getLoginInfo().getUserId()));
        keyValues.add(new KeyValue(UpdateHeadReqBean.文件类型,type));
        keyValues.add(new KeyValue(UpdateHeadReqBean.文件路径,new File(path)));
        NetDataOpe.User.uploadPhoto(getActivity(),keyValues,adapter);
    }

    public void updateHead(NetI<UpdateHeadResBean> adapter){
        UpdateHeadReqBean updateHeadReqBean = new UpdateHeadReqBean();
        updateHeadReqBean.setId(LocalValue.getLoginInfo().getUserId());
        updateHeadReqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        NetDataOpe.User.updatePhoto(getActivity(),updateHeadReqBean,adapter);
    }

    public void updateCar(NetI<UpdateCarRes> adapter){
        UpdateCarReq updateCarReq = new UpdateCarReq();
        updateCarReq.setId(LocalValue.getLoginInfo().getVehicleId());
        updateCarReq.setCarLicenseNo(LocalValue.getLoginInfo().getCarLicenseNo());
        updateCarReq.setCarBrand(LocalValue.getLoginInfo().getCarBrand());
        updateCarReq.setVehiclePhoto(LocalValue.getLoginInfo().getVehiclePhoto());
        updateCarReq.setVehicleLicensePhoto(LocalValue.getLoginInfo().getVehicleLicensePhoto());
        updateCarReq.setMaxCapacity(LocalValue.getLoginInfo().getMaxCapacity());
        updateCarReq.setEmptyWeight(LocalValue.getLoginInfo().getEmptyWeight());
        updateCarReq.setIcCard(LocalValue.getLoginInfo().getIcCard());
        updateCarReq.setEditer(LocalValue.getLoginInfo().getUserId());
        NetDataOpe.Mana.Car.updateCar(getActivity(),updateCarReq,adapter);
    }


    public void getInfo(NetI<LoginResBean> adapter){

        UserInfoReqBean userInfoReqBean = new UserInfoReqBean();
        userInfoReqBean.setId(LocalValue.getLoginInfo().getUserId());
        userInfoReqBean.setIsApp(1);
        NetDataOpe.User.getInfo(getActivity(),userInfoReqBean,adapter);
    }

}
