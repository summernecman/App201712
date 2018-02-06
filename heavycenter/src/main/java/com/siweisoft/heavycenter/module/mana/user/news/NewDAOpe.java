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



    public void addUser(AddUserReqBean reqBean, NetI<AddUserResBean> adapter){
        NetDataOpe.Mana.User.addUser(getActivity(),reqBean,adapter);
    }

    public AddUserReqBean getReqBean() {
        reqBean.setUserId(LocalValue.get登录返回信息().getUserId());
        reqBean.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        return reqBean;
    }
}
