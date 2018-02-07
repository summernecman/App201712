package com.siweisoft.heavycenter.module.mana.user.list;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.OjectUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.daimajia.swipe.SwipeLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUserResBean;
import com.siweisoft.heavycenter.databinding.FragManaUserBinding;
import com.siweisoft.heavycenter.databinding.ItemManaUserBinding;

import java.util.Date;
import java.util.List;

public class UserUIOpe extends AppUIOpe<FragManaUserBinding> {

    public final static String 解除绑定 = "解除绑定";

    public final static String 重新邀请 = "重新邀请";

    public final static String 指定新的超管 = "指定新的\n超管";

    private boolean swipe = true;


    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final UnitUserResBean o, ViewListener listener) {
        if(o==null || o.getResults()==null || o.getResults().size()==0){
            getFrag().showTips("暂无数据");
            return;
        }
        getFrag().removeTips();
        final List<UnitUserResBean.ResultsBean> data = o.getResults();
        final LoginResBean loginResBean = LocalValue.get登录返回信息();
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_user, BR.item_mana_user, data,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final ItemManaUserBinding binding = (ItemManaUserBinding) holder.viewDataBinding;

                if(data.get(position).getStatus()== UnitUserResBean.ResultsBean.STATUS_ONLINE){
                    binding.tvState.setText(UnitUserResBean.ResultsBean.STATUS_ONLINE_CN);
                    binding.ivHead.setSelected(true);
                }else{
                    binding.tvState.setText(UnitUserResBean.ResultsBean.STATUS_OFFLINE_CN);
                    binding.ivHead.setSelected(false);
                }

                switch (data.get(position).getBindCompanyState()){
                    case LoginResBean.BIND_UNIT_STATE_BINDED:
                        binding.tvState.setText(LoginResBean.BIND_UNIT_STATE_BINDED_CN);
                        break;
                    case LoginResBean.BIND_UNIT_STATE_CHECK:
                        binding.tvState.setText(LoginResBean.BIND_UNIT_STATE_CHECK_CN);
                        break;
                    case LoginResBean.BIND_UNIT_STATE_REJECT:
                        binding.tvState.setText(LoginResBean.BIND_UNIT_STATE_REJECT_CN);
                        break;
                    case LoginResBean.BIND_UNIT_STATE_UNBIND:
                        binding.tvState.setText(LoginResBean.BIND_UNIT_STATE_UNBIND_CN);
                        break;
                        default:
                            break;
                }


                binding.rlUser.setOnClickListener(this);
                binding.rlUser.setTag(R.id.position,position);
                binding.rlUser.setTag(R.id.data,data.get(position));


                binding.munu.setOnClickListener(this);
                binding.munu.setTag(R.id.position,position);
                binding.munu.setTag(R.id.data,data.get(position));
                binding.munu.setTag(R.id.data1,binding.swipe);
                binding.munu.setTag(R.id.type,0);
                if(data.get(position).getBindCompanyTime()!=null){
                    binding.tvDes.setText("由管理员审核于"+ StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(data.get(position).getBindCompanyTime()))));
                }

                switch (data.get(position).getBindCompanyState()){
                    case LoginResBean.BIND_UNIT_STATE_BINDED:
                        binding.munu.setText(解除绑定);
                        binding.munu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_red));
                        break;
                    default:
                        binding.munu.setText(重新邀请);
                        binding.munu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_yelll));
                        break;
                }

                if(OjectUtil.equals(loginResBean.getUserRole(),LoginResBean.USER_ROLE_SUPER_ADMIN)&&
                        OjectUtil.equals(data.get(position).getUserId(),loginResBean.getUserId())){
                    binding.munu.setText(指定新的超管);
                    binding.munu.setBackgroundColor(context.getResources().getColor(R.color.color_hv_red));
                    binding.munu.setTag(R.id.type,1);
                }

                if(isSwipe()){
                    binding.swipe.setRightSwipeEnabled(true);
                    binding.swipe.addSwipeListener(new SwipeLayout.SwipeListener() {
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

                        }

                        @Override
                        public void onStartClose(SwipeLayout layout) {

                        }

                        @Override
                        public void onClose(SwipeLayout layout) {

                        }

                        @Override
                        public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

                        }

                        @Override
                        public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

                        }
                    });
                }else{
                    binding.swipe.setRightSwipeEnabled(false);
                }


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

    public boolean isSwipe() {
        return swipe;
    }

    public void setSwipe(boolean swipe) {
        this.swipe = swipe;
    }
}