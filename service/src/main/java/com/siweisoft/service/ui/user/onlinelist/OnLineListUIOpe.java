package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.BR;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragOnlinelistBinding;
import com.siweisoft.service.databinding.ItemUserBinding;
import com.siweisoft.service.netdb.NetValue;
import com.siweisoft.service.netdb.user.UserBean;

import java.util.ArrayList;

public class OnLineListUIOpe extends BaseUIOpe<FragOnlinelistBinding> {



    public void refresh() {
        if (bind.recycle.getAdapter() != null) {
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

    public void initList(final ArrayList<UserBean> data, final View.OnClickListener onClickListener) {

        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_user, BR.item_user, data) {
            @Override
            public void onClick(View v) {
                super.onClick(v);
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            }

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemUserBinding viewDataBinding = (ItemUserBinding) holder.viewDataBinding;
                viewDataBinding.ivHead.setTag(R.id.data, data.get(position));
                viewDataBinding.ivHead.setOnClickListener(this);
                viewDataBinding.ivCall.setTag(R.id.data, data.get(position));
                viewDataBinding.ivCall.setOnClickListener(this);
                viewDataBinding.tvName.setText(NullUtil.isStrEmpty(data.get(position).getName()) ? data.get(position).getPhone() : StringUtil.getStr(data.get(position).getName()));
                viewDataBinding.ratingbar.setStar(data.get(position).getRate());
                GlideApp.with(context).asBitmap().centerInside().placeholder(R.drawable.icon_head1).placeholder(R.drawable.icon_head1).load(NetValue.获取文件路径("/" + data.get(position).getHeadurl())).into(viewDataBinding.ivHead);

                switch (data.get(position).getUsertype()) {
                    case UserBean.CUSTOME:
                        viewDataBinding.ivHeadType.setImageResource(R.drawable.icon_customer);
                        break;
                    case UserBean.ENGINEER:
                        viewDataBinding.ivHeadType.setImageResource(R.drawable.icon_engineer);
                        break;
                    case UserBean.SERVER:
                        viewDataBinding.ivHeadType.setImageResource(R.drawable.icon_server);
                        break;
                }

                int num = SPUtil.getInstance().getInt(data.get(position).getPhone());
                viewDataBinding.unacceptnum.setText(num == 0 ? "" : "" + num);
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
