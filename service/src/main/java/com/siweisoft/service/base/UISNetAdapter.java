package com.siweisoft.service.base;

import android.content.Context;
import android.view.View;

import com.android.lib.R;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.LoadUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public abstract class UISNetAdapter<A> extends SNetAdapter<A> {


    public static final int Loading = 1;


    private int isload = 0;

    public UISNetAdapter(Context context) {
        super(context);
    }

    public UISNetAdapter(BaseUIFrag baseUIFrag) {
        super(baseUIFrag);
    }

    public UISNetAdapter(BaseUIFrag baseUIFrag, int isload) {
        super(baseUIFrag);
        this.isload = isload;
    }


    public UISNetAdapter(BaseUIFrag baseUIFrag, int isload, boolean isshow) {
        super(baseUIFrag, isshow);
        this.isload = isload;
    }

    public UISNetAdapter(Context context, boolean isshow) {
        super(context, isshow);
    }

    public UISNetAdapter(BaseUIFrag baseUIFrag, boolean isshow) {
        super(baseUIFrag, isshow);
    }


    @Override
    public boolean onNetStart(String url, String gson) {
        if(isload==1){
            if(baseUIFrag!=null){
                baseUIFrag.startLoading();
            }else{
                LoadUtil.getInstance().onStartLoading(context, url);
            }
        }
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
        stopRefreshORLoadMore();
        super.onResult(success, msg, o);
    }

    public void stopLoading(){
        LoadUtil.getInstance().onStopLoading(this.url);
    }

    public void stopRefreshORLoadMore(){
        if(baseUIFrag!=null&&baseUIFrag.getView()!=null){
            View v = baseUIFrag.getView().findViewById(R.id.refresh);
            if(v!=null&&v instanceof SmartRefreshLayout){
                SmartRefreshLayout refreshLayout = (SmartRefreshLayout) v;
                refreshLayout.finishLoadmore();
                refreshLayout.finishRefresh();
            }
            baseUIFrag.stopLoading();
        }
    }
}