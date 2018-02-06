package com.siweisoft.heavycenter.module.mana.car.my;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
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

public class MyUIOpe extends AppUIOpe<FragManaCarMyBinding>{


    CarsResBean cars;

    @Override
    public void initUI(BaseUIFrag baseUIFrag) {
        super.initUI(baseUIFrag);
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
                binding.llContainer.setTag(com.android.lib.R.id.data, list.get(position));
                binding.llContainer.setTag(com.android.lib.R.id.position, position);
                binding.llContainer.setOnClickListener(this);

                binding.executePendingBindings();//加一行，问题解决
                if(CarsReqBean.WHAT_MY.equals(moudle)){
                    if(cars.getResults().get(position).getStatus()== CarsResBean.CarInfoRes.STATUS_OFF){
                        binding.menu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_yelll));
                        binding.menu.setText(CarsResBean.CarInfoRes.STATUS_ON_CN);
                    }else{
                        binding.menu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_red));
                        binding.menu.setText(CarsResBean.CarInfoRes.STATUS_OFF_CN);
                    }
                    binding.swipe.setShowMode(SwipeLayout.ShowMode.PullOut);
                    binding.swipe.addDrag(SwipeLayout.DragEdge.Right,binding.menu);
                    binding.menu.setOnClickListener(this);
                    binding.menu.setTag(R.id.data,list.get(position));
                    binding.menu.setTag(R.id.data1,binding.swipe);
                    binding.menu.setTag(R.id.position,position);
                    binding.swipe.addSwipeListener(new SwipeLayout.SwipeListener() {
                        @Override
                        public void onClose(SwipeLayout layout) {
                            //when the SurfaceView totally cover the BottomView.
                        }

                        @Override
                        public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                            //you are swiping.
                        }

                        @Override
                        public void onStartOpen(SwipeLayout layout) {
                            for(int i=0;i<bind.recycle.getChildCount();i++){
                                SwipeLayout swipeLayout= bind.recycle.getChildAt(i).findViewById(R.id.swipe);
                                if(swipeLayout!=binding.swipe){
                                    swipeLayout.close(true);
                                }
                            }
                        }

                        @Override
                        public void onOpen(SwipeLayout layout) {
                            //when the BottomView totally show.
                        }

                        @Override
                        public void onStartClose(SwipeLayout layout) {

                        }

                        @Override
                        public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                            //when user's hand released.
                        }
                    });
                }
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
}
