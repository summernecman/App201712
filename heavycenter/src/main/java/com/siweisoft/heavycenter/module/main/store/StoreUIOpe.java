package com.siweisoft.heavycenter.module.main.store;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.status.StatusStoresReqBean;
import com.siweisoft.heavycenter.databinding.FragMainStoreBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDoneBinding;
import com.siweisoft.heavycenter.databinding.ItemMainStoreBinding;

import java.util.List;

public class StoreUIOpe extends BaseUIOpe<FragMainStoreBinding>{

    public StoreUIOpe(Context context) {
        super(context);
    }


    public void initRefresh(OnRefreshListener refreshListener, OnLoadmoreListener loadmoreListener){
        bind.refreshLayout.setOnRefreshListener(refreshListener);
        bind.refreshLayout.setOnLoadmoreListener(loadmoreListener);
    }

    public void finishRefresh(){
        bind.refreshLayout.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refreshLayout.finishLoadmore();
    }

    public void autoRefresh(){
        bind.refreshLayout.autoRefresh();
    }


    public void autoRefresh(int delay){
        bind.refreshLayout.autoRefresh(delay);
    }



    public void notifyDataSetChanged(){
        if(bind.recycle.getAdapter()!=null){
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final StoresResBean o, ViewListener listener) {
        if(o==null || o.getResults()==null || o.getResults().size()==0){
            getFrag().showTips("暂无数据");
            return;
        }
        getFrag().removeTips();
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_store, BR.item_main_store, o.getResults(),listener){

            int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
            int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemMainStoreBinding storeBinding = (ItemMainStoreBinding) holder.viewDataBinding;
                storeBinding.getRoot().setSelected(position%2==0?true:false);
                storeBinding.tvCurrent.setText("剩余:"+StringUtil.getStr(o.getResults().get(position).getCurrentStock()));

                storeBinding.pvProgress.setValues(Math.min(o.getResults().get(position).getMaxStock(),o.getResults().get(position).getProductMaxStock()),
                        Math.max(o.getResults().get(position).getMinStock(),o.getResults().get(position).getProductMinStock()),
                        o.getResults().get(position).getCurrentStock());

            }
        });
    }
}
