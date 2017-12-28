package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.car.CarFrag;
import com.siweisoft.heavycenter.module.mana.good.GoodFrag;
import com.siweisoft.heavycenter.module.mana.store.StoreFrag;
import com.siweisoft.heavycenter.module.mana.user.UserFrag;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindFrag;

import butterknife.OnClick;

public class MyceFrag extends AppFrag<MyceUIOpe,MyceDAOpe> {

    @Override
    public void initData() {
        super.initData();
        setIndex(((MainAct)(getActivity())).getP().getU().getPos_content());
        getP().getU().hideOrShowManageFunction(((MainAct)(getActivity())).getP().getD().isRead());
    }

    @OnClick({R.id.item_car,R.id.item_good,R.id.item_store,R.id.item_user,R.id.item_unit})
    public void onClick(View v){

        FragManager.getInstance().clearAll(((MainAct)(getActivity())).getSupportFragmentManager(),getIndex());
        switch (v.getId()){
            case R.id.login:
                break;
                case R.id.ftv_back:

                break;
            case R.id.item_car:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new CarFrag());
                break;
            case R.id.item_good:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new GoodFrag());
                break;
            case R.id.item_store:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new StoreFrag());
                break;
            case R.id.item_user:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new UserFrag());
                break;
            case R.id.item_unit:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new BindFrag());
                break;
        }

        ((MainAct)getActivity()).getP().getU().switchDrawer();
    }
}
