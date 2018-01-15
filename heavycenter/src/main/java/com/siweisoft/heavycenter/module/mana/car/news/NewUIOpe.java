package com.siweisoft.heavycenter.module.mana.car.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewReqBean;
import com.siweisoft.heavycenter.databinding.FragManaCarDetailBinding;
import com.siweisoft.heavycenter.databinding.FragManaCarNewBinding;

public class NewUIOpe extends AppUIOpe<FragManaCarDetailBinding>{


    public NewUIOpe(Context context) {
        super(context);
    }

    public CarNewReqBean getCarNewReqBean(CarNewReqBean carNewReqBean) {
        carNewReqBean.setCarLicenseNo(bind.itemCarlicenseno.getMidET().getText().toString());
        carNewReqBean.setCarBrand(bind.itemBrand.getMidET().getText().toString());
        carNewReqBean.setMaxCapacity(Integer.parseInt(bind.itemMaxweight.getMidET().getText().toString()));
        carNewReqBean.setEmptyWeight(Integer.parseInt(bind.itemEmptyweight.getMidET().getText().toString()));
        carNewReqBean.setIcCard(bind.itemIccard.getMidET().getText().toString());
        return carNewReqBean;
    }

    public boolean canGo(){
        if(NullUtil.isStrEmpty(bind.itemCarlicenseno.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemBrand.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemMaxweight.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemEmptyweight.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemIccard.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        return true;
    }
}
