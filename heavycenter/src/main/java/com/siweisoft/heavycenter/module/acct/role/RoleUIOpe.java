package com.siweisoft.heavycenter.module.acct.role;

//by summer on 2017-12-18.

import android.content.Context;
import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragAcctRoleBinding;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;
import com.siweisoft.heavycenter.module.view.center.DiaLogCenterFrag;

public class RoleUIOpe extends AppUIOpe<FragAcctRoleBinding> {


    public RoleUIOpe(Context context) {
        super(context);
    }

    public void showTip(int index,View.OnClickListener onClickListener){
        DiaLogCenterFrag diaLogCenterFrag = new DiaLogCenterFrag();
        diaLogCenterFrag.setOnClickListener(onClickListener);
        FragManager.getInstance().cover(getActivity(), AcctAct.ROOT_ID,diaLogCenterFrag,R.anim.fade_in,R.anim.fade_out);
    }
}
