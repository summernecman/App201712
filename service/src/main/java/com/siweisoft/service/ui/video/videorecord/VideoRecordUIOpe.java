package com.siweisoft.service.ui.video.videorecord;

//by summer on 17-08-23.

import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.BR;
import com.android.lib.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragVideorecordBinding;
import com.siweisoft.service.databinding.ItemVideorecordBinding;
import com.siweisoft.service.data.netd.NetValue;
import com.siweisoft.service.data.netd.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;

import java.util.List;

public class VideoRecordUIOpe extends BaseUIOpe<FragVideorecordBinding> {


    public void initRefresh(OnRefreshListener refreshListener, OnLoadmoreListener loadmoreListener) {
        bind.refreshLayout.setOnRefreshListener(refreshListener);
        bind.refreshLayout.setOnLoadmoreListener(loadmoreListener);
    }


    public void initList(final List<VideoBean> data, ViewListener viewListener, MyRecyclerView.OnScroll onScroll) {
        if (bind.recycle.getAdapter() == null) {
            bind.recycle.setLayoutManager(new LinearLayoutManager(context));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_videorecord, BR.item_videorecord, data, viewListener) {
                @Override
                public void onBindViewHolder(AppViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    ItemVideorecordBinding itemVideorecordBinding = (ItemVideorecordBinding) holder.viewDataBinding;
                    itemVideorecordBinding.tvVideotips.setText(StringUtil.getStr(data.get(position).getVideotips()));
                    holder.viewDataBinding.getRoot().findViewById(R.id.play).setTag(com.android.lib.R.id.data, data.get(position));
                    holder.viewDataBinding.getRoot().findViewById(R.id.iv_head).setTag(com.android.lib.R.id.data, data.get(position));
                    holder.viewDataBinding.getRoot().findViewById(R.id.iv_head).setTag(com.android.lib.R.id.position, position);
                    holder.viewDataBinding.getRoot().findViewById(R.id.iv_head).setOnClickListener(this);
                    holder.viewDataBinding.getRoot().findViewById(R.id.play).setOnClickListener(this);
                    ((ItemVideorecordBinding) holder.viewDataBinding).tvTimes.setText(StringUtil.secondToMinute(data.get(position).getTimenum()));
                    if (Value.getUserInfo().getPhone().equals(data.get(position).getFromUser().getPhone())) {
                        itemVideorecordBinding.ivWay.setSelected(false);
                        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(NetValue.获取文件路径("/" + data.get(position).getToUser().getHeadurl()) ).into(itemVideorecordBinding.ivHead);
                    } else {
                        itemVideorecordBinding.ivWay.setSelected(true);
                        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(NetValue.获取文件路径("/" + data.get(position).getFromUser().getHeadurl()) ).into(itemVideorecordBinding.ivHead);
                    }
                }
            });
            bind.recycle.setOnScroll(onScroll);
        } else {
            bind.recycle.getAdapter().notifyDataSetChanged();
        }

    }

    public void loadmore() {
        bind.recycle.getAdapter().notifyDataSetChanged();
    }


    public void finishRefresh(){
        bind.refreshLayout.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refreshLayout.finishLoadmore();
    }

}
