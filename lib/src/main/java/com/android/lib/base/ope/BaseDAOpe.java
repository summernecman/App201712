package com.android.lib.base.ope;

import android.content.Context;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.bean.LayoutDABean;

import java.util.ArrayList;

/**
 * 数据处理的操作列
 */
public class BaseDAOpe implements BaseOpe {

    /**
     * 上下文
     */
    protected Context context;

    protected BaseUIFrag frag;

    public BaseDAOpe() {

    }

    public void initDA(){

    }



    public BaseDAOpe(Context context) {
        this.context = context;
    }

    public BaseUIActivity getActivity(){
        if(frag!=null){
            return frag.getBaseUIAct();
        }else{
            return (BaseUIActivity) context;
        }
    }


    public LayoutDABean getData(Object[] objects) {
        LayoutDABean bean = new LayoutDABean();
        for (int i = 0; i < objects.length; i++) {
            bean.data[i].set(objects[i]);
        }
        return bean;
    }

    public ArrayList<LayoutDABean> getData(Object[][] objects) {
        ArrayList<LayoutDABean> data = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            data.add(getData(objects[i]));
        }
        return data;
    }

    public BaseUIFrag getFrag() {
        return frag;
    }

    public void setFrag(BaseUIFrag frag) {
        this.frag = frag;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}