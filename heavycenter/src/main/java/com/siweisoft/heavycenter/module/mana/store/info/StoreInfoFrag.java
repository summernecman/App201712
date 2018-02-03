package com.siweisoft.heavycenter.module.mana.store.info;

import com.android.lib.constant.ValueConstant;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;

/**
 * Created by summer on 2018/1/31 0:12.
 */

public class StoreInfoFrag extends AppFrag<StoreInfoUIOpe,StoreInfoDAOpe>{


    @Override
    public void initdelay() {
        super.initdelay();
        getP().getD().setStoreDetail((StoreDetail) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initUI(getP().getD().getStoreDetail());
    }
}
