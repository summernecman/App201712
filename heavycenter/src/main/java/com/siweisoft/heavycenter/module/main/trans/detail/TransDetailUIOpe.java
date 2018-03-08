package com.siweisoft.heavycenter.module.main.trans.detail;

//by summer on 2017-12-18.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.trans.detail.TransDetailRes;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.databinding.FragMainTransDetailBinding;
import com.siweisoft.heavycenter.module.main.trans.detail.detail.TransDetailRecordFrag;

import java.util.ArrayList;
import java.util.List;

public class TransDetailUIOpe extends AppUIOpe<FragMainTransDetailBinding> {




    public void initUI(TransDetailRes data){

        if(data==null){
            return;
        }
        bind.setVariable(BR.frag_main_trans_detail,data);
        if(data.getDeliverRecordList().size()>0){
            bind.tvDelivenum.setText(StringUtil.getStr(data.getDeliverRecordList().get(0).getGross()));
            bind.tvReceipt.setText(StringUtil.getStr(data.getDeliverRecordList().get(0).getTare()));
            bind.tvYk.setText(StringUtil.getStr(data.getDeliverRecordList().get(0).getDeduct()));

        }

        if(data.getReceiveRecordList().size()>0){
            bind.tvDelivenum.setText(StringUtil.getStr(data.getReceiveRecordList().get(0).getGross()));
            bind.tvReceipt.setText(StringUtil.getStr(data.getReceiveRecordList().get(0).getTare()));
            bind.tvYk.setText(StringUtil.getStr(data.getReceiveRecordList().get(0).getDeduct()));

        }

        List<Fragment> fragments = new ArrayList<>();

        if(data.getDeliverRecordList()!=null&&data.getDeliverRecordList().size()!=0){
            fragments.add( TransDetailRecordFrag.getInstance(data.getDeliverRecordList()));
        }
        if(data.getReceiveRecordList()!=null&&data.getReceiveRecordList().size()!=0){
            fragments.add( TransDetailRecordFrag.getInstance(data.getReceiveRecordList()));
        }

        bind.tvCarno.setText("第"+NullUtil.isEmpty(data.getCarNumber(),1)+"车");
        bind.viewpager.setAdapter(new AppBasePagerAdapter2(getFrag().getChildFragmentManager(),getActivity(),fragments));
    }
}
