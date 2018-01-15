package com.siweisoft.heavycenter.module.mana.user;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUserResBean;
import com.siweisoft.heavycenter.module.mana.user.news.NewFrag;

import butterknife.OnClick;

public class UserFrag extends AppFrag<UserUIOpe,UserDAOpe> implements OnRefreshListener,OnLoadmoreListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getU().initRefresh(this,this);


    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),new NewFrag());
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getU().finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().unitUsers(new UINetAdapter<UnitUserResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, UnitUserResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o.getResults());
                getP().getU().finishRefresh();
            }
        });
    }
}
