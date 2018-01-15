package com.siweisoft.heavycenter.module.main.msg.car;

//by summer on 2017-12-11.

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.network.news.UINetAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;
import com.siweisoft.heavycenter.module.main.MainAct;

public class CarFrag extends AppFrag<CarUIOpe,CarDAOpe> implements OnRefreshListener,OnLoadmoreListener {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this,this);
        getP().getU().autoRefresh();
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getU().finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().getMsgCar(new UINetAdapter<MsgsResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, MsgsResBean o) {
                super.onResult(success, msg, o);
                getP().getU().finishRefresh();
                getP().getU().LoadListData(o.getResults());
            }
        });
    }

}
