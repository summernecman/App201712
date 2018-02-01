package com.siweisoft.heavycenter.module.main.trans.detail;

//by summer on 2017-12-18.

import android.content.Context;

import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.databinding.FragMainTransDetailBinding;

public class TransDetailUIOpe extends AppUIOpe<FragMainTransDetailBinding> {


    public TransDetailUIOpe(Context context) {
        super(context);
    }


    public void initUI(TransRes.ResultsBean data){
        bind.setVariable(BR.frag_main_trans_detail,data);
    }
}
