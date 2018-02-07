package com.siweisoft.heavycenter.module.main.msg.msg;

//by summer on 2017-12-11.

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import android.view.ViewAnimationUtils;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.AnimUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.system.HandleUtil;
import com.github.florent37.viewanimator.ViewAnimator;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;
import com.siweisoft.heavycenter.databinding.FragMainMsgsMsgBinding;
import com.siweisoft.heavycenter.databinding.ItemMainMsgAllBinding;

import java.util.Date;

public class MsgUIOpe extends BaseUIOpe<FragMainMsgsMsgBinding>{



    public void initUI() {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final MsgsResBean o, ViewListener listener){
        if(o==null || o.getResults()==null || o.getResults().size()==0){
            getFrag().showTips("暂无数据");
            return;
        }
        getFrag().removeTips();
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_msg_all, BR.item_main_msg_all,o.getResults(),listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                if(o.getResults()==null || o.getResults().size()==0){
                    return;
                }
                ItemMainMsgAllBinding binding = (ItemMainMsgAllBinding) holder.viewDataBinding;
                binding.tvDes.setVisibility(View.GONE);
                binding.tvDate.setText(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(o.getResults().get(position).getSendTime())));
                switch (o.getResults().get(position).getAuditState()){
                    case MsgsResBean.ResultsBean.AUDITOR_STATE_CHECKING:
                        binding.llFuction.setVisibility(View.VISIBLE);
                        switch (o.getResults().get(position).getMessageType()){
                            case MsgsResBean.ResultsBean.MSG_TYPE_INVITE_M:
                                binding.btMana.setOnClickListener(this);
                                binding.btReject.setOnClickListener(this);
                                binding.btReject.setOnClickListener(this);
                                binding.btReject.setTag(R.id.data1,binding.llFuction);
                                binding.btMana.setTag(R.id.data,o.getResults().get(position));
                                binding.btMana.setTag(R.id.data1,binding.llFuction);
                                binding.btReject.setTag(R.id.data,o.getResults().get(position));
                                binding.btReject.setTag(R.id.data1,binding.llFuction);
                                binding.btMana.setTag(R.id.position,position);
                                binding.btReject.setTag(R.id.position,position);
                                binding.btReject.setTag(R.id.position,position);
                                break;
                            default:
                                binding.btAgree.setOnClickListener(this);
                                binding.btReject.setOnClickListener(this);
                                binding.btAgree.setTag(R.id.data1,binding.llFuction);
                                binding.btAgree.setTag(R.id.data,o.getResults().get(position));
                                binding.btReject.setOnClickListener(this);
                                binding.btReject.setTag(R.id.data1,binding.llFuction);
                                binding.btReject.setTag(R.id.data,o.getResults().get(position));
                                binding.btMana.setTag(R.id.data,o.getResults().get(position));
                                binding.btMana.setTag(R.id.data1,binding.llFuction);
                                binding.btMana.setTag(R.id.position,position);
                                binding.btReject.setTag(R.id.position,position);
                                binding.btReject.setTag(R.id.position,position);
                                break;

                        }
                        break;
                    case MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED:
                        binding.llFuction.setVisibility(View.VISIBLE);
                        binding.tvDes.setVisibility(View.VISIBLE);
                        binding.tvDes.setText(MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED_CN);
                        break;
                    case MsgsResBean.ResultsBean.AUDITOR_STATE_REJECT:
                        binding.llFuction.setVisibility(View.VISIBLE);
                        binding.tvDes.setVisibility(View.VISIBLE);
                        binding.tvDes.setText(MsgsResBean.ResultsBean.AUDITOR_STATE_REJECT_CN);
                        break;
                    case MsgsResBean.ResultsBean.AUDITOR_STATE_NONEED:
                        ViewAnimator.animate(binding.llFuction).height(binding.llFuction.getHeight(),0).accelerate().duration(400).start();
                        break;

                }

            }
        });
    }


    public void initRefresh(OnRefreshListener onRefreshListener,OnLoadmoreListener onLoadmoreListener){
        bind.refresh.setOnRefreshListener(onRefreshListener);
        bind.refresh.setOnLoadmoreListener(onLoadmoreListener);
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

    public void notifyDataSetChanged(){
        if(bind.recycle.getAdapter()!=null){
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

    public void notifyDataSetChanged(int pos){
        if(bind.recycle.getAdapter()!=null){
            bind.recycle.getAdapter().notifyItemChanged(pos);
        }
    }

    public void setBtnGone(final View view){
        ViewAnimator.animate(view).height(view.getHeight(),0).accelerate().duration(400).start();
        HandleUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 400);
    }

}
