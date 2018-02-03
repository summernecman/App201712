package com.siweisoft.heavycenter.module.main.store.check;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.check.CheckStoreResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;

import butterknife.OnClick;

public class CheckFrag extends AppFrag<CheckUIOpe,CheckDAOpe> {

    @Override
    public void initNow() {
        super.initNow();
        getP().getU().initRecycle();
        getP().getD().storesInfo(new UINetAdapter<StoresResBean>(activity) {
            @Override
            public void onResult(boolean success, String msg, StoresResBean o) {
                super.onResult(success, msg, o);
                if(success){
                    getP().getD().setStoresResBean(o);
                    getP().getU().LoadListData(getP().getD().getStoresResBean());
                    getP().getD().setInitdata(true);
                }
            }
        });
    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                if(getP().getD().canGo()){
                    getP().getD().checkStore(getP().getD().getCheckStoreReqBean(getP().getD().getStoresResBean()), new UINetAdapter<CheckStoreResBean>(activity) {
                        @Override
                        public void onResult(boolean success, String msg, CheckStoreResBean o) {
                            super.onResult(success, msg, o);
                            if(success){
                                getArguments().putBoolean(ValueConstant.FRAG_KEY,true);
                                getBaseUIActivity().onBackPressed();
                            }
                        }
                    });
                }
                break;
        }
    }
}
