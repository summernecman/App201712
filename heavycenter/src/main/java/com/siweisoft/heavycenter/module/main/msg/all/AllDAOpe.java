package com.siweisoft.heavycenter.module.main.msg.all;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetArrayI;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;

import java.util.ArrayList;

public class AllDAOpe extends BaseDAOpe {

    public AllDAOpe(Context context) {
        super(context);
    }



    public void getMsgAll(NetI<MsgsResBean> adapter){
        MsgsReqBean msgsReqBean = new MsgsReqBean();
        msgsReqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        msgsReqBean.setPageIndex(0);
        msgsReqBean.setPageSize(100);
        NetDataOpe.Msg.list(getActivity(),msgsReqBean,adapter);
    }

}
