package com.android.lib.network.newsf;

import android.content.Context;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.util.LoadUtil;

public abstract class UIFNetAdapter<A> extends NetFAdapter<A> {


    public UIFNetAdapter(BaseUIFrag frag) {
        super(frag);
    }

    @Override
    public boolean onNetStart(String url, String gson) {
        frag.startLoading();
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
        if(frag!=null){
            frag.stopLoading();
        }
    }
}