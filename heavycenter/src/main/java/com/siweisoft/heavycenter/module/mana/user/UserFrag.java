package com.siweisoft.heavycenter.module.mana.user;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.network.news.NetArrayAdapter;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUserResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.user.news.NewFrag;

import java.util.ArrayList;

import butterknife.OnClick;

public class UserFrag extends AppFrag<UserUIOpe,UserDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getD().unitUsers(new UINetAdapter<UnitUserResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, UnitUserResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o.getResults());
            }
        });

    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new NewFrag());
                break;
        }
    }
}
