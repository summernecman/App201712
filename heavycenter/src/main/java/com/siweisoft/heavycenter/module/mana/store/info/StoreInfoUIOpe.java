package com.siweisoft.heavycenter.module.mana.store.info;

import android.content.Context;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.databinding.FragManaStoreInfoBinding;

/**
 * Created by summer on 2018/1/31 0:12.
 */

public class StoreInfoUIOpe  extends BaseUIOpe<FragManaStoreInfoBinding>{


    public StoreInfoUIOpe(Context context) {
        super(context);
    }


    public void initUI(StoreDetail storeDetail){
        bind.itemMaxstock.setMidEtTxt(StringUtil.getStr(storeDetail.getMaxStock()));
        bind.itemMinstock.setMidEtTxt(StringUtil.getStr(storeDetail.getMaxStock()));
        bind.itemId.setMidEtTxt(StringUtil.getStr(storeDetail.getWarehouseName()));
    }
}
