package com.siweisoft.heavycenter.module.mana.car;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.car.news.NewFrag;

import butterknife.OnClick;

public class CarFrag extends AppFrag<CarUIOpe,CarDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initPages(fragment,getP().getD().getPages(getIndex()));

    }

    @OnClick({R.id.ftv_right})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)(getActivity())).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),getIndex(),new NewFrag());
                break;
        }
    }

}
