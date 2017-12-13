package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.heavycenter.module.view.map.MapUtil;

public class TransDAOpe extends BaseDAOpe {

    MapUtil mapUtil;

    public TransDAOpe(Context context) {
        super(context);
        mapUtil = new MapUtil();
    }

    public MapUtil getMapUtil() {
        return mapUtil;
    }

}
