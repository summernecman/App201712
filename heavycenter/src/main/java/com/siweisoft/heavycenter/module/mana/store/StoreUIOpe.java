package com.siweisoft.heavycenter.module.mana.store;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.network.news.NetI;
import com.android.lib.util.StringUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.status.StatusStoresReqBean;
import com.siweisoft.heavycenter.data.netd.mana.store.status.StatusStoresResBean;
import com.siweisoft.heavycenter.databinding.FragManaStoreBinding;
import com.siweisoft.heavycenter.databinding.ItemManaGoodBinding;
import com.siweisoft.heavycenter.databinding.ItemManaStoreBinding;

import java.util.List;

public class StoreUIOpe extends AppUIOpe<FragManaStoreBinding> {

    public StoreUIOpe(Context context) {
        super(context);
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final StoresResBean o, final ViewListener listener) {
        if(o==null){
            return;
        }
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_store, BR.item_mana_store, o.getResults(),listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemManaStoreBinding binding = (ItemManaStoreBinding) holder.viewDataBinding;
                binding.tvMaxstock.setText("最大库存:"+ StringUtil.getStr(o.getResults().get(position).getMaxStock())+"t");
                binding.tvMinstock.setText("最大库存:"+ StringUtil.getStr(o.getResults().get(position).getMinStock())+"t");

                switch (o.getResults().get(position).getStatus()){
                    case StoresResBean.ResultsBean.STATUS_OFF:
                        binding.munu.setText(StoresResBean.ResultsBean.STATUS_ON_CN);
                        binding.munu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_red));
                        break;
                    default:
                        binding.munu.setText(StoresResBean.ResultsBean.STATUS_OFF_CN);
                        binding.munu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_yelll));
                        break;
                }

                binding.munu.setOnClickListener(this);
                binding.munu.setTag(R.id.position,position);
                binding.munu.setTag(R.id.data,o.getResults().get(position));
                binding.munu.setTag(R.id.data1,binding.swipe);

            }
        });
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


    public void statusStore(StatusStoresReqBean statusStoresReqBean){

    }


    public void notifyDataSetChanged(){
        bind.recycle.getAdapter().notifyDataSetChanged();
    }

    public StatusStoresReqBean getStatusStoresReqBean(StatusStoresReqBean reqBean,int storeid, int  status) {
        reqBean.setId(storeid);
        switch (status){
            case StoresResBean.ResultsBean.STATUS_OFF:
                status = StoresResBean.ResultsBean.STATUS_ON;
                break;
            case StoresResBean.ResultsBean.STATUS_ON:
                status = StoresResBean.ResultsBean.STATUS_OFF;
                break;
        }
        reqBean.setStatus(status);
        return reqBean;
    }

}
