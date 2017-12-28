package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.FragManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.trans.detail.TransDetailFrag;

public class TransFrag extends AppFrag<TransUIOpe,TransDAOpe> implements ViewListener,OnRefreshListener {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this);
        getP().getU().initRecycle();
        //getP().getU().LoadListData(getP().getD().getData(),this);
        getP().getU().ddd();
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.E("setUserVisibleHint:"+isVisibleToUser);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()){
                    case R.id.tv_root:
                        FragManager.getInstance().startFragment(activity.getSupportFragmentManager(), getIndex(),new TransDetailFrag());
                        break;
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)getActivity()).getP().getU().switchDrawer();
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(2000);
        getP().getU().LoadListData(getP().getD().getData(),this);
    }
}
