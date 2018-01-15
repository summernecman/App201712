package com.siweisoft.heavycenter.module.mana.user;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.bean.AppViewHolder;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUserResBean;
import com.siweisoft.heavycenter.databinding.FragManaUserBinding;
import com.siweisoft.heavycenter.databinding.ItemManaUserBinding;

import java.util.List;

public class UserUIOpe extends AppUIOpe<FragManaUserBinding> {

    public UserUIOpe(Context context) {
        super(context);
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final List<UnitUserResBean.ResultsBean> data) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_user, BR.item_mana_user, data){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemManaUserBinding binding = (ItemManaUserBinding) viewHolder.viewDataBinding;
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
                            if(data.get(position).getStatus()== UnitUserResBean.ResultsBean.STATUS_ONLINE){
                                binding.tvState.setText(UnitUserResBean.ResultsBean.STATUS_ONLINE_CN);
                                binding.ivHead.setSelected(true);
                            }else{
                                binding.tvState.setText(UnitUserResBean.ResultsBean.STATUS_OFFLINE_CN);
                                binding.ivHead.setSelected(false);
                        }
                            break;
                }
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

}
