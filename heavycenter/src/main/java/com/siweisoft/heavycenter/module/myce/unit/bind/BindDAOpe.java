package com.siweisoft.heavycenter.module.myce.unit.bind;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.unit.list.ListReqBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;

public class BindDAOpe extends AppDAOpe {


    public static final int BIND_UNIT = 0;

    public static final int UP_UNIT = 1;

    public BindDAOpe(Context context) {
        super(context);
    }

    public void getData( NetI<ListResBean> adapter){
        ListReqBean listReqBean = new ListReqBean();
        listReqBean.setIsAPP(1);
        NetDataOpe.unitList(getActivity(), listReqBean,adapter);
    }
}
