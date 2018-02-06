package com.siweisoft.heavycenter.module.main.trans.detail;

//by summer on 2017-12-18.

import android.content.Context;

import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.trans.detail.TransDetailRes;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.databinding.FragMainTransDetailBinding;

public class TransDetailUIOpe extends AppUIOpe<FragMainTransDetailBinding> {




    public void initUI(TransDetailRes data){
        bind.setVariable(BR.frag_main_trans_detail,data);
        if(data.getDeliverRecordList().size()>0){
            bind.tvFhmz.setText(data.getDeliverRecordList().get(0).getGross());
            bind.tvFhpz.setText(data.getDeliverRecordList().get(0).getTare());
            bind.tvFhck.setText(data.getDeliverRecordList().get(0).getFhDeduct());

        }

        if(data.getDeliverRecordList().size()>0){
            bind.tvFhmz.setText(data.getDeliverRecordList().get(0).getGross());
            bind.tvFhpz.setText(data.getDeliverRecordList().get(0).getTare());
            bind.tvFhck.setText(data.getDeliverRecordList().get(0).getFhDeduct());

        }
    }
}
