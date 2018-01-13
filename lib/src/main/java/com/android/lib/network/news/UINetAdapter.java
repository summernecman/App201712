package com.android.lib.network.news;

import android.content.Context;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.LoadUtil;

public abstract class UINetAdapter<A extends BaseBean> extends NetAdapter<A> {


    public UINetAdapter(Context context) {
        super(context);
    }

    @Override
    public boolean onNetStart(String url, String gson) {
        LoadUtil.getInstance().onStartLoading(context, url);
        return super.onNetStart(url, gson);
    }

    @Override
    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
        super.onNetFinish(haveData, url, baseResBean);
        stopLoading();
    }

    @Override
    public void onResult(boolean success, String msg, A o) {
        stopLoading();
        super.onResult(success, msg, o);
    }

    public void stopLoading(){
        LoadUtil.getInstance().onStopLoading(this.url);
    }
}