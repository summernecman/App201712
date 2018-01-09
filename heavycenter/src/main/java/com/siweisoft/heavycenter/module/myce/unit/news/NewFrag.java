package com.siweisoft.heavycenter.module.myce.unit.news;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.myce.unit.addr.AddrFrag;
import com.siweisoft.heavycenter.module.myce.unit.area.prov.ProvFrag;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindFrag;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {

    @OnClick({R.id.addr,R.id.upunit,R.id.area})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.addr:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new AddrFrag());
                break;
            case R.id.upunit:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new BindFrag());
                break;
            case R.id.area:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new ProvFrag());
                break;
        }
    }
}
