package com.siweisoft.heavycenter.module.mana.user.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.user.add.AddUserReqBean;
import com.siweisoft.heavycenter.data.netd.mana.user.add.AddUserResBean;

public class NewDAOpe extends AppDAOpe {

    AddUserReqBean reqBean = new AddUserReqBean();


    public NewDAOpe(Context context) {
        super(context);
    }

    public void addUser(AddUserReqBean reqBean, NetI<AddUserResBean> adapter){
        NetDataOpe.Mana.User.addUser(getActivity(),reqBean,adapter);
    }

    public AddUserReqBean getReqBean() {
        reqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        reqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        return reqBean;
    }
}
