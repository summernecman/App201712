package com.siweisoft.heavycenter.module.myce.unit.bind;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
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

public class BindFrag extends AppFrag<BindUIOpe,BindDAOpe> implements ViewListener,OnRefreshListener {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this);
        getP().getU().initRecycle();
        getP().getD().getData(new UINetAdapter<ListResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, ListResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o,BindFrag.this);
            }
        });
    }


    @OnClick({R.id.ftv_right,R.id.ftv_right2,R.id.iv_search})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right:

                break;
            case R.id.ftv_right2:
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),MainAct.ID_CONTENT,new NewFrag());
                break;
            case R.id.iv_search:
                getP().getD().searchUnit(getP().getU().getSearchReqBean(),new UINetAdapter<SearchResBean>(getActivity()){
                    @Override
                    public void onResult(boolean success, String msg, SearchResBean o) {
                        super.onResult(success, msg, o);
                        getP().getU().LoadListData(o,BindFrag.this);
                    }
                });
                break;
        }
    }

    @Override
    public void onInterupt(int type, final View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                if(getArguments().getInt(ValueConstant.DATA_DATA,-1)==BindDAOpe.BIND_UNIT){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(ValueConstant.DATA_DATA2, (Serializable) v.getTag(R.id.data));
                    FragManager.getInstance().finish(getActivity().getSupportFragmentManager(),getIndex(),bundle);
                }
                getP().getU().showTip(new View.OnClickListener(){

                    @Override
                    public void onClick(View vv) {
                        UnitInfo unitInfo = (UnitInfo) v.getTag(R.id.data);
                        getP().getD().bindUnit(unitInfo.getId(), new UINetAdapter<BindResBean>(getActivity()) {
                            @Override
                            public void onResult(boolean success, String msg, BindResBean o) {
                                super.onResult(success, msg, o);
                                getP().getD().getInfo(new UINetAdapter<LoginResBean>(getContext()) {
                                    @Override
                                    public void onResult(boolean success, String msg, LoginResBean o) {
                                        super.onResult(success, msg, o);
                                        if(success){
                                            LocalValue.saveLoginInfo(o);
                                            ((MainAct)getActivity()).ddd();
                                        }
                                    }
                                });
                            }
                        });

                    }
                });
                // FragManager.getInstance().startFragment(activity.getSupportFragmentManager(), getIndex(),new SearchFrag());
                break;
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(2000);
        getP().getD().getData(new UINetAdapter<ListResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, ListResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o,BindFrag.this);
            }
        });
    }
}
