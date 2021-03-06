package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.BR;
import com.android.lib.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.databinding.FragUserinfoBinding;
import com.siweisoft.service.databinding.ItemRemarkBinding;
import com.siweisoft.service.data.netd.NetValue;
import com.siweisoft.service.data.netd.comment.CommentBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.data.netd.video.VideoTimeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UserInfoUIOpe extends BaseUIOpe<FragUserinfoBinding> {





    public void initTips(final HashMap<Integer, TipBean> data) {
//        if (data == null || data.keySet() == null || data.keySet().size() == 0) {
//            UserInfoDAOpe userInfoDAOpe = new UserInfoDAOpe(context);
//            final TipsBean tipsBean = userInfoDAOpe.getData();
//            bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
//            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, tipsBean.getTipBeen()) {
//            });
//            return;
//        }
        ArrayList<TipBean> tipBeen = new ArrayList<>();
        Iterator<Integer> iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            LogUtil.E("key:" + key + ":" + data.get(key).getTip() + ":" + data.get(key).getNum());
            tipBeen.add(new TipBean(key, data.get(key).getTip(), data.get(key).getNum()));
        }
        if (bind.recycle.getAdapter() == null) {
            bind.recycle.setLayoutManager(new GridLayoutManager(context, 4));
            bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_tip, BR.item_tip, tipBeen) {
                @Override
                public void onBindViewHolder(AppViewHolder holder, int position) {
                    super.onBindViewHolder(holder, position);
                    holder.viewDataBinding.getRoot().setSelected(true);
                }
            });
        } else {
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

    public void initRemarks(final ArrayList<CommentBean> data, ViewListener viewListener, MyRecyclerView.OnScroll onScroll) {
        bind.remarklist.setLayoutManager(new LinearLayoutManager(context));
        bind.remarklist.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_remark, BR.item_remark, data, viewListener) {
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemRemarkBinding itemRemarkBinding = (ItemRemarkBinding) holder.viewDataBinding;
                itemRemarkBinding.ivHead.setOnClickListener(this);
                itemRemarkBinding.ivHead.setTag(R.id.data, data.get(position));
                itemRemarkBinding.ivAgree.setOnClickListener(this);
                itemRemarkBinding.ivAgree.setTag(R.id.data, data.get(position));
                itemRemarkBinding.ivAgree.setTag(R.id.data1, itemRemarkBinding.tvNum);
                itemRemarkBinding.ivAgree.setSelected(data.get(position).isAgree());
                GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(NetValue.获取文件路径("/" + data.get(position).getFromUser().getHeadurl()) ).into(itemRemarkBinding.ivHead);
            }
        });
        bind.remarklist.setOnScroll(onScroll);
    }

    public void refreshRemarks() {
        if (bind.remarklist.getLayoutManager() == null || bind.remarklist.getAdapter() == null) {
            return;
        }
        bind.remarklist.getAdapter().notifyDataSetChanged();
    }


    public void initCallInfo(VideoTimeBean videoTimeBean) {
        bind.setCallinfo1(videoTimeBean);
    }

    public void initHead(UserBean userBean) {
        bind.ratingbar.setStar(userBean.getRate());
        bind.tvName.setText(NullUtil.isStrEmpty(userBean.getName()) ? userBean.getPhone() : StringUtil.getStr(userBean.getName()));
        bind.tvPhone.setText(userBean.getPhone());
        GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(NetValue.获取文件路径("/" + userBean.getHeadurl())  ).into(bind.ivHead11);
    }


    public void finishRefresh(){
        bind.refreshLayout.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refreshLayout.finishLoadmore();
    }

    public void initRefresh(OnRefreshListener refreshListener, OnLoadmoreListener loadmoreListener) {
        bind.refreshLayout.setOnRefreshListener(refreshListener);
        bind.refreshLayout.setOnLoadmoreListener(loadmoreListener);
    }

    public void initOnline(boolean online) {
        bind.call.setVisibility(online ? View.VISIBLE : View.GONE);
    }
}
