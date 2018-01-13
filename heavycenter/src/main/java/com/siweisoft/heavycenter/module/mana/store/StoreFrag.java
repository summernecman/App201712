package com.siweisoft.heavycenter.module.mana.store;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.store.news.NewFrag;

import butterknife.OnClick;

public class StoreFrag extends AppFrag<StoreUIOpe,StoreDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData(),this);

        getP().getD().storesInfo(new UINetAdapter<StoresResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, StoresResBean o) {
                super.onResult(success, msg, o);
                LogUtil.E(o);
            }
        });
    }


    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),getIndex(),new NewFrag());
                break;
        }
    }


    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                //FragManager.getInstance().startFragment(activity.getSupportFragmentManager(), getIndex(),new CheckFrag());
                break;
        }
    }
}
