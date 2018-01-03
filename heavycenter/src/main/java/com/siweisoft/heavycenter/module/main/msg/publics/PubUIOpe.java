package com.siweisoft.heavycenter.module.main.msg.publics;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.databinding.FragMainMsgPublicBinding;

import java.util.List;

public class PubUIOpe extends BaseUIOpe<FragMainMsgPublicBinding>{

    public PubUIOpe(Context context) {
        super(context);
        initRecycle();
    }


    private void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s){
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_msg_public, BR.item_main_msg_public,s));
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
