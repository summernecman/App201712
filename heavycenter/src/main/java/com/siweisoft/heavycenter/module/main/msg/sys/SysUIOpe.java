package com.siweisoft.heavycenter.module.main.msg.sys;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.data.DateFormatUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;
import com.siweisoft.heavycenter.databinding.FragMainMsgSysBinding;
import com.siweisoft.heavycenter.databinding.ItemMainMsgAllBinding;

import java.util.Date;
import java.util.List;

public class SysUIOpe extends BaseUIOpe<FragMainMsgSysBinding>{

    public SysUIOpe(Context context) {
        super(context);
        initRecycle();
    }


    private void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final List<MsgsResBean.ResultsBean> data, ViewListener listener){
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_msg_all, BR.item_main_msg_all,data,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemMainMsgAllBinding binding = (ItemMainMsgAllBinding) holder.viewDataBinding;
                binding.llFuction.setVisibility(View.GONE);
                binding.tvDes.setVisibility(View.GONE);
                binding.tvDate.setText(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(data.get(position).getSendTime())));
                switch (data.get(position).getAuditState()){
                    case MsgsResBean.ResultsBean.AUDITOR_STATE_CHECKING:
                        switch (data.get(position).getMessageType()){
                            case MsgsResBean.ResultsBean.MSG_TYPE_APPLY_D:
                                break;
                            case MsgsResBean.ResultsBean.MSG_TYPE_APPLY_U:
                                binding.llFuction.setVisibility(View.VISIBLE);
                                binding.btAgree.setOnClickListener(this);
                                binding.btReject.setOnClickListener(this);
                                binding.btAgree.setTag(R.id.data,data.get(position));
                                binding.btReject.setOnClickListener(this);
                                binding.btReject.setTag(R.id.data,data.get(position));
                                break;
                            case MsgsResBean.ResultsBean.MSG_TYPE_INFO:
                                break;
                            case MsgsResBean.ResultsBean.MSG_TYPE_INVITE_D:
                                break;
                            case MsgsResBean.ResultsBean.MSG_TYPE_INVITE_M:
                                break;
                            case MsgsResBean.ResultsBean.MSG_TYPE_UPDATE:
                                break;
                            default:
                                break;

                        }
                        break;
                    case MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED:
                        binding.tvDes.setVisibility(View.VISIBLE);
                        binding.tvDes.setText(MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED_CN);
                        break;
                    case MsgsResBean.ResultsBean.AUDITOR_STATE_REJECT:
                        binding.tvDes.setVisibility(View.VISIBLE);
                        binding.tvDes.setText(MsgsResBean.ResultsBean.AUDITOR_STATE_REJECT_CN);
                        break;
                    case MsgsResBean.ResultsBean.AUDITOR_STATE_NONEED:
                        break;

                }

            }
        });
    }

    public void initRefresh(OnRefreshListener onRefreshListener,OnLoadmoreListener onLoadmoreListener){
        bind.refreshLayout.setOnRefreshListener(onRefreshListener);
        bind.refreshLayout.setOnLoadmoreListener(onLoadmoreListener);
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

    public void notifyDataSetChanged(){
        bind.recycle.getAdapter().notifyDataSetChanged();
    }

}
