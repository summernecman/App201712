package com.siweisoft.heavycenter.module.acct.role;

//by summer on 2017-12-18.

import android.view.View;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.crash.CrashNetOpe;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeResBean;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;
import com.siweisoft.heavycenter.module.main.MainAct;

import butterknife.OnClick;

public class RoleFrag extends AppFrag<RoleUIOpe,RoleDAOpe>{

    public static String 直接登录 = "直接登录";

    @OnClick({R.id.tv_notdriver,R.id.tv_driver})
    public void onClick(final View vv){
        super.onClick(vv);
        getP().getU().showTip(R.id.tv_driver==vv.getId(),new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                switch (v.getId()){
                    case R.id.tv_sure:
                        getP().getD().login(LocalValue.get登录参数(), new UINetAdapter<LoginResBean>(getContext(),false) {
                            @Override
                            public void onSuccess(LoginResBean o) {
                                getP().getU().getUserTypeReqBean().setId(o.getUserId());
                                getP().getU().getUserTypeReqBean().setUserType((R.id.tv_driver==vv.getId())?UserTypeReqBean.驾驶员 :UserTypeReqBean.非驾驶员);
                                LocalValue.save登录返回信息(o);
                                getP().getD().setUserType(getP().getU().getUserTypeReqBean(), new UINetAdapter<UserTypeResBean>(getContext()) {
                                    @Override
                                    public void onSuccess(UserTypeResBean o) {
                                        LogUtil.E("1");
                                        if(getArguments().getBoolean(直接登录,false)){
                                            LogUtil.E("2");
                                            LoginResBean resBean = LocalValue.get登录返回信息();
                                            LogUtil.E("3");
                                            resBean.setUserType((R.id.tv_driver==vv.getId())?UserTypeReqBean.驾驶员 :UserTypeReqBean.非驾驶员);
                                            LogUtil.E("4");
                                            LocalValue.save登录返回信息(resBean);
                                            LogUtil.E("5");
                                            IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                                            LogUtil.E("6");
                                        }else{
                                            LogUtil.E("7");
                                            getBaseUIActivity().onBackPressed();
                                            LogUtil.E("8");
                                        }
                                    }
                                });
                            }
                        });
                        break;
                }
                FragManager2.getInstance().setFinishAnim(R.anim.fade_in,R.anim.fade_out).finish(getBaseUIActivity(), AcctAct.账号,true);
            }
        });
    }

}
