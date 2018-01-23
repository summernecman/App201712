package com.siweisoft.heavycenter.module.main.store.detail;

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
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.databinding.FragMainStoreBinding;
import com.siweisoft.heavycenter.databinding.FragMainStoreDetailBinding;

import java.util.List;

public class DetailUIOpe extends BaseUIOpe<FragMainStoreDetailBinding>{

    public DetailUIOpe(Context context) {
        super(context);
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

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_store_detail, BR.item_main_store_detail, s));
    }

    public void initUI( StoreDetail storeDetail){
        bind.setVariable(BR.frag_main_store_detail,storeDetail);
        bind.store.setTxt(storeDetail.getMinStock(),storeDetail.getProductMinStock(),storeDetail.getMaxStock(),storeDetail.getProductMaxStock(),storeDetail.getCurrentStock());
    }
}
