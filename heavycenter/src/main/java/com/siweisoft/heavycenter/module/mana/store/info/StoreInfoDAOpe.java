package com.siweisoft.heavycenter.module.mana.store.info;

import android.content.Context;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;

/**
 * Created by summer on 2018/1/31 0:12.
 */

public class StoreInfoDAOpe extends BaseDAOpe {

    private StoreDetail storeDetail ;

    public StoreInfoDAOpe(Context context) {
        super(context);
    }

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(StoreDetail storeDetail) {
        this.storeDetail = storeDetail;
    }
}
