package com.siweisoft.heavycenter.module.main.store;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresReqBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;

import java.util.ArrayList;

public class StoreDAOpe extends BaseDAOpe {

    private int pageIndex = 0;

    private StoresResBean storesResBean = new StoresResBean();

    public StoreDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void storesInfo(NetI<StoresResBean> adapter){
        StoresReqBean reqBean = new StoresReqBean();
        reqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        reqBean.setIsApp(1);
        reqBean.setPageIndex(getPageIndex());
        reqBean.setPageSize(20);
        NetDataOpe.Mana.Store.sotresInfo(getActivity(),reqBean,adapter);
    }

    public StoresResBean getStoresResBean() {
        return storesResBean;
    }

    public void addData(StoresResBean data){
        if(data!=null&&data.getResults()!=null){
            storesResBean.getResults().addAll(data.getResults());
        }
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
