package com.siweisoft.heavycenter.module.myce.unit.list;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchResBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.myce.unit.news.NewFrag;

import java.io.Serializable;

import butterknife.OnClick;

public class ListFrag extends AppFrag<ListUIOpe,ListDAOpe> implements ViewListener,OnRefreshListener,OnFinishListener {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this);
        getP().getU().initRecycle();
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
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,1);
                if(LocalValue.get登录返回信息().getBindCompanyState()!=LoginResBean.BIND_UNIT_STATE_BINDED){
                    bundle.putInt(ValueConstant.FARG_REQ,2);
                }
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),MainAct.主界面ID,new NewFrag(),bundle);
                break;
            case R.id.iv_search:
                getP().getD().searchUnit(getP().getU().getSearchReqBean(),new UINetAdapter<SearchResBean>(activity){
                    @Override
                    public void onResult(boolean success, String msg, SearchResBean o) {
                        super.onResult(success, msg, o);
                        getP().getU().LoadListData(o,ListFrag.this);
                    }
                });
                break;
        }
    }

    @Override
    public void onInterupt(int type, final View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                if(getArguments().getInt(ValueConstant.DATA_DATA,-1)== ListDAOpe.UP_UNIT
                        ||getArguments().getInt(ValueConstant.DATA_DATA,-1)== ListDAOpe.SEL_UNIT){
                    getArguments().putSerializable(ValueConstant.DATA_DATA2, (Serializable) v.getTag(R.id.data));
                    getBaseUIActivity().onBackPressed();
                    return;
                }
                final UnitInfo unitInfo = (UnitInfo) v.getTag(R.id.data);

                getP().getD().getUnitInfo(unitInfo.getId(), new UINetAdapter<UnitInfo>(activity) {
                    @Override
                    public void onResult(boolean success, String msg, UnitInfo o) {
                        super.onResult(success, msg, o);
                        if(o.getCompanyIsNull()==UnitInfo.COMPANY_NULL){
                            getP().getU().showTip(new View.OnClickListener(){
                                @Override
                                public void onClick(View vv) {
                                    switch (vv.getId()){
                                        case R.id.tv_n:
                                            break;
                                        case R.id.tv_y:
                                            getP().getD().bindUnit(unitInfo.getId(), true,new UINetAdapter<BindResBean>(activity) {
                                                @Override
                                                public void onResult(boolean success, String msg, BindResBean o) {
                                                    super.onResult(success, msg, o);
                                                    getP().getD().getInfo(new UINetAdapter<LoginResBean>(getContext()) {
                                                        @Override
                                                        public void onResult(boolean success, String msg, LoginResBean o) {
                                                            super.onResult(success, msg, o);
                                                            if(success){
                                                                LocalValue.save登录返回信息(o);
                                                                getBaseUIActivity().onBackPressed();
                                                                ((MainAct)activity).reStart();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                            break;
                                    }
                                    getBaseUIActivity().onBackPressed();
                                }
                            });
                        }else{
                            getP().getD().bindUnit(unitInfo.getId(), false,new UINetAdapter<BindResBean>(activity) {
                                @Override
                                public void onResult(boolean success, String msg, BindResBean o) {
                                    super.onResult(success, msg, o);
                                    getP().getD().getInfo(new UINetAdapter<LoginResBean>(getContext()) {
                                        @Override
                                        public void onResult(boolean success, String msg, LoginResBean o) {
                                            super.onResult(success, msg, o);
                                            if(success){
                                                LocalValue.save登录返回信息(o);
                                                getBaseUIActivity().onBackPressed();
                                                ((MainAct)activity).reStart();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                    }
                });

                // FragManager.getInstance().startFragment(activity.getSupportFragmentManager(), getIndex(),new SearchFrag());
                break;
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getU().clearKey();
        getP().getD().getData(new UINetAdapter<ListResBean>(activity) {
            @Override
            public void onResult(boolean success, String msg, ListResBean o) {
                super.onResult(success, msg, o);
                o = new Test().getListResBean();
                getP().getD().setNetUnits(o);
                getP().getU().LoadListData(getP().getD().getSelUnits(""),ListFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }

    @Override
    public void onRestart(int res, Bundle bundle) {
        super.onRestart(res, bundle);
        switch (res){
            case 1:
                if(bundle==null||!bundle.getBoolean(ValueConstant.DATA_RES,false)){
                    return;
                }
                onRefresh(null);
                break;
            case 2:
                if(bundle==null||!bundle.getBoolean(ValueConstant.DATA_RES,false)){
                    return;
                }
                getBaseUIActivity().onBackPressed();
                ((MainAct)getActivity()).netRestart();
                break;
        }
    }

    @Override
    public void onFinish(Object o) {
        getP().getU().LoadListData(getP().getD().getSelUnits(o.toString()),ListFrag.this);
    }


    public void selUnit(UnitInfo unitInfo){
        getArguments().putSerializable(ValueConstant.DATA_DATA2,unitInfo);
        getBaseUIActivity().onBackPressed();
    }
}
