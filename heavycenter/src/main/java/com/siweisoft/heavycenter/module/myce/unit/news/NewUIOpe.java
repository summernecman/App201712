package com.siweisoft.heavycenter.module.myce.unit.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.data.netd.unit.news.NewReqBean;
import com.siweisoft.heavycenter.databinding.FragMyceUnitNewBinding;

public class NewUIOpe extends AppUIOpe<FragMyceUnitNewBinding>{

    NewReqBean newReqBean;

    public NewUIOpe(Context context) {
        super(context);
    }

    public NewReqBean getNewReqBean() {
        newReqBean.setCompanyName(bind.itemUnitname.getMidET().getText().toString());
        newReqBean.setAbbreviationName(bind.itemNunitshortname.getMidET().getText().toString());
        newReqBean.setCompanyAddress(bind.unitaddr.getMidET().getText().toString());
        newReqBean.setContactPhone(bind.unitphone.getMidET().getText().toString());
        return newReqBean;
    }

    public void initUI(ListResBean.UnitInfo unit){
        bind.itemUnitname.getMidET().setText(StringUtil.getStr(unit.getCompanyName()));
        bind.itemNunitshortname.getMidTV().setText(StringUtil.getStr(unit.getAbbreviationName()));
        bind.upunit.getMidTV().setText(StringUtil.getStr(unit.getCompanyName()));
        bind.unitaddr.getMidTV().setText(StringUtil.getStr(unit.getCompanyAddress()));
        bind.area.getMidTV().setText(StringUtil.getStr(unit.getBelongArea()));

    }
}
