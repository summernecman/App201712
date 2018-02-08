package com.siweisoft.heavycenter.module.mana.store;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.network.news.NetI;
import com.android.lib.util.StringUtil;
import com.daimajia.swipe.SwipeLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.status.StatusStoresReqBean;
import com.siweisoft.heavycenter.data.netd.mana.store.status.StatusStoresResBean;
import com.siweisoft.heavycenter.databinding.FragManaStoreBinding;
import com.siweisoft.heavycenter.databinding.ItemManaGoodBinding;
import com.siweisoft.heavycenter.databinding.ItemManaStoreBinding;
import com.siweisoft.heavycenter.module.view.MySwipeListener;

import java.util.List;

public class StoreUIOpe extends AppUIOpe<FragManaStoreBinding> {

    private boolean swipe = true;


    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final StoresResBean o, final ViewListener listener) {
        if(o==null || o.getResults()==null || o.getResults().size()==0){
            getFrag().showTips("暂无数据");
            return;
        }
        getFrag().removeTips();
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_store, BR.item_mana_store, o.getResults(),listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final ItemManaStoreBinding binding = (ItemManaStoreBinding) holder.viewDataBinding;
                binding.tvMaxstock.setText("最大库存:"+ StringUtil.getStr(o.getResults().get(position).getMaxStock())+"t");
                binding.tvMinstock.setText("安全库存:"+ StringUtil.getStr(o.getResults().get(position).getMinStock())+"t");

                switch (o.getResults().get(position).getStatus()){
                    case StoreDetail.STATUS_OFF:
                        binding.munu.setText(StoreDetail.STATUS_ON_CN);
                        binding.munu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_red));
                        break;
                    default:
                        binding.munu.setText(StoreDetail.STATUS_OFF_CN);
                        binding.munu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_yelll));
                        break;
                }

                binding.munu.setOnClickListener(this);
                binding.munu.setTag(R.id.position,position);
                binding.munu.setTag(R.id.data,o.getResults().get(position));
                binding.munu.setTag(R.id.data1,binding.swipe);

                binding.rlRoot.setOnClickListener(this);
                binding.rlRoot.setTag(R.id.position,position);
                binding.rlRoot.setTag(R.id.data,o.getResults().get(position));
                binding.swipe.setRightSwipeEnabled(isSwipe());

                binding.swipe.addSwipeListener(new MySwipeListener() {
                    @Override
                    public void onStartOpen(SwipeLayout layout) {
                        for(int i=0;i<bind.recycle.getChildCount();i++){
                            SwipeLayout swipeLayout= bind.recycle.getChildAt(i).findViewById(R.id.swipe);
                            if(swipeLayout!=binding.swipe){
                                swipeLayout.close(true);
                            }
                        }
                    }
                });
            }


            @Override
            public void onClick(View v) {
                super.onClick(v);
                switch (v.getId()){
                    case R.id.munu:
                        SwipeLayout swipeLayout = (SwipeLayout) v.getTag(R.id.data1);
                        swipeLayout.close(true);
                        break;
                }
            }
        });


        bind.recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        for(int i=0;i<recyclerView.getChildCount();i++){
                            SwipeLayout swipeLayout= recyclerView.getChildAt(i).findViewById(R.id.swipe);
                            swipeLayout.close(true);
                        }
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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

    public void autoRefresh(int delay){
        bind.refreshLayout.autoRefresh(delay);
    }


    public void statusStore(StatusStoresReqBean statusStoresReqBean){

    }


    public void notifyDataSetChanged(){
        if(bind.recycle.getAdapter()!=null){
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

    public StatusStoresReqBean getStatusStoresReqBean(StatusStoresReqBean reqBean,int storeid, int  status) {
        reqBean.setId(storeid);
        switch (status){
            case StoreDetail.STATUS_OFF:
                status = StoreDetail.STATUS_ON;
                break;
            case StoreDetail.STATUS_ON:
                status = StoreDetail.STATUS_OFF;
                break;
        }
        reqBean.setStatus(status);
        return reqBean;
    }


    public boolean isSwipe() {
        return swipe;
    }

    public void setSwipe(boolean swipe) {
        this.swipe = swipe;
    }
}
