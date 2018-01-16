package com.siweisoft.heavycenter.module.main.store.check;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.store.check.CheckStoreReqBean;
import com.siweisoft.heavycenter.data.netd.mana.store.check.CheckStoreResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresReqBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;

import java.util.ArrayList;

public class CheckDAOpe extends AppDAOpe {


    CheckStoreReqBean checkStoreReqBean = new CheckStoreReqBean();

    public CheckDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void checkStore(CheckStoreReqBean checkStoreReqBean, NetI<CheckStoreResBean> adapter){
        NetDataOpe.Mana.Store.checkStore(getActivity(),checkStoreReqBean,adapter);
    }

    public CheckStoreReqBean getCheckStoreReqBean() {
        checkStoreReqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        return checkStoreReqBean;
    }

    public CheckStoreReqBean getCheckStoreReqBean(StoresResBean.ResultsBean data) {
        checkStoreReqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        checkStoreReqBean.setWarehouseId(data.getWarehouseId());

        return checkStoreReqBean;
    }


    public void storesInfo(NetI<StoresResBean> adapter){
        StoresReqBean reqBean = new StoresReqBean();
        reqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        reqBean.setIsApp(1);
        reqBean.setPageIndex(0);
        reqBean.setPageSize(1000);
        reqBean.setStatus(StoresReqBean.STATUS_ALL);
        NetDataOpe.Mana.Store.sotresInfo(getActivity(),reqBean,adapter);
    }
}
