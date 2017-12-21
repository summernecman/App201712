package com.siweisoft.heavycenter.module.main.msg.sys;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.databinding.FragMainMsgSysBinding;

import java.util.List;

public class SysUIOpe extends BaseUIOpe<FragMainMsgSysBinding>{

    public SysUIOpe(Context context) {
        super(context);
        initRecycle();
    }


    private void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s){
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_order, BR.item_order,s));
    }

}
