package com.siweisoft.heavycenter.module.main.store.detail;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.store.detail.StoreDetailReq;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;

import java.util.ArrayList;

public class DetailDAOpe extends BaseDAOpe {

    private StoreDetail storeDetail;


    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void detail(Context context,int id, NetI<StoreDetail> adapter){
        StoreDetailReq storeDetailReq = new StoreDetailReq();
        storeDetailReq.setWarehouseId(id);
        NetDataOpe.Mana.Store.detail(context,storeDetailReq,adapter);
    }

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        this.storeDetail = storeDetail;
    }
}
