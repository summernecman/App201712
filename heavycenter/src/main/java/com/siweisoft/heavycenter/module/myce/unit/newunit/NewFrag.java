package com.siweisoft.heavycenter.module.myce.unit.newunit;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindFrag;
import com.siweisoft.heavycenter.module.myce.unit.newunit.area.AreaFrag;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {

    @OnClick({R.id.addr,R.id.upunit,R.id.area})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.addr:

                break;
            case R.id.upunit:

                break;
            case R.id.area:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new AreaFrag());
                break;
        }
    }
}
