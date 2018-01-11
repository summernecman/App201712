package com.siweisoft.heavycenter.module.acct.login;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.NetArrayAdapter;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityReqBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;
import com.siweisoft.heavycenter.data.netd.unit.news.NewResBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchReqBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchResBean;
import com.siweisoft.heavycenter.data.netd.user.info.InfoReqBean;
import com.siweisoft.heavycenter.data.netd.user.info.InfoResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.module.acct.regist.RegistFrag;
import com.siweisoft.heavycenter.module.acct.repwd.RepwdFrag;
import com.siweisoft.heavycenter.module.acct.role.RoleFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

import java.util.ArrayList;

import butterknife.OnClick;

public class LoginFrag extends AppFrag<LoginUIOpe,LoginDAOpe> {

    @Override
    public void initData() {
        super.initData();

        NetDataOpe.getCity(getActivity(),new CityReqBean(),new NetArrayAdapter<CityResBean>(getActivity()){
            @Override
            public void onResult(boolean success, String msg, ArrayList<CityResBean> o) {
              LocalValue.saveCitysInfo(o);
              getP().getD().saveProMapInfo();
              getP().getD().initDATA();
            }
        });

        InfoReqBean reqBean = new InfoReqBean();
        reqBean.setId(140);
        reqBean.setIsApp(1);
        NetDataOpe.User.getInfo(getActivity(),reqBean,new NetAdapter<InfoResBean>(getActivity()){
            @Override
            public void onResult(boolean success, String msg, InfoResBean o) {
                super.onResult(success, msg, o);
                LogUtil.E(o);
            }
        });
    }

    @OnClick({R.id.login,R.id.regist,R.id.repwd})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                if(getP().getU().go()){
                    getP().getD().login(getP().getU().getLoginReqBean(), new UINetAdapter<LoginResBean>(getActivity()) {
                        @Override
                        public void onResult(boolean success, String msg, LoginResBean loginResBean) {
                            if(success){
                                LocalValue.saveLoginInfo(loginResBean);
                                if(loginResBean.getUserType()== UserTypeReqBean.USER_TYPE_DRIVER || loginResBean.getUserType()== UserTypeReqBean.USER_TYPE_GENERAL){
                                    IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                                }else{
                                    FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new RoleFrag());
                                }
                            }
                        }
                    });
                }
                break;
            case R.id.regist:
                FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new RegistFrag());
                break;
            case R.id.repwd:
                FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new RepwdFrag());
                break;
        }
    }

}
