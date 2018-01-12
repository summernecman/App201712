package com.android.lib.network.news;

import android.content.Context;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NetWorkUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public  class NetArrayAdapter<A> implements NetArrayI<A> {

    protected Context context;
    protected String url;

    public NetArrayAdapter(Context context) {
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

            Type type = getClass().getGenericSuperclass();
            getClass().getSimpleName();
            Class<A> a = (Class<A>) ((ParameterizedType) type).getActualTypeArguments()[0];

            ArrayList<A> aa = null;
            try {
                aa = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),new TypeToken<ArrayList<A>>(){}.getType());
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            } finally {
            }

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
    public void onResult(boolean success, String msg, ArrayList<A> o) {

    }


    @Override
    public void onProgress(long total, long current) {

    }


}