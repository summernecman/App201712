package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.baidu.mapapi.map.BaiduMap;
import com.github.florent37.viewanimator.ViewAnimator;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransReq;
import com.siweisoft.heavycenter.databinding.FragMainTransBinding;
import com.siweisoft.heavycenter.databinding.ItemTransBinding;

import java.util.List;

public class TransUIOpe extends BaseUIOpe<FragMainTransBinding>{



    public TransUIOpe(Context context) {
        super(context);
        //initRecycle();
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s, final ViewListener listener) {
//        if(o==null || o.getResults()==null || o.getResults().size()==0){
//            getFrag().showTips("暂无数据");
//            return;
//        }
//        getFrag().removeTips();
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_trans, BR.item_main_trans, s,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                if(position%2==0){
                    holder.viewDataBinding.getRoot().setBackgroundColor(Color.RED);
                }else{
                    holder.viewDataBinding.getRoot().setBackgroundColor(Color.BLACK);
                }
            }
        });

        if(s.size()==0){
            bind.ivNodata.setVisibility(View.VISIBLE);
        }else{
            bind.ivNodata.setVisibility(View.GONE);
        }
    }

    public void search(OnFinishListener onFinishListener){
        if(bind.title.getRightIV2().isSelected()){
            bind.title.getRightIV2().setSelected(false);
            bind.search.getRoot().setVisibility(View.GONE);
            onFinishListener.onFinish(true);
        }else{
            bind.title.getRightIV2().setSelected(true);
            ViewAnimator.animate(bind.search.getRoot()).alpha(0,1).translationY(-bind.search.getRoot().getHeight(),0).accelerate().duration(300).start();
            bind.search.getRoot().setVisibility(View.VISIBLE);
            onFinishListener.onFinish(false);
        }

    }

    public void initRefresh(OnRefreshListener onRefreshListener,OnLoadmoreListener onLoadmoreListener){
        bind.refreshLayout.setOnRefreshListener(onRefreshListener);
        bind.refreshLayout.setOnLoadmoreListener(onLoadmoreListener);
    }

    public void finishRefresh(){
        bind.refreshLayout.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refreshLayout.finishLoadmore();
    }

    public void autoRefresh(){
        bind.refreshLayout.autoRefresh();
    }

    public void notifyDataSetChanged(){
        bind.recycle.getAdapter().notifyDataSetChanged();
    }


    public void refreshSearch(){
        bind.title.getRightIV2().setSelected(false);
        bind.search.getRoot().setVisibility(View.GONE);
    }


    public TransReq getTransReq(TransReq transReq) {
        transReq.setMateriel(bind.search.itemGood.getMidEtTxt());
        transReq.setCompanyName(bind.search.itemUnitname.getMidEtTxt());
        transReq.setCompanyAddress(bind.search.itemUnitaddr.getMidEtTxt());
        transReq.setCarLicenseNo(bind.search.itemUnitcar.getMidEtTxt());
        return transReq;
    }


}
