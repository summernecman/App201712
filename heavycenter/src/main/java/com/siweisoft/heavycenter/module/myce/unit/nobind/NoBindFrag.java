package com.siweisoft.heavycenter.module.myce.unit.nobind;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindFrag;

import butterknife.OnClick;

public class NoBindFrag extends AppFrag<NoBindUIOpe,NoBindDAOpe> {

    @OnClick({R.id.ftv_right,R.id.bind,R.id.ftv_back})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.ID_CONTENT,new BindFrag());
                break;
            case R.id.ftv_back:
                ((MainAct)getActivity()).getP().getU().switchDrawer();
                break;
        }
    }
}
