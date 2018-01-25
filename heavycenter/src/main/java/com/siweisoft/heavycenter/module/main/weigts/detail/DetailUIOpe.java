package com.siweisoft.heavycenter.module.main.weigts.detail;

//by summer on 2017-12-11.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMainWeigtsDetailBinding;

import java.util.List;

public class DetailUIOpe extends AppUIOpe<FragMainWeigtsDetailBinding> {

    public DetailUIOpe(Context context) {
        super(context);
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s) {
//        if(o==null || o.getResults()==null || o.getResults().size()==0){
//            getFrag().showTips("暂无数据");
//            return;
//        }
//        getFrag().removeTips();
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_weight_detail, BR.item_main_weight_detail, s){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
            }
        });
    }

}
