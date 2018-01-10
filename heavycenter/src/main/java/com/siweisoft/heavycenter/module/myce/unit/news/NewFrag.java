package com.siweisoft.heavycenter.module.myce.unit.news;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.module.myce.unit.addr.AddrFrag;
import com.siweisoft.heavycenter.module.myce.unit.area.prov.ProvFrag;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindDAOpe;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindFrag;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {

    @OnClick({R.id.unitaddr,R.id.upunit,R.id.area})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.unitaddr:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new AddrFrag());
                break;
            case R.id.upunit:
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.DATA_DATA, BindDAOpe.UP_UNIT);
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new BindFrag(),bundle);
                break;
            case R.id.area:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new ProvFrag());
                break;
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        if(getArguments()!=null && getArguments().getSerializable(ValueConstant.DATA_DATA2)!=null){
            ListResBean.ResultsBean unit = (ListResBean.ResultsBean) getArguments().getSerializable(ValueConstant.DATA_DATA2);
            getP().getU().initUI(unit);
        }
    }
}
