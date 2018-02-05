package com.siweisoft.heavycenter.module.main.weigts.detail;

//by summer on 2017-12-11.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.databinding.FragMainWeigtsDetailBinding;
import com.siweisoft.heavycenter.databinding.ItemMainWeightDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class DetailUIOpe extends AppUIOpe<FragMainWeigtsDetailBinding> {

    public DetailUIOpe(Context context) {
        super(context);
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final ArrayList<String> s) {
//        if(o==null || o.getResults()==null || o.getResults().size()==0){
//            getFrag().showTips("暂无数据");
//            return;
//        }
//        getFrag().removeTips();
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_weight_detail, BR.item_main_weight_detail, s){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemMainWeightDetailBinding binding = (ItemMainWeightDetailBinding) holder.viewDataBinding;
//                binding.tvTime.setText(StringUtil.getStr(s.get(position).getMessage().getTime()));
//                binding.tvTxt.setText(StringUtil.getStr(s.get(position).getMessage().getContent()));
                binding.tvTxt.setText(StringUtil.getStr(s));
            }
        });
    }

    public void initTopUI(WeightMsg.MessageBean data){
        if(data==null||data.getOrder()==null){
            return;
        }
        bind.tvOrderandtransno.setText("订单"+StringUtil.getStr(data.getOrder().getOrderNo())+"     运输单:"+StringUtil.getStr(data.getOrder().getYsdNo()));
        bind.tvGoodnameanspes.setText(StringUtil.getStr(data.getOrder().getProductName())+"        "+StringUtil.getStr(data.getOrder().getSpecification()));
        bind.tvFrom.setText(StringUtil.getStr(data.getOrder().getFhdwName()));
        bind.tvTo.setText(StringUtil.getStr(data.getOrder().getShdwName()));
    }

    public void  notifyDataSetChanged(){
        if(bind.recycle.getAdapter()!=null){
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

}
