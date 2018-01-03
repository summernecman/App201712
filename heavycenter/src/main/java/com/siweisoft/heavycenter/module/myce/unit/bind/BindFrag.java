package com.siweisoft.heavycenter.module.myce.unit.bind;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.fragment.FragManager;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.trans.detail.TransDetailFrag;
import com.siweisoft.heavycenter.module.myce.unit.newunit.NewFrag;

import butterknife.OnClick;

public class BindFrag extends AppFrag<BindUIOpe,BindDAOpe> implements ViewListener,OnRefreshListener {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this);
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData(),this);
    }


    @OnClick({R.id.ftv_right,R.id.ftv_right2})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                if(FragManager.getInstance().getFragMaps().get(getIndex())!=null&& FragManager.getInstance().getFragMaps().get(getIndex()).size()>0){
                    FragManager.getInstance().finish(getActivity().getSupportFragmentManager(),getIndex());
                }
            case R.id.ftv_right:

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
                switch (v.getId()){
                    case R.id.tv_root:
                       // FragManager.getInstance().startFragment(activity.getSupportFragmentManager(), getIndex(),new SearchFrag());
                        break;
                }
                break;
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(2000);
        getP().getU().LoadListData(getP().getD().getData(),this);
    }
}
