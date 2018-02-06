package com.siweisoft.heavycenter.module.myce.car.bind;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.BaseTextWather;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.daimajia.swipe.SwipeLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.databinding.FragMyceCarBindBinding;
import com.siweisoft.heavycenter.databinding.ItemManaCarMyBinding;

import java.util.List;

public class BindUIOpe extends AppUIOpe<FragMyceCarBindBinding>{
    CarsResBean cars;


    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void 实时搜索(final OnFinishListener listener){
        bind.search.getEditText().addTextChangedListener(new BaseTextWather(){
            @Override
            public void afterTextChanged(Editable s) {
                listener.onFinish(s.toString());
            }
        });
    }

    public void LoadListData(CarsResBean data,ViewListener listener){
        if(data==null){
            return;
        }
        this.cars = data;
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_car_my, BR.item_mana_car_my,cars.getResults(),listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemManaCarMyBinding binding = (ItemManaCarMyBinding) holder.viewDataBinding;
                binding.llContainer.setTag(com.android.lib.R.id.data, list.get(position));
                binding.llContainer.setTag(com.android.lib.R.id.position, position);
                binding.llContainer.setOnClickListener(this);
                binding.executePendingBindings();//加一行，问题解决
                binding.swipe.setRightSwipeEnabled(false);
                binding.swipe.setLeftSwipeEnabled(false);
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                switch (v.getId()){
                    case R.id.menu:
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
                            SwipeLayout swipeLayout = (SwipeLayout) recyclerView.getChildAt(i).findViewById(R.id.swipe);
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
    public CarsResBean getCars() {
        return cars;
    }

    public void setCars(CarsResBean cars) {
        this.cars = cars;
    }

    public void notifyDataSetChanged(){
        if(bind.recycle.getAdapter()!=null){
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

    public void initRefresh(OnRefreshListener refreshListener){
        bind.refreshLayout.setOnRefreshListener(refreshListener);
    }

    public boolean canSearchGo(){
        if(NullUtil.isStrEmpty(bind.search.getEditText().getText().toString())){
            ToastUtil.getInstance().showShort(getActivity(),"车牌号为输入");
            return false;
        }
        if(bind.search.getEditText().getText().toString().length()!=7){
            ToastUtil.getInstance().showShort(getActivity(),"车牌号输入有误");
            return false;
        }
        return true;
    }

    public String getInputText(){
        return bind.search.getEditText().getText().toString();
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


    public String getKeyWord(){
        return bind.search.getEditText().getText().toString();
    }

    public void clearKey(){
        bind.search.getEditText().setText("");
    }
}
