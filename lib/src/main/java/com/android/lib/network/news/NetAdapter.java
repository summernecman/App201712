package com.android.lib.network.news;

import android.content.Context;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.*;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public  class NetAdapter<A> implements NetI<A> {

    public static boolean cache = true;
    protected Context context;
    protected String url;

    protected boolean showTips = true;

    public NetAdapter(Context context) {
        this.context = context;
    }

    public NetAdapter(Context context,boolean isshow) {
        this.context = context;
        showTips = isshow;
    }


    @Override
    public boolean onNetStart(String url, String gson) {
        this.url = url;
        boolean isNetOk = NetWorkUtil.getInstance().getNetisAvailable(context);
        if(NetGet.test){
            return true;
        }
        if(!isNetOk){
            ToastUtil.getInstance().showShort(context,"无网络");
        }
        return isNetOk;
    }

    @Override
    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
        if (!haveData) {
            onResult(false,baseResBean.getMessage(), null);
        } else {
            if(cache){
                if(showTips){
                    ToastUtil.getInstance().showShort(context,"当前为无网络测试环境");
                }
                BaseResBean resBean = GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(url),BaseResBean.class);
                if(resBean ==null){
                    resBean = new BaseResBean();
                    resBean.setCode("000");
                }
                deal(haveData,url,resBean);
            }else{
                if(!NullUtil.isStrEmpty(baseResBean.getMessage())&& showTips){
                    ToastUtil.getInstance().showShort(context.getApplicationContext(),baseResBean.getMessage());
                }
                SPUtil.getInstance().saveStr(url,GsonUtil.getInstance().toJson(baseResBean));
                deal(haveData,url,baseResBean);
            }

        }
    }


    private void deal(boolean haveData, String url, BaseResBean baseResBean){
        boolean isobject = false;
        boolean isarray = false;
        Type type = getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType ){
            ParameterizedType parameterizedType = (ParameterizedType) type;
            A aa = null;
            try {
                LogUtil.E(isobject+":1"+isarray);
                Object o = new JSONTokener(GsonUtil.getInstance().toJson(baseResBean.getResult())).nextValue();
                LogUtil.E(isobject+":2"+isarray);
                try {
                    isobject = o instanceof JSONObject;
                    LogUtil.E(isobject+":3"+isarray);
                    if(!isobject){
                        LogUtil.E(isobject+":4"+isarray);
                        try {
                            isarray = o instanceof JSONArray;
                            LogUtil.E(isobject+":5"+isarray);
                        } catch (Exception e) {
                            e.printStackTrace();
                            LogUtil.E(isobject+":6"+isarray);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.E(isobject+":7"+isarray);
                }
                LogUtil.E(isobject+":8"+isarray);
                if(isobject){
                    Class<A> a = (Class<A>) parameterizedType.getActualTypeArguments()[0];
                     aa = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),a);
                }else if(isarray){
                    TypeToken<?> typeToken = TypeToken.get(parameterizedType.getActualTypeArguments()[0]);
                     aa = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getResult()),typeToken.getType());
                }else{
                    LogUtil.E(baseResBean.getResult());
                }
            } catch (Exception e) {
                e.printStackTrace();

            }finally {
                LogUtil.E(isobject+":9"+isarray+""+baseResBean.getCode());
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
        if(success){
            onSuccess(o);
        }
    }

    @Override
    public void onProgress(long total, long current) {

    }

    @Override
    public void onSuccess(A o) {

    }

    @Override
    public void onFail(boolean haveData, String msg) {

    }


}