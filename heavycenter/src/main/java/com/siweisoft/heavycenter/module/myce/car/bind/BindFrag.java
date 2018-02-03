package com.siweisoft.heavycenter.module.myce.car.bind;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.network.news.UINetAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.bind.BindCarRes;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.module.main.MainAct;

import butterknife.OnClick;

public class BindFrag extends AppFrag<BindUIOpe,BindDAOpe> implements ViewListener,OnRefreshListener,OnFinishListener {

    @Override
    public void initNow() {
        super.initNow();
        getP().getU().initRecycle();
        getP().getU().initRefresh(this);
        onRefresh(null);
        getP().getU().实时搜索(this);
    }

    @OnClick({R.id.ftv_right,R.id.ftv_right2,R.id.iv_search})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right:

                break;
            case R.id.ftv_right2:
//                Bundle bundle = new Bundle();
//                bundle.putInt(ValueConstant.FARG_REQ,1);
//                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),MainAct.主界面ID,new NewFrag(),bundle);
                break;
            case R.id.iv_search:

                break;
        }
    }


    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                CarsResBean.CarInfoRes data = (CarsResBean.CarInfoRes) v.getTag(R.id.data);
                getP().getD().bindCar(data.getVehicleId(), new UINetAdapter<BindCarRes>(getActivity()) {
                    @Override
                    public void onResult(boolean success, String msg, BindCarRes o) {
                        super.onResult(success, msg, o);
                        if(success){
                            ((MainAct)getBaseUIActivity()).getP().getD().getMyceFrag().initUINET();
                            getBaseUIActivity().onBackPressed();
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getU().clearKey();
        getP().getD().Cars(new UINetAdapter<CarsResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, CarsResBean o) {
                super.onResult(success, msg, o);
                getP().getD().setNetCars(o);
                getP().getU().LoadListData(getP().getD().getSelCars(""),BindFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }

    @Override
    public void onFinish(Object o) {
        getP().getU().LoadListData(getP().getD().getSelCars(o.toString()),BindFrag.this);
    }
}
