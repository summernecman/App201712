package com.siweisoft.service.ui.user.userlist;

//by summer on 17-09-06.

import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.BR;
import com.android.lib.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragUserlistBinding;
import com.siweisoft.service.databinding.ItemUser3Binding;
import com.siweisoft.service.data.netd.NetValue;
import com.siweisoft.service.data.netd.user.UserBean;

import java.util.ArrayList;

public class UserListUIOpe extends BaseUIOpe<FragUserlistBinding> {




    public void initList(final ArrayList<UserBean> data, final ViewListener viewListener) {
        for (int i = 0; i < data.size(); i++) {
            LogUtil.E(data.get(i).toString());
        }
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_user3, BR.item_user3, data, viewListener) {

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemUser3Binding viewDataBinding = (ItemUser3Binding) holder.viewDataBinding;
                viewDataBinding.tvName.setText(NullUtil.isStrEmpty(data.get(position).getName()) ? data.get(position).getPhone() : StringUtil.getStr(data.get(position).getName()));
                viewDataBinding.ratingbar.setStar(data.get(position).getAvg());
                GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).placeholder(R.drawable.icon_head1).load(NetValue.获取文件路径("/" + data.get(position).getHeadurl()) ).into(viewDataBinding.ivHead);
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
            }
        });
    }

    public void initRefresh(MaterialRefreshListener refreshListener) {
        bind.refresh.setMaterialRefreshListener(refreshListener);
    }
}
