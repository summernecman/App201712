package com.siweisoft.heavycenter.module.acct.role;

//by summer on 2017-12-18.

import android.content.Context;
import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.databinding.FragAcctRoleBinding;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;
import com.siweisoft.heavycenter.module.view.center.DiaLogCenterFrag;

public class RoleUIOpe extends AppUIOpe<FragAcctRoleBinding> {

    UserTypeReqBean userTypeReqBean = new UserTypeReqBean();


    public RoleUIOpe(Context context) {
        super(context);
    }

    public void showTip(int index,View.OnClickListener onClickListener){
        DiaLogCenterFrag diaLogCenterFrag = new DiaLogCenterFrag();
        diaLogCenterFrag.setOnClickListener(onClickListener,R.id.tv_sure,R.id.tv_close);
        FragManager2.getInstance().setStartAnim(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).start(getActivity(),AcctAct.账号,AcctAct.账号ID,diaLogCenterFrag);
    }

    public UserTypeReqBean getUserTypeReqBean() {
        //userTypeReqBean.setId(LocalValue.getLoginInfo());
        return userTypeReqBean;
    }
}
