package com.siweisoft.heavycenter.module.main.order.begin;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.bean.AppViewHolder;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMainOrderBeginBinding;
import com.siweisoft.heavycenter.databinding.FragMyceMsgBinding;
import com.siweisoft.heavycenter.databinding.ItemOrderBinding;

import java.util.List;

public class BeginUIOpe extends AppUIOpe<FragMainOrderBeginBinding>{


    public BeginUIOpe(Context context) {
        super(context);
        initRecycle();
    }



    private void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s){
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_order, BR.item_order,s){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemOrderBinding binding = (ItemOrderBinding) holder.viewDataBinding;
                binding.swipe.addSwipeListener(new SimpleSwipeListener(){
                    @Override
                    public void onStartOpen(SwipeLayout layout) {
                        super.onStartOpen(layout);
                        for(int i=0;i<bind.recycle.getChildCount();i++){
                            SwipeLayout swipeLayout = (SwipeLayout) bind.recycle.getChildAt(i);
                            swipeLayout.close(true);
                        }
                    }
                });

            }
        });
        bind.recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        for(int i=0;i<recyclerView.getChildCount();i++){
                            SwipeLayout swipeLayout = (SwipeLayout) recyclerView.getChildAt(i);
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
}
