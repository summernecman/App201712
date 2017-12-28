package com.siweisoft.heavycenter.module.myce.unit.bind;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMyceUnitBindBinding;
import com.siweisoft.heavycenter.databinding.ItemMyceUnitBindBinding;
import com.siweisoft.heavycenter.databinding.ItemTransBinding;

import java.util.List;

public class BindUIOpe extends AppUIOpe<FragMyceUnitBindBinding>{


    public BindUIOpe(Context context) {
        super(context);
    }

    public void initRefresh(OnRefreshListener onRefreshListener){
        bind.refreshLayout.setOnRefreshListener(onRefreshListener);
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

    public void LoadListData(List<String> s, final ViewListener listener) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_myce_unit_bind, BR.item_myce_unit_bind, s,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                ItemMyceUnitBindBinding binding = (ItemMyceUnitBindBinding) holder.viewDataBinding;
                //binding.tvRoot.setOnClickListener(this);
            }
        });

    }
}
