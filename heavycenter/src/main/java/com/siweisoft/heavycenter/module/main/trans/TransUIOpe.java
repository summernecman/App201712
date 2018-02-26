package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.ObjectUtil;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.view.ItemDecoration.MyItemDecoration2;
import com.baidu.mapapi.map.BaiduMap;
import com.github.florent37.viewanimator.ViewAnimator;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.trans.detail.TransDetailRes;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransReq;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.databinding.FragMainTransBinding;
import com.siweisoft.heavycenter.databinding.ItemMainTrainReceiptBinding;
import com.siweisoft.heavycenter.databinding.ItemMainTrans2Binding;
import com.siweisoft.heavycenter.databinding.ItemMainTrans3Binding;
import com.siweisoft.heavycenter.databinding.ItemMainTransBinding;
import com.siweisoft.heavycenter.databinding.ItemMainTransSendBinding;
import com.siweisoft.heavycenter.databinding.ItemTransBinding;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class TransUIOpe extends BaseUIOpe<FragMainTransBinding>{





    public void initUI() {
        initRecycle();
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final List<TransDetailRes> s, final ViewListener listener) {
//        if(o==null || o.getResults()==null || o.getResults().size()==0){
//            getFrag().showTips("暂无数据");
//            return;
//        }
//        getFrag().removeTips();
//        final Paint paint = new Paint();
//        paint.setColor(Color.WHITE);
//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(ScreenUtil.最小DIMEN);
//        bind.recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
//
//
//            @Override
//            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//                super.onDrawOver(c, parent, state);
//                int left = parent.getPaddingLeft();
//                int right = parent.getWidth()-parent.getPaddingRight();
//                paint.setColor(context.getResources().getColor(R.color.color_grey_700));
//                for(int i=0;i<parent.getChildCount();i++){
//                    View view = parent.getChildAt(i);
//                    for(int j=0;j<(right-left)/3;j++){
//                        c.drawRect(left+j*(6),view.getBottom(),left+j*(6)+3,view.getBottom()+ScreenUtil.最小DIMEN,paint);
//                    }
//
//                    for(int j=0;j<view.getHeight()/3;j++){
//                        c.drawRect(ScreenUtil.最小DIMEN*120,view.getTop()+j*3, (float) (ScreenUtil.最小DIMEN*120.25),view.getTop()+j*6+3,paint);
//                    }
//                    paint.setColor(Color.WHITE);
//                    c.drawCircle((float) (left+120.5*ScreenUtil.最小DIMEN),(float) (view.getBottom()+0.5*ScreenUtil.最小DIMEN),5*ScreenUtil.最小DIMEN,paint);
//                }
//
//
//            }
//        });

        final String comname = LocalValue.get登录返回信息().getAbbreviationName();
        final int usertype = LocalValue.get登录返回信息().getUserType();
        final DecimalFormat df = new DecimalFormat("#.##");
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_trans, BR.item_main_trans, s,listener){

            @Override
            public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if(viewType==0){
                    return new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main_trans_send, parent, false));
                }
                return new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main_train_receipt, parent, false));
            }

            @Override
            public int getItemViewType(int position) {
                //return position%2;
                //我是发货公司
                if(StringUtil.equals(comname,s.get(position).getDeveliverCompanyName())){
                    return 0;
                }
                //我是收货公司
                return 1;
            }

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                ViewDataBinding viewDataBinding = holder.viewDataBinding;
                viewDataBinding.getRoot().setTag(com.android.lib.R.id.data, list.get(position));
                viewDataBinding.getRoot().setTag(com.android.lib.R.id.position, position);
                viewDataBinding.getRoot().setOnClickListener(this);
                viewDataBinding.getRoot().setOnLongClickListener(this);
