package com.siweisoft.heavycenter.module.mana.user;

//by summer on 2017-12-14.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.user.add.AddUserReqBean;
import com.siweisoft.heavycenter.data.netd.mana.user.add.AddUserResBean;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUserResBean;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUsersReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindResBean;

import java.util.ArrayList;

public class UserDAOpe extends AppDAOpe {
    public UserDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void unitUsers(NetI<UnitUserResBean> adapter){
        UnitUsersReqBean reqBean = new UnitUsersReqBean();
        reqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        reqBean.setIsApp(1);
        reqBean.setPageIndex(0);
        reqBean.setPageSize(1000);
        NetDataOpe.Unit.unitUsers(getActivity(),reqBean,adapter);
    }

    public void addUser(String tel, NetI<AddUserResBean> adapter){
        AddUserReqBean reqBean = new AddUserReqBean();
        reqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        reqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        reqBean.setUserRole(LoginResBean.USER_ROLE_GENERAL);
        NetDataOpe.Mana.User.addUser(getActivity(),reqBean,adapter);
    }

    public void unBindUser(int userid, NetI<UnBindResBean> adapter){
        UnBindReqBean reqBean = new UnBindReqBean();
        reqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        reqBean.setId(userid);
        NetDataOpe.User.unBinUnit(getActivity(),reqBean,adapter);
    }

    public boolean canUnBind(int userid){
        if(LocalValue.getLoginInfo().getUserId()==userid){
            ToastUtil.getInstance().showShort(getActivity(),"不能解绑自己");
            return false;
        }
        return true;
    }

}
