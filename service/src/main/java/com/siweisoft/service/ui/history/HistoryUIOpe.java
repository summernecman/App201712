package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.BR;
import com.android.lib.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.HistoryBean;
import com.siweisoft.service.databinding.FragHistoryBinding;
import com.siweisoft.service.databinding.ItemHistoryBinding;
import com.siweisoft.service.data.netd.NetValue;

import java.util.ArrayList;

public class HistoryUIOpe extends BaseUIOpe<FragHistoryBinding> {


    public void initList(final ArrayList<HistoryBean> data, ViewListener listener) {
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_history, BR.item_history, data, listener) {
                @Override
                public void onBindViewHolder(AppViewHolder holder, int position) {
                    ItemHistoryBinding viewDataBinding = (ItemHistoryBinding) holder.viewDataBinding;
                    viewDataBinding.getRoot().setTag(com.android.lib.R.id.data, list.get(position));
                    viewDataBinding.getRoot().setTag(com.android.lib.R.id.position, position);
                    viewDataBinding.getRoot().setOnClickListener(this);
                    viewDataBinding.getRoot().setOnLongClickListener(this);
                    viewDataBinding.setVariable(vari, data.get(position));
                    viewDataBinding.tvVideonum.setText(StringUtil.getStr(data.get(position).getNum()));
                    GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(NetValue.获取文件路径("/" + data.get(position).getUserBean().getHeadurl())).into(viewDataBinding.ivHead);
                    if (NullUtil.isStrEmpty(data.get(position).getUserBean().getName())) {
                        viewDataBinding.tvTophone.setText(data.get(position).getUserBean().getPhone());
                    } else {
                        viewDataBinding.tvTophone.setText(data.get(position).getUserBean().getName());
                    }
//                    viewDataBinding.tvTophone.setText(data.get(position).get(0).getTophone());
//                    viewDataBinding.tvDate.setText(data.get(position).get(0).getCreated());
                }
            });
    }

    public void initRefresh(OnRefreshListener refreshListener) {
        bind.refreshLayout.setOnRefreshListener(refreshListener);
    }




    public void finishRefresh(){
        bind.refreshLayout.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refreshLayout.finishLoadmore();
    }
}
