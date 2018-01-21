package com.siweisoft.heavycenter.module.main.store.check;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.android.lib.util.GsonUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.store.check.Check;
import com.siweisoft.heavycenter.data.netd.mana.store.check.CheckStoreReqBean;
import com.siweisoft.heavycenter.data.netd.mana.store.check.CheckStoreResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresReqBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;

import java.util.ArrayList;

public class CheckDAOpe extends AppDAOpe {


    CheckStoreReqBean checkStoreReqBean = new CheckStoreReqBean();

    private StoresResBean storesResBean;

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

    public CheckStoreReqBean getCheckStoreReqBean(StoresResBean data) {
        getCheckStoreReqBean().setUserId(LocalValue.getLoginInfo().getUserId());
        ArrayList<Check> checks = new ArrayList<>();
        for(int i=0;data!=null && data.getResults()!=null && i<data.getResults().size();i++){
            Check check  = new Check();
            check.setBeforeAdjust(data.getResults().get(i).getCurrentStock());
            check.setAfterAdjust(data.getResults().get(i).getAfterAdjust());
            check.setWarehouseId(data.getResults().get(i).getWarehouseId());
            check.setProductId(data.getResults().get(i).getProductId());
            checks.add(check);
        }
        checkStoreReqBean.setWareHouseListString(GsonUtil.getInstance().toJson(checks));
        return checkStoreReqBean;
    }


    public void storesInfo(NetI<StoresResBean> adapter){
        StoresReqBean reqBean = new StoresReqBean();
        reqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        reqBean.setIsApp(1);
        reqBean.setPageIndex(0);
        reqBean.setPageSize(1000);
        NetDataOpe.Mana.Store.sotresInfo(getActivity(),reqBean,adapter);
    }

    public boolean canGo(){
        for(int i=0;getStoresResBean()!=null && getStoresResBean().getResults()!=null && i<getStoresResBean().getResults().size();i++){
            if(getStoresResBean().getResults().get(i).getAfterAdjust()<0){
                return false;
            }
        }
        return true;
    }

    public StoresResBean getStoresResBean() {
        return storesResBean;
    }

    public void setStoresResBean(StoresResBean storesResBean) {
        this.storesResBean = storesResBean;
    }
}
