package com.siweisoft.heavycenter.module.mana.car.my;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.daimajia.swipe.SwipeLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragManaCarMyBinding;
import com.siweisoft.heavycenter.databinding.ItemManaCarMyBinding;

import java.util.List;

public class MyUIOpe extends AppUIOpe<FragManaCarMyBinding>{


    public MyUIOpe(Context context) {
        super(context);
        initRecycle();
    }



    private void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s, ViewListener listener){
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_car_my, BR.item_mana_car_my,s,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemManaCarMyBinding binding = (ItemManaCarMyBinding) holder.viewDataBinding;
                binding.llContent.setTag(com.android.lib.R.id.data, list.get(position));
                binding.llContent.setTag(com.android.lib.R.id.position, position);
                binding.llContent.setOnClickListener(this);

                binding.executePendingBindings();//加一行，问题解决

                binding.swipe.setShowMode(SwipeLayout.ShowMode.PullOut);
                binding.swipe.addDrag(SwipeLayout.DragEdge.Right,binding.bottomWrapper);
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
}
