package com.android.lib.base.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.R;
import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.FragI;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseOpes;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.system.HandleUtil;
import com.android.lib.view.bottommenu.MessageEvent;

import com.wang.avi.AVLoadingIndicatorView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by summer on 2016/4/16 0016 16:03.
 */
public abstract class BaseUIFrag<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseFrg implements View.OnClickListener, View.OnLongClickListener {

    /**
     * fragment所属的层次
     */
    protected int index;

    Unbinder unbinder;
    /**
     * fragment的操作类
     */
    BaseOpes<A, B> opes;

    private ArrayList<FragI> fragIs = new ArrayList<>();

    private boolean isInit = false;

    private boolean isFiistVisibleinit = false;

    private ViewGroup parent;

    public BaseUIFrag() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onCreate(savedInstanceState);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments() != null) {
            index = getArguments().getInt(ValueConstant.FRAG_POSITION);
        }
        View group = inflater.inflate(getLayoutID(), null);
        return group;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parent = (ViewGroup) view.findViewById(R.id.container);
        if (getP()!=null && getP().getU() != null && getP().getU().getBind().getRoot() != null) {
            ViewGroup viewGroup = (ViewGroup) getP().getU().getBind().getRoot().getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            parent.addView(getP().getU().getBind().getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        unbinder = ButterKnife.bind(this, view);
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onCreateView(null,null,savedInstanceState);
        }
        getP().getU().initUI(this);
        initNow();
        HandleUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(isAttach()){
                    initdelay();
                }
            }
        }, 700);
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onViewCreated(view,savedInstanceState);
        }
    }

    public void initdelay() {
        if(getView()==null){
            return;
        }
    }

    public void initNow() {
        if(getView()==null){
            return;
        }
    }

    public void onFristVisible(){
        if(!isFiistVisibleinit){
        onFristVisibleInit();
        isFiistVisibleinit = true;
        }
    }

    private int 显示次数 = 0;

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser&&isAttach()){
//            显示次数=显示次数+1;
//        }
//        LogUtil.E(getClass().getSimpleName()+"setUserVisibleHint"+isVisibleToUser+(getActivity()==null)+显示次数);
//        if(显示次数==1){
//
//        }
//    }

    protected void onFristVisibleInit(){

    }


    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onDestroyView();
        }
    }

    /**
     * 重新此方法获取布局文件
     */
    public int getLayoutID() {
        return R.layout.layout_baseui;
    }

    /**
     * 获取操作类
     */
    public BaseOpes<A, B> getP() {
        if (opes == null) {
            getaabb(getClass());
        }
        return opes;
    }

    private BaseOpes<A, B> getaabb(Class<?> c) {
        if (c == null) {
            opes = (BaseOpes<A, B>) new BaseOpes<>(new BaseUIOpe<ViewDataBinding>(activity), new BaseDAOpe(activity));
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<A> a = (Class<A>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
            Class<B> b = (Class<B>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[1];
            try {
                Constructor<A> ac = a.getConstructor(Context.class);
                Constructor<B> bc = b.getConstructor(Context.class);
                A aa = ac.newInstance(activity);
                B bb = bc.newInstance(activity);
                aa.setFrag(this);
                bb.setFrag(this);
                opes = new BaseOpes<>(aa, bb);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            getaabb(c.getSuperclass());
        }

        return opes;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    public void onResult(int req, Bundle bundle) {

    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        for(int i=0;i<fragIs.size();i++){
            fragIs.get(i).onDestroy();
        }
        super.onDestroy();
    }

    /**
     * 消息总线处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(MessageEvent event) {
        LogUtil.E(event.dealer + ":" + getClass().getName());
        if (!event.dealer.equals(getClass().getName())) {
            event.isme = false;
            return;
        }
    }

    public void onRestart(int res,Bundle bundle){

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void addFragListener(FragI fragI){
        fragIs.add(fragI);
    }


    public String getContainerName() {
        return getArguments().getString(ValueConstant.CONTAINER_NAME);
    }

    public BaseUIActivity getBaseUIActivity() {
        return (BaseUIActivity) activity;
    }

    public void setInited() {
        isInit = true;
    }

    public boolean isInit() {
        return isInit;
    }
    View  v;

    View tips;
    public void startLoading(){
          v = LayoutInflater.from(activity).inflate(R.layout.dialog_loading,null);
        AVLoadingIndicatorView avLoadingIndicatorView = (AVLoadingIndicatorView) v.findViewById(R.id.av);
        parent.addView(v,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        avLoadingIndicatorView.show();


    }

    public void stopLoading(){
        if(v!=null && v.getParent()==parent){
            parent.removeView(v);
        }
    }

    public void showTips(String txt){
        tips = LayoutInflater.from(activity).inflate(R.layout.item_tips,null);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        parent.addView(tips,params);
        TextView textView = tips.findViewById(R.id.tv_txt);
        textView.setText(txt);
    }

    public void removeTips(){
        if(tips!=null && tips.getParent()==parent){
            parent.removeView(tips);
        }
    }
}
