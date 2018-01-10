package com.siweisoft.heavycenter.module.myce.name;

//by summer on 2017-12-19.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.rename.ReNameReqBean;
import com.siweisoft.heavycenter.databinding.FragMyceNameBinding;
import com.siweisoft.heavycenter.databinding.FragMyceSetAboutBinding;

public class NameUIOpe extends AppUIOpe<FragMyceNameBinding>{

    ReNameReqBean reNameReqBean  = new ReNameReqBean();

    public NameUIOpe(Context context) {
        super(context);
    }

    public ReNameReqBean getReNameReqBean() {
        reNameReqBean.setId(LocalValue.getLoginInfo().getUserId());
        reNameReqBean.setTrueName(bind.itemName.getMidET().getText().toString());
        return reNameReqBean;
    }
}
