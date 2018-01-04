package com.siweisoft.heavycenter.module.myce.unit.info;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.myce.unit.addr.AddrFrag;
import com.siweisoft.heavycenter.module.myce.unit.newunit.area.prov.ProvFrag;

import butterknife.OnClick;

public class InfoFrag extends AppFrag<InfoUIOpe,InfoDAOpe> {

    @OnClick({R.id.ftv_right,R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.addr:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new AddrFrag());
                break;
            case R.id.upunit:

                break;
            case R.id.area:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new ProvFrag());
                break;
        }
    }
}