//                viewDataBinding.setVariable(vari, list.get(position));
//                viewDataBinding.executePendingBindings();//加一行，问题解决
                holder.viewDataBinding.getRoot().setSelected(position%2==0?true:false);
                switch (getItemViewType(position)){
                    case 0:
                        ItemMainTransSendBinding sendBinding = (ItemMainTransSendBinding) holder.viewDataBinding;
                        viewDataBinding.setVariable(BR.item_main_trans_send, list.get(position));
                        viewDataBinding.executePendingBindings();//加一行，问题解决


                        if(s.get(position).getFhTime()!=null){
                            sendBinding.tvSendtime.setText(DateFormatUtil.getdDateStr(DateFormatUtil.MM_DD_HH_MM,new Date(s.get(position).getFhTime())));
                        }
                        if(s.get(position).getShTime()!=null){
                            sendBinding.tvReceipttime.setText(DateFormatUtil.getdDateStr(DateFormatUtil.MM_DD_HH_MM,new Date(s.get(position).getShTime())));
                        }

                        sendBinding.circlebar.update((int) (100*s.get(position).getTotalSuttle()/(s.get(position).getPlanNumber()+0.00001)),false);

                        sendBinding.btSure.setOnClickListener(this);
                        sendBinding.btSure.setTag(R.id.position,position);
                        sendBinding.btSure.setTag(R.id.data,list.get(position));
                        switch (s.get(position).getSignStatus()){
                            case TransDetailRes.SING_STATUS_已确认:
                                sendBinding.tvPlanNumber.setText(StringUtil.getStr(s.get(position).getTotalSuttle()));
                                sendBinding.btSure.setVisibility(View.GONE);
                                sendBinding.circlebar.update(0,false);

                                break;
                            case TransDetailRes.SING_STATUS_等待确认:
                                sendBinding.tvPlanNumber.setText(StringUtil.getStr(s.get(position).getTotalSuttle()));
                                if(usertype == UserTypeReqBean.非驾驶员){
                                    sendBinding.btSure.setVisibility(View.VISIBLE);
                                    sendBinding.tvReceipttime.setVisibility(View.GONE);
                                    sendBinding.tvPlanNumber.setText(StringUtil.getStr(s.get(position).getReceiveNum()-s.get(position).getDeveliverNum()));
                                }else{
                                    sendBinding.tvReceipttime.setVisibility(View.VISIBLE);
                                }
                                break;
                        }
                        break;
                    case 1:
                        ItemMainTrainReceiptBinding receiptBinding = (ItemMainTrainReceiptBinding) holder.viewDataBinding;
                        viewDataBinding.setVariable(BR.item_main_trans_receipt, list.get(position));
                        viewDataBinding.executePendingBindings();//加一行，问题解决

                        if(s.get(position).getFhTime()!=null){
                            receiptBinding.tvSendtime.setText(DateFormatUtil.getdDateStr(DateFormatUtil.MM_DD_HH_MM,new Date(s.get(position).getFhTime())));
                        }

                        if(s.get(position).getShTime()!=null){
                            receiptBinding.tvReceipttime.setText(DateFormatUtil.getdDateStr(DateFormatUtil.MM_DD_HH_MM,new Date(s.get(position).getShTime())));
                        }
                        receiptBinding.circlebar.update((int) (100*s.get(position).getTotalSuttle()/(s.get(position).getPlanNumber()+0.00001)),false);


                        receiptBinding.btSure.setOnClickListener(this);
                        receiptBinding.btSure.setTag(R.id.position,position);
                        receiptBinding.btSure.setTag(R.id.data,list.get(position));
                        switch (s.get(position).getSignStatus()){
                            case TransDetailRes.SING_STATUS_已确认:
                                receiptBinding.tvPlanNumber.setText(StringUtil.getStr(s.get(position).getTotalSuttle()));
                                receiptBinding.btSure.setVisibility(View.GONE);
                                receiptBinding.circlebar.update(0,false);
                                receiptBinding.tvPlanNumber.setText(StringUtil.getStr(s.get(position).getReceiveNum()-s.get(position).getDeveliverNum()));
                                break;
                            case TransDetailRes.SING_STATUS_等待确认:
                                receiptBinding.tvPlanNumber.setText(StringUtil.getStr(s.get(position).getTotalSuttle()));
                                if(usertype == UserTypeReqBean.非驾驶员){
                                    receiptBinding.btSure.setVisibility(View.VISIBLE);
                                    receiptBinding.tvSendtime.setVisibility(View.GONE);
                                }else{
                                    receiptBinding.tvSendtime.setVisibility(View.VISIBLE);
                                }
                                break;
                        }
                        break;
                }
            }


        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void search(OnFinishListener onFinishListener){
        if(bind.title.getRightIV2().isSelected()){
            bind.title.getRightIV2().setSelected(false);
            Animator anim = ViewAnimationUtils.createCircularReveal(bind.search.getRoot(), bind.search.getRoot().getWidth()/2, 0, bind.search.getRoot().getWidth()/2, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    bind.search.getRoot().setVisibility(View.GONE);
                }
            });
            anim.start();
            onFinishListener.onFinish(true);
        }else{
            bind.title.getRightIV2().setSelected(true);
            //ViewAnimator.animate(bind.search.getRoot()).alpha(0,1).translationY(-bind.search.getRoot().getHeight(),0).accelerate().duration(300).start();
            Animator anim = ViewAnimationUtils.createCircularReveal(bind.search.getRoot(),bind.search.getRoot().getWidth()/2,0,0,bind.search.getRoot().getWidth()/2);
            bind.search.getRoot().setVisibility(View.VISIBLE);
            anim.start();
            onFinishListener.onFinish(false);
        }

    }

    public void initRefresh(OnRefreshListener onRefreshListener,OnLoadmoreListener onLoadmoreListener){
        bind.refresh.setOnRefreshListener(onRefreshListener);
        bind.refresh.setOnLoadmoreListener(onLoadmoreListener);
    }

    public void finishRefresh(){
        bind.refresh.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refresh.finishLoadmore();
    }

    public void autoRefresh(){
        bind.refresh.autoRefresh();
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
