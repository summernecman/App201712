package com.siweisoft.heavycenter.module.acct.role;

//by summer on 2017-12-18.

import android.view.View;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeResBean;
import com.siweisoft.heavycenter.module.main.MainAct;

import butterknife.OnClick;

public class RoleFrag extends AppFrag<RoleUIOpe,RoleDAOpe>{

    @OnClick({R.id.tv_notdriver,R.id.tv_driver})
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_notdriver:
                getP().getU().showTip(getIndex(),new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case R.id.tv_sure:
                               getP().getD().login(LocalValue.getLoginReq(), new UINetAdapter<LoginResBean>(getContext()) {
                                   @Override
                                   public void onResult(boolean success, String msg, LoginResBean o) {
                                       getP().getU().getUserTypeReqBean().setId(o.getUserId());
                                       getP().getU().getUserTypeReqBean().setUserType(UserTypeReqBean.USER_TYPE_GENERAL);
                                       getP().getD().setUserType(getP().getU().getUserTypeReqBean(), new UINetAdapter<UserTypeResBean>(getContext()) {
                                           @Override
                                           public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                                               super.onNetFinish(haveData, url, baseResBean);
                                               stopLoading();
                                               if("200".equals(baseResBean.getCode())){
                                                   if(getArguments().getBoolean("regist")){
                                                       FragManager.getInstance().finish(getActivity().getSupportFragmentManager(),getIndex());
                                                   }else{
                                                       LoginResBean resBean = LocalValue.getLoginInfo();
                                                       resBean.setUserType(UserTypeReqBean.USER_TYPE_GENERAL);
                                                       LocalValue.saveLoginInfo(resBean);
                                                       IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                                                   }
                                               }
                                           }
                                       });
                                   }
                               });
                                break;
                        }
                    }
                });
                break;
            case R.id.tv_driver:
                getP().getU().showTip(getIndex(),new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case R.id.tv_sure:
                                getP().getD().login(LocalValue.getLoginReq(), new UINetAdapter<LoginResBean>(getContext()) {
                                    @Override
                                    public void onResult(boolean success, String msg, LoginResBean o) {
                                        getP().getU().getUserTypeReqBean().setId(o.getUserId());
                                        getP().getU().getUserTypeReqBean().setUserType(UserTypeReqBean.USER_TYPE_DRIVER);
                                        getP().getD().setUserType(getP().getU().getUserTypeReqBean(), new UINetAdapter<UserTypeResBean>(getContext()) {
                                            @Override
                                            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                                                super.onNetFinish(haveData, url, baseResBean);
                                                stopLoading();
                                                if("200".equals(baseResBean.getCode())){
                                                    if(getArguments().getBoolean("regist")){
                                                        FragManager.getInstance().finish(getActivity().getSupportFragmentManager(),getIndex());
                                                    }else{
                                                        LoginResBean resBean = LocalValue.getLoginInfo();
                                                        resBean.setUserType(UserTypeReqBean.USER_TYPE_DRIVER);
                                                        LocalValue.saveLoginInfo(resBean);
                                                        IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                                                    }
                                                }
                                            }
                                        });
                                    }
                                });
                                break;
                        }
                    }
                });
                break;
        }
    }

}
