package com.siweisoft.heavycenter.module.mana.good;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.good.list.GoodListRes;
import com.siweisoft.heavycenter.module.mana.good.news.NewFrag;

import butterknife.OnClick;

public class GoodFrag extends AppFrag<GoodUIOpe,GoodDAOpe> implements ViewListener,OnRefreshListener{
    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this);
        getP().getU().initRecycle();
        getP().getU().autoRefresh();
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
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                //FragManager.getInstance().startFragment(activity.getSupportFragmentManager(), getIndex(),new CheckFrag());
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().listGood(new UINetAdapter<GoodListRes>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, GoodListRes o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o,GoodFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }
}
