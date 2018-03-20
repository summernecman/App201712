package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.BaseTextWather;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.GlideApp;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarReq;
import com.siweisoft.heavycenter.data.netd.unit.dirvers.DriverRes;
import com.siweisoft.heavycenter.databinding.FragManaCarDetailBinding;
import com.siweisoft.heavycenter.databinding.FragManaCarDetailTitleBinding;
import com.siweisoft.heavycenter.databinding.ItemManaCarDetailDriverBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailUIOpe extends AppUIOpe<FragManaCarDetailTitleBinding>{


    DetailCotentUIOpe detailCotentUIOpe;


    public DetailCotentUIOpe getDetailCotentUIOpe() {
        return detailCotentUIOpe;
    }

    @Override
    public void initDelay() {
        super.initDelay();
        detailCotentUIOpe = new DetailCotentUIOpe();
        detailCotentUIOpe.setFrag(getFrag());
        bind.fragManaCarDetailTitle.addView(detailCotentUIOpe.bind.getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void initTitle(String type){
        switch (type){
            case CarDetailValue.查看车辆:
                bind.title.getMidTV().setText("车辆详情");
                break;
            case CarDetailValue.新建车辆:
                bind.title.getMidTV().setText("新建车辆");
                break;
            case CarDetailValue.绑定车辆:
                bind.title.getMidTV().setText("绑定车辆");
                break;
            case CarDetailValue.新建车辆并绑定:
                bind.title.getMidTV().setText("新建车辆");
                break;
                default:
                    break;

        }
    }


    public void LoadListData(final ArrayList<DriverRes> o, ViewListener listener) {
        getDetailCotentUIOpe().LoadListData(o,listener);
    }

    public void initData(String type,CarsResBean.CarInfoRes data){
        switch (type){
            case CarDetailValue.查看车辆:
            case CarDetailValue.绑定车辆:
                bind.title.getMidTV().setText(StringUtil.getStr(data.getCarLicenseNo()));
                break;
            case CarDetailValue.新建车辆:
                bind.title.getMidTV().setText(StringUtil.getStr(data.getCarLicenseNo()));
                break;
        }
        getDetailCotentUIOpe().initData(type,data);
    }

    public UpdateCarReq getUpdateCarReq(UpdateCarReq updateCarReq) {
        return getDetailCotentUIOpe().getUpdateCarReq(updateCarReq);
    }

    public void initPhoto(String vehiclephoto,String vehiclelicensephot0){
        getDetailCotentUIOpe().initPhoto(vehiclephoto,vehiclelicensephot0);
    }

    public boolean canGo(){
        return getDetailCotentUIOpe().canGo();
    }


    public CarNewReqBean getCarNewReqBean(CarNewReqBean carNewReqBean) {
        return getDetailCotentUIOpe().getCarNewReqBean(carNewReqBean);
    }

    public boolean canNewGo() {
        return getDetailCotentUIOpe().canNewGo();
    }


    public void notifyDataSetChanged(){
        getDetailCotentUIOpe().notifyDataSetChanged();
    }

    public boolean canSearchCar(){
        return getDetailCotentUIOpe().canSearchCar();
    }

    public String getCarNO(){
        return getDetailCotentUIOpe().getCarNO();
    }

    public void showCotent(boolean show){
        getDetailCotentUIOpe().showCotent(show);
    }

    public void whenClickReInput(){
        getDetailCotentUIOpe().whenClickReInput();
    }

    public void whenClickMakeSure(){
        getDetailCotentUIOpe().whenClickMakeSure();
    }

    public boolean nowReInput(){
        return getDetailCotentUIOpe().nowReInput();
    }
}
