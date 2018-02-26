package com.siweisoft.heavycenter.module.main.trans.detail;

//by summer on 2017-12-18.

import android.content.Context;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.trans.detail.TransDetailRes;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.databinding.FragMainTransDetailBinding;

public class TransDetailUIOpe extends AppUIOpe<FragMainTransDetailBinding> {




    public void initUI(TransDetailRes data){

        bind.recycle.setAdapter(new AppsDataBindingAdapter(context,R.layout.item_main_trans_detail,BR.item_main_trans_detail,null){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
            }

            @Override
            public int getItemCount() {
                return 10;
            }
        });


        if(data==null){
            return;
        }
        bind.setVariable(BR.frag_main_trans_detail,data);
        final String comname = LocalValue.get登录返回信息().getAbbreviationName();
        if(StringUtil.equals(comname,data.getDeveliverCompanyName())){
            bind.type.setText("发往");
            bind.type.setBackgroundResource(R.color.color_hv_yelll);
        }else{
            bind.type.setText("送来");
            bind.type.setBackgroundResource(R.color.color_hv_blue);
        }
        if(data.getDeliverRecordList().size()>0){
            bind.tvDelivenum.setText(StringUtil.getStr(data.getDeliverRecordList().get(0).getGross()));
            bind.tvReceipt.setText(StringUtil.getStr(data.getDeliverRecordList().get(0).getTare()));
            bind.tvYk.setText(StringUtil.getStr(data.getDeliverRecordList().get(0).getFhDeduct()));

        }

        if(data.getReceiveRecordList().size()>0){
            bind.tvDelivenum.setText(StringUtil.getStr(data.getReceiveRecordList().get(0).getGross()));
            bind.tvReceipt.setText(StringUtil.getStr(data.getReceiveRecordList().get(0).getTare()));
            bind.tvYk.setText(StringUtil.getStr(data.getReceiveRecordList().get(0).getShDeduct()));

        }



    }
}
