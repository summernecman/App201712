package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.content.Context;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.databinding.FragMyceBinding;

public class MyceUIOpe extends AppUIOpe<FragMyceBinding> {

    public MyceUIOpe(Context context) {
        super(context);
    }

    @Override
    public void initUI(BaseUIFrag baseUIFrag) {
        super.initUI(baseUIFrag);
        bind.llHead.tvName.setText(StringUtil.getStr(LocalValue.getLoginInfo().getTrueName()));
        bind.llHead.tvPhone.setText(StringUtil.getStr(LocalValue.getLoginInfo().getTel()));
        if(LocalValue.getLoginInfo().getUserRole()==null){
            if(LocalValue.getLoginInfo().getUserType()== UserTypeReqBean.USER_TYPE_DRIVER){
                bind.llHead.tvRole.setText(UserTypeReqBean.USER_TYPE_DRIVER_CN);
            }else{
                bind.llHead.tvRole.setText(UserTypeReqBean.USER_TYPE_GENERAL_CN);
            }
            return;
        }
        switch (LocalValue.getLoginInfo().getUserRole()){
            case LoginResBean.USER_ROLE_ADMIN:
                bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_ADMIN_CN);
                break;
            case LoginResBean.USER_ROLE_DRIVER:
                bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_DRIVER_CN);
                break;
            case LoginResBean.USER_ROLE_GENERAL:
                bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_GENERAL_CN);
                break;
            case LoginResBean.USER_ROLE_SUPER_ADMIN:
                bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_SUPER_ADMIN_CN);
                break;
            case LoginResBean.USER_ROLE_SYS_ADMIN:
                bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_SYS_ADMIN_CN);
                break;
        }

        switch (LocalValue.getLoginInfo().getBindCompanyState()){
            case LoginResBean.BIND_UNIT_STATE_UNBIND:
                bind.itemUnit.getRightTV().setText(LoginResBean.BIND_UNIT_STATE_UNBIND_CN);
                break;
            case LoginResBean.BIND_UNIT_STATE_CHECK:
                bind.itemUnit.getRightTV().setText(LoginResBean.BIND_UNIT_STATE_CHECK_CN);
                break;
            case LoginResBean.BIND_UNIT_STATE_BINDED:
                bind.itemUnit.getRightTV().setText(LoginResBean.BIND_UNIT_STATE_BINDED_CN);
                break;
            case LoginResBean.BIND_UNIT_STATE_REJECT:
                bind.itemUnit.getRightTV().setText(LoginResBean.BIND_UNIT_STATE_REJECT_CN);
                break;


        }


    }

    public void hideOrShowManageFunction(boolean show){
        int vis = View.VISIBLE;
        if(show){
            vis = View.VISIBLE;
        }else{
            vis = View.GONE;
        }
        bind.itemCar.setVisibility(vis);
        bind.itemGood.setVisibility(vis);
        bind.itemStore.setVisibility(vis);
        bind.itemUser.setVisibility(vis);
    }


}
