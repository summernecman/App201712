package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.view.*;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.trans.detail.TransDetailRes;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransReq;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.databinding.FragMainTransBinding;
import com.siweisoft.heavycenter.databinding.ItemMainTrainReceiptBinding;
import com.siweisoft.heavycenter.databinding.ItemMainTrans2Binding;
import com.siweisoft.heavycenter.databinding.ItemMainTransBinding;
import com.siweisoft.heavycenter.databinding.ItemMainTransSendBinding;
import com.siweisoft.heavycenter.module.main.orders.news.rule.RuleDAOpe;

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

        if(s==null || s.size()==0){
            getFrag().showTips("暂无数据");
            return;
        }
        getFrag().removeTips();

        final String comname = LocalValue.get登录返回信息().getAbbreviationName();
        final LoginResBean loginResBean = LocalValue.get登录返回信息();
        final int usertype = LocalValue.get登录返回信息().getUserType();
        final DecimalFormat df = new DecimalFormat("#.##");
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_trans2, BR.item_main_trans2, s,listener){

            @Override
            public AppViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                switch (viewType){
                    case 1:
                        return new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main_trans2_driver, parent, false));
                    default:
                        return new AppViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_main_trans2, parent, false));
                }
            }

            @Override
            public int getItemViewType(int position) {
                if(loginResBean.is驾驶员()){
                    return 1;
                }else{
                    return 2;
                }
            }

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                ViewDataBinding viewDataBinding = holder.viewDataBinding;
                setTag(viewDataBinding.getRoot(),position);
                switch (getItemViewType(position)){
                    case 1:
                        viewDataBinding.setVariable(BR.item_main_trans2_driver, list.get(position));
                        break;
                        default:
                            viewDataBinding.setVariable(BR.item_main_trans2, list.get(position));
                            break;
                }
                viewDataBinding.executePendingBindings();//加一行，问题解决
                holder.viewDataBinding.getRoot().setSelected(position%2==0?true:false);
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
