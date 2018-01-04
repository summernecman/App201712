package com.siweisoft.heavycenter.module.main.store.check;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMainStoreCheckBinding;
import com.siweisoft.heavycenter.databinding.FragManaStoreListBinding;
import com.siweisoft.heavycenter.databinding.ItemMainStoreBinding;

import java.util.List;

public class CheckUIOpe extends AppUIOpe<FragMainStoreCheckBinding>{


    public CheckUIOpe(Context context) {
        super(context);
    }


    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_store_check, BR.item_main_store_check, s){

            int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
            int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        });
    }
}
