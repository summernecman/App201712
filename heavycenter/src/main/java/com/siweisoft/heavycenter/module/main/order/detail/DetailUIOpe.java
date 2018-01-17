package com.siweisoft.heavycenter.module.main.order.detail;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.bean.AppViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMainOrderBeginBinding;
import com.siweisoft.heavycenter.databinding.FragMainOrderDetailBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderBeginBinding;

import java.util.List;

public class DetailUIOpe extends AppUIOpe<FragMainOrderDetailBinding>{


    public DetailUIOpe(Context context) {
        super(context);
        initRecycle();
    }



    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }


}
