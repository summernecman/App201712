package com.siweisoft.heavycenter.module.main.store;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.databinding.FragMainStoreBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDoneBinding;
import com.siweisoft.heavycenter.databinding.ItemMainStoreBinding;

import java.util.List;

public class StoreUIOpe extends BaseUIOpe<FragMainStoreBinding>{

    public StoreUIOpe(Context context) {
        super(context);
    }

    public void initRefresh(){
        bind.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000);
            }
        });
        bind.refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s, ViewListener listener) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_store, BR.item_main_store, s,listener){

            int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
            int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemMainStoreBinding storeBinding = (ItemMainStoreBinding) holder.viewDataBinding;
                storeBinding.getRoot().setSelected(position%2==0?true:false);
            }
        });
    }
}
