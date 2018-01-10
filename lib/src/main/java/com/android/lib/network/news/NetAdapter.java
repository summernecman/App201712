package com.android.lib.network.news;

import android.content.Context;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NetWorkUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

public  class NetAdapter<A extends Object> implements NetI<A> {

    protected Context context;
    protected String url;

    public NetAdapter(Context context) {
        this.context = context;
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
            Class<A> a = (Class<A>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

            A aa =GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),a);
            if (!"200".equals(baseResBean.getCode())) {
                onResult(false,baseResBean.getMessage(), aa);
            } else {
                onResult(true,baseResBean.getMessage(), aa);
            }
            if(!NullUtil.isStrEmpty(baseResBean.getMessage())){
                ToastUtil.getInstance().showLong(context,baseResBean.getMessage());
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