package com.siweisoft.heavycenter.module.myce.unit.news;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.StringUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.module.myce.unit.addr.AddrFrag;
import com.siweisoft.heavycenter.module.myce.unit.area.prov.ProvFrag;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindDAOpe;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindFrag;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {

    @OnClick({R.id.unitaddr,R.id.upunit,R.id.area})
    public void onClick(View v) {
        super.onClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.unitaddr:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new AddrFrag());
                break;
            case R.id.upunit:
                bundle.putInt(ValueConstant.DATA_DATA, BindDAOpe.UP_UNIT);
                bundle.putInt(ValueConstant.FARG_REQ,3);
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new BindFrag(),bundle);
                break;
            case R.id.area:
                bundle.putInt(ValueConstant.FARG_REQ,4);
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new ProvFrag());
                break;
        }
    }

    @Override
    public void onRestart(int res, Bundle bundle) {
        super.onRestart(res, bundle);
        switch (res){
            case 3:
                if(getArguments()!=null && getArguments().getSerializable(ValueConstant.DATA_DATA2)!=null){
                    UnitInfo unit = (UnitInfo) getArguments().getSerializable(ValueConstant.DATA_DATA2);
                    getP().getU().initUI(unit);
                }
                break;
            case 4:
                getP().getU().getNewReqBean().setBelongArea(StringUtil.getStr(bundle.getString(ValueConstant.DATA_RES)));
                break;
        }
    }
}
