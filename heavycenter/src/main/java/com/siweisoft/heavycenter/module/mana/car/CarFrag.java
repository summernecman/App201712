package com.siweisoft.heavycenter.module.mana.car;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.car.news.NewFrag;

import butterknife.OnClick;

public class CarFrag extends AppFrag<CarUIOpe,CarDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initPages(fragment,getP().getD().getPages(getIndex()));

    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,MainAct.主界面ID,new NewFrag());
                break;
        }

    }

}
