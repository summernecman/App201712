package com.siweisoft.heavycenter.module.myce.unitinfo.bindunit;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.myce.unitinfo.newunit.NewFrag;

import butterknife.OnClick;

public class BindUnitFrag extends AppFrag<BindUnitUIOpe,BindUnitDAOpe> {

    @OnClick({R.id.ftv_right})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),getIndex(),new NewFrag());
                break;
        }
    }
}
