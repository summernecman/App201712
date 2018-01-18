package com.siweisoft.heavycenter.module.myce.sett;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutReqBean;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutResBean;

public class SetDAOpe extends AppDAOpe {


    public SetDAOpe(Context context) {
        super(context);
    }

    public void logOut( NetI<LogOutResBean> adapter){
        LogOutReqBean logOutReqBean = new LogOutReqBean();
        logOutReqBean.setTel(LocalValue.getLoginInfo().getTel());
        NetDataOpe.logOut(getActivity(),logOutReqBean,adapter);
    }
}
