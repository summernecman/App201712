package com.android.lib.base.ope2;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.service.quicksettings.Tile;
import android.view.LayoutInflater;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.bean.AppViewHolder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

/**
 * ui处理操作者 处理对象 uibean fragment view
 */
public class BaseUIOpe<A extends ViewDataBinding, B extends ViewDataBinding> {

    public B content;
    public A title;
    protected Context context;
    protected  BaseUIFrag frag;




    public BaseUIOpe(){

    }


    public BaseUIOpe(Context context) {
        this.context = context;
        content = initContentViewDataBinding();
        title = initTitleViewDataBinding();
        content.executePendingBindings();
        title.executePendingBindings();
    }

    public BaseUIActivity getActivity(){
        if(frag!=null ){
            return frag.getBaseUIAct();
        }else{
            return (BaseUIActivity) context;
        }
    }

    public void copy(BaseUIOpe baseUIOpe){
        this.context = baseUIOpe.context;
        this.frag = baseUIOpe.frag;
        this.content = (B) baseUIOpe.content;
        this.title = (A) baseUIOpe.title;
        initUI();
    }


    public void initUI(){

    }


    public B initContentViewDataBinding() {
        B viewDataBinding = null;
        if (viewDataBinding == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<B> a = (Class<B>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
                Method method = null;
                try {
                    method = a.getMethod("inflate", LayoutInflater.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    viewDataBinding = (B) method.invoke(null, LayoutInflater.from(context));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                viewDataBinding = getContent();
            }
        }
        return viewDataBinding;
    }


    public A initTitleViewDataBinding() {
        A viewDataBinding = null;
        if (viewDataBinding == null) {
            if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
                Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                Method method = null;
                try {
                    method = a.getMethod("inflate", LayoutInflater.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    viewDataBinding = (A) method.invoke(null, LayoutInflater.from(context));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                viewDataBinding = getTitle();
            }
        }
        return viewDataBinding;
    }



    public B getContent() {
        return content;
    }

    public A getTitle() {
        return title;
    }

    public BaseUIFrag getFrag() {
        return frag;
    }

    public void setFrag(BaseUIFrag frag) {
        this.frag = frag;
        this.context = frag.getActivity();
        if(content ==null){
            content = initContentViewDataBinding();
            content.executePendingBindings();
        }
        if(title ==null){
            title = initTitleViewDataBinding();
            title.executePendingBindings();
        }
    }

    public void setContext(Context context) {
        this.context = context;
        if(content ==null){
            content = initContentViewDataBinding();
            content.executePendingBindings();
        }
        if(title ==null){
            title = initTitleViewDataBinding();
            title.executePendingBindings();
        }
    }
}
