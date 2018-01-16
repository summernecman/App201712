package com.siweisoft.heavycenter.module.main.store.check;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;

import butterknife.OnClick;

public class CheckFrag extends AppFrag<CheckUIOpe,CheckDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getD().storesInfo(new UINetAdapter<StoresResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, StoresResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o);
            }
        });
    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:

                break;
        }
    }
}
