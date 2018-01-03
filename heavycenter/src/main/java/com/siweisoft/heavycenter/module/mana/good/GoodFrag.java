package com.siweisoft.heavycenter.module.mana.good;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.good.news.NewFrag;

import butterknife.OnClick;

public class GoodFrag extends AppFrag<GoodUIOpe,GoodDAOpe> implements ViewListener{
    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData(),this);
    }


    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)(getActivity())).getP().getU().switchDrawer();
                break;
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
