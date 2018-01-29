package com.siweisoft.heavycenter.module.myce.unit.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.unit.news.NewReqBean;
import com.siweisoft.heavycenter.databinding.FragMyceUnitNewBinding;

public class NewUIOpe extends AppUIOpe<FragMyceUnitNewBinding>{

    NewReqBean newReqBean = new NewReqBean();

    public NewUIOpe(Context context) {
        super(context);
    }

    public NewReqBean getNewReqBean(UnitInfo unitInfo) {
        newReqBean.setCompanyName(bind.itemUnitname.getMidET().getText().toString());
        newReqBean.setAbbreviationName(bind.itemNunitshortname.getMidET().getText().toString());
        newReqBean.setCompanyAddress(bind.unitaddr.getMidET().getText().toString());
        newReqBean.setContactPhone(bind.unitphone.getMidET().getText().toString());
        newReqBean.setBelongArea(unitInfo.getBelongArea());
        newReqBean.setCompanyLat("0");
        newReqBean.setCompanyLng("0");
        newReqBean.setContactName(bind.unitcontact.getMidET().getText().toString());
        newReqBean.setCreater(LocalValue.get登录返回信息().getUserId());
        newReqBean.setHighCompany(unitInfo.getId()==0?-1:unitInfo.getId());
        return newReqBean;
    }

    public boolean canGo(){
        if(NullUtil.isStrEmpty(bind.itemUnitname.getMidET().getText().toString())){
            ToastUtil.getInstance().showShort(getActivity(),"单位名称为空");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemNunitshortname.getMidET().getText().toString())){
            ToastUtil.getInstance().showShort(getActivity(),"单位简称为空");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.unitaddr.getMidTV().getText().toString())){
            ToastUtil.getInstance().showShort(getActivity(),"单位地址为空");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.unitcontact.getMidET().getText().toString())){
            ToastUtil.getInstance().showShort(getActivity(),"联系人为空");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.unitphone.getMidET().getText().toString())){
            ToastUtil.getInstance().showShort(getActivity(),"联系电话为空");
            return false;
        }
        return true;
    }

    public void initUI(UnitInfo unit){
        bind.upunit.getMidTV().setText(StringUtil.getStr(unit.getParentCompanyName()));
        bind.unitaddr.getMidTV().setText(StringUtil.getStr(unit.getCompanyAddress()));
        bind.area.getMidTV().setText(StringUtil.getStr(unit.getBelongAreaDes()));

    }
}
