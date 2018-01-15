package com.siweisoft.heavycenter.module.myce.unit.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.unit.news.NewReqBean;
import com.siweisoft.heavycenter.data.netd.unit.news.NewResBean;

public class NewDAOpe extends AppDAOpe {

   UnitInfo unit = new UnitInfo();

    public NewDAOpe(Context context) {
        super(context);
    }

    public UnitInfo getUnit() {
        return unit;
    }

    public void createUnit(NewReqBean reqBean, NetI<NewResBean> adapter){
        NetDataOpe.Unit.createUnit(getActivity(),reqBean,adapter);
    }
}
