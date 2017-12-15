package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.baidu.mapapi.map.BaiduMap;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.databinding.FragTransBinding;

import java.util.List;

public class TransUIOpe extends BaseUIOpe<FragTransBinding>{

    public static final int 地图状态_DESTROY = 0;

    public static final int 地图状态_ONRESUME = 1;

    public static final int 地图状态_ONPAUSE = 2;


    public TransUIOpe(Context context) {
        super(context);
        //initRecycle();
    }

    public void 设置地图状态(int status){
        switch (status){
            case 地图状态_DESTROY:
                bind.bmapView.onDestroy();
                break;
            case 地图状态_ONRESUME:
                bind.bmapView.onResume();
                break;
            case 地图状态_ONPAUSE:
                bind.bmapView.onPause();
                break;
        }
    }

    public void initRecycle(){
        bind.drawerview.getRecyclerView().setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s) {
        bind.drawerview.getRecyclerView().setAdapter(new AppsDataBindingAdapter(context, R.layout.item_order, BR.item_order, s));
    }

    public void ddd(){

    }


    public BaiduMap getMap(){
        return bind.bmapView.getMap();
    }
}
