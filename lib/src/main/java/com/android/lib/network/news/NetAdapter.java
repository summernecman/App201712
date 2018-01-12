package com.android.lib.network.news;

import android.content.Context;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NetWorkUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public  class NetAdapter<A> implements NetI<A> {

    protected Context context;
    protected String url;

    public NetAdapter(Context context) {
        this.context = context;
        Type type = this.getClass().getGenericSuperclass();
        getClass().getSimpleName();
    }


    @Override
    public boolean onNetStart(String url, String gson) {
        this.url = url;
        boolean isNetOk = NetWorkUtil.getInstance().getNetisAvailable(context);
        return isNetOk;
    }

    @Override
    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
        if (!haveData) {
            onResult(false,baseResBean.getMessage(), null);
        } else {

            if(!NullUtil.isStrEmpty(baseResBean.getMessage())){
                ToastUtil.getInstance().showLong(context,baseResBean.getMessage());
            }

            Type type = getClass().getGenericSuperclass();
            if(type instanceof ParameterizedType ){
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class<A> a = (Class<A>) parameterizedType.getActualTypeArguments()[0];
                A aa = null;
                try {
                    aa = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),a);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } finally {
                }
                if (!"200".equals(baseResBean.getCode())) {
                    onResult(false,baseResBean.getMessage(), aa);
                } else {
                    onResult(true,baseResBean.getMessage(), aa);
                }
            }

        }
    }

    @Override
    public void onResult(boolean success, String msg, A o) {

    }

    @Override
    public void onProgress(long total, long current) {

    }


}