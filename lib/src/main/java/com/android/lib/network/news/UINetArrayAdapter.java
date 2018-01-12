package com.android.lib.network.news;

import android.content.Context;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.LoadUtil;

public abstract class UINetArrayAdapter<A> extends NetArrayAdapter<A> {


    public UINetArrayAdapter(Context context) {
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

    public void stopLoading(){
        LoadUtil.getInstance().onStopLoading(this.url);
    }
}