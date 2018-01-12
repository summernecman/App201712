package com.siweisoft.heavycenter.module.myce.unit.nobind;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindFrag;

import butterknife.OnClick;

public class NoBindFrag extends AppFrag<NoBindUIOpe,NoBindDAOpe> {

    @OnClick({R.id.ftv_right,R.id.bind})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bind:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), ((MainAct)getActivity()).getP().getU().getPos_content(),new BindFrag());
                break;
        }
    }
}
