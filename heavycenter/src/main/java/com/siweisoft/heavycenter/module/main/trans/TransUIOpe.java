package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.StringUtil;
import com.baidu.mapapi.map.BaiduMap;
import com.github.florent37.viewanimator.ViewAnimator;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransReq;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.databinding.FragMainTransBinding;
import com.siweisoft.heavycenter.databinding.ItemMainTransBinding;
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

    public void LoadListData(List<TransRes.ResultsBean> s, final ViewListener listener) {
//        if(o==null || o.getResults()==null || o.getResults().size()==0){
//            getFrag().showTips("暂无数据");
//            return;
//        }
//        getFrag().removeTips();
        final Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(ScreenUtil.最小DIMEN);
        bind.recycle.addItemDecoration(new RecyclerView.ItemDecoration() {


            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                int left = parent.getPaddingLeft();
                int right = parent.getWidth()-parent.getPaddingRight();
                for(int i=0;i<parent.getChildCount();i++){
                    View view = parent.getChildAt(i);
                    for(int j=0;j<(right-left)/5;j++){
                        c.drawRect(left+j*(10),view.getBottom(),left+j*(10)+5,view.getBottom()+ScreenUtil.最小DIMEN,paint);
                    }

                    for(int j=0;j<view.getHeight()/5;j++){
                        c.drawRect(ScreenUtil.最小DIMEN*90,view.getTop()+j*10, (float) (ScreenUtil.最小DIMEN*90.25),view.getTop()+j*10+5,paint);
                    }

                    c.drawCircle((float) (left+90.5*ScreenUtil.最小DIMEN),(float) (view.getBottom()+0.5*ScreenUtil.最小DIMEN),3*ScreenUtil.最小DIMEN,paint);
                }


            }
        });
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_trans, BR.item_main_trans, s,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                ItemMainTransBinding itemMainTransBinding = (ItemMainTransBinding) holder.viewDataBinding;
                if(position%2==0){
                    holder.viewDataBinding.getRoot().setBackgroundColor(Color.RED);
                }else{
                    holder.viewDataBinding.getRoot().setBackgroundColor(Color.BLACK);
                }
                itemMainTransBinding.btSure.setOnClickListener(this);
                itemMainTransBinding.btSure.setTag(R.id.position,position);
                itemMainTransBinding.btSure.setTag(R.id.data,list.get(position));
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
        if(bind.recycle.getAdapter()!=null){
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
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


    public void  clearSel() {
        bind.search.itemGood.getMidET().setText("");
        bind.search.itemUnitname.getMidET().setText("");
        bind.search.itemUnitaddr.getMidET().setText("");
        bind.search.itemUnitcar.getMidET().setText("");
    }

    public void setUnit(String unitname){
        bind.search.itemUnitname.getMidET().setText(StringUtil.getStr(unitname));
    }

}
