package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.baidu.mapapi.map.BaiduMap;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.databinding.FragMainTransBinding;
import com.siweisoft.heavycenter.databinding.ItemTransBinding;

import java.util.List;

public class TransUIOpe extends BaseUIOpe<FragMainTransBinding>{



    public TransUIOpe(Context context) {
        super(context);
        //initRecycle();
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
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_trans, BR.item_main_trans, s,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                if(position%2==0){
                    holder.viewDataBinding.getRoot().setBackgroundColor(Color.RED);
                }else{
                    holder.viewDataBinding.getRoot().setBackgroundColor(Color.BLACK);
                }
            }
        });

        if(s.size()==0){
            bind.ivNodata.setVisibility(View.VISIBLE);
        }else{
            bind.ivNodata.setVisibility(View.GONE);
        }
    }

    public void search(OnFinishListener onFinishListener){
        if(bind.title.getRightIV2().isSelected()){
            bind.title.getRightIV2().setSelected(false);
            onFinishListener.onFinish(false);
        }else{
            bind.title.getRightIV2().setSelected(true);
            onFinishListener.onFinish(true);
        }

    }



}
