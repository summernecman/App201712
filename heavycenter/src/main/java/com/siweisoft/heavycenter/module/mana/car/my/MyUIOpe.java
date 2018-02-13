package com.siweisoft.heavycenter.module.mana.car.my;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.daimajia.swipe.SwipeLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.databinding.FragManaCarMyBinding;
import com.siweisoft.heavycenter.databinding.ItemManaCarMyBinding;
import com.tubb.smrv.SwipeHorizontalMenuLayout;
import com.tubb.smrv.SwipeMenuLayout;

public class MyUIOpe extends AppUIOpe<FragManaCarMyBinding>{


    CarsResBean cars;

    public void initUI() {
        initRecycle();
    }

    private void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(CarsResBean data, final String moudle, ViewListener listener){
        if(data==null || data.getResults()==null || data.getResults().size()==0){
            getFrag().showTips("暂无数据");
            return;
        }
        getFrag().removeTips();
        this.cars = data;
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_car_my, BR.item_mana_car_my,cars.getResults(),listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final ItemManaCarMyBinding binding = (ItemManaCarMyBinding) holder.viewDataBinding;


                View content = binding.sml.getChildAt(1);
                TextView menu = (TextView) binding.sml.getChildAt(0);

                content.setOnClickListener(this);
                content.setTag(R.id.position,position);
                content.setTag(R.id.data,list.get(position));


                menu.setOnClickListener(this);
                menu.setTag(R.id.position,position);
                menu.setTag(R.id.data,list.get(position));
                menu.setTag(R.id.data1,binding.sml);
                menu.setTag(R.id.type,0);


                binding.executePendingBindings();//加一行，问题解决
                switch (moudle){
                    case CarsReqBean.WHAT_MY:
                        if(cars.getResults().get(position).getStatus()== CarsResBean.CarInfoRes.STATUS_OFF){
                            menu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_yelll));
                            menu.setText(CarsResBean.CarInfoRes.STATUS_ON_CN);
                            content.setSelected(true);
                        }else{
                            menu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_red));
                            menu.setText(CarsResBean.CarInfoRes.STATUS_OFF_CN);
                            content.setSelected(false);
                        }
                        binding.sml.setSwipeListener(new com.siweisoft.heavycenter.module.view.swipe.MySwipeListener(){
                            @Override
                            public void endMenuOpened(SwipeMenuLayout swipeMenuLayout) {
                                for(int i=0;i<bind.recycle.getChildCount();i++){
                                    SwipeHorizontalMenuLayout swipeLayout= (SwipeHorizontalMenuLayout) bind.recycle.getChildAt(i);
                                    if(swipeLayout!=binding.sml){
                                        swipeLayout.smoothCloseMenu(400);
                                    }
                                }
                            }
                        });
                        break;
                        default:
                            content.setSelected(false);
                            binding.sml.setSwipeEnable(false);
                            break;

                }
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                switch (v.getId()){
                    case R.id.smMenuViewRight:
                        SwipeHorizontalMenuLayout swipeLayout= (SwipeHorizontalMenuLayout) v.getTag(R.id.data1);
                        swipeLayout.smoothCloseMenu(400);
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
                            SwipeHorizontalMenuLayout swipeLayout= (SwipeHorizontalMenuLayout) recyclerView.getChildAt(i);
                            swipeLayout.smoothCloseMenu(400);
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

    public void initRefresh(OnRefreshListener refreshListener, OnLoadmoreListener loadmoreListener){
        bind.refresh.setOnRefreshListener(refreshListener);
        bind.refresh.setOnLoadmoreListener(loadmoreListener);
    }

    public void finishRefresh(){
        bind.refresh.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refresh.finishLoadmore();
    }

    public void autoRefresh(){
        bind.refresh.autoRefresh();
    }
}
