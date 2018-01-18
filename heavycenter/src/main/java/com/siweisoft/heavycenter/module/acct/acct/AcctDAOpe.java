package com.siweisoft.heavycenter.module.acct.acct;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutReqBean;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutResBean;
import com.siweisoft.heavycenter.data.netd.user.info.UserInfoReqBean;
import com.siweisoft.heavycenter.module.acct.login.LoginFrag;
import com.siweisoft.heavycenter.module.acct.regist.RegistFrag;
import com.siweisoft.heavycenter.module.acct.repwd.RepwdFrag;
import com.siweisoft.heavycenter.module.acct.role.RoleFrag;

import java.util.ArrayList;

public class AcctDAOpe extends AppDAOpe {

    private ArrayList<Fragment> frags;

    private int index=0;

    public AcctDAOpe(Context context) {
        super(context);
        initPages();
    }

    private void initPages(){
        frags = new ArrayList<>();
        frags.add(new LoginFrag());
        frags.add(new RegistFrag());
        frags.add(new RepwdFrag());
        frags.add(new RoleFrag());
    }

    public Fragment getShowFrag(){
        for(int i=0;i<frags.size();i++){
            if(!frags.get(i).isHidden()){
                return frags.get(i);
            }
        }
        return frags.get(0);
    }

    public ArrayList<Fragment> getFrags(){
        return frags;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void logOut( NetI<LogOutResBean> adapter){
        LogOutReqBean logOutReqBean = new LogOutReqBean();
        logOutReqBean.setTel(LocalValue.getLoginInfo().getTel());
        NetDataOpe.logOut(getActivity(),logOutReqBean,adapter);
    }

    public void getInfo(NetI<LoginResBean> adapter){

        UserInfoReqBean userInfoReqBean = new UserInfoReqBean();
        userInfoReqBean.setId(LocalValue.getLoginInfo().getUserId());
        userInfoReqBean.setIsApp(1);
        NetDataOpe.User.getInfo(getActivity(),userInfoReqBean,adapter);
    }
}
