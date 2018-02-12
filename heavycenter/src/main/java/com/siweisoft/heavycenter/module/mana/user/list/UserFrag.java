package com.siweisoft.heavycenter.module.mana.user.list;

//by summer on 2017-12-14.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.OjectUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.user.add.AddUserResBean;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUserResBean;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindResBean;
import com.siweisoft.heavycenter.data.netd.user.userrole.UserRoleRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.mana.user.news.NewFrag;

import butterknife.OnClick;

public class UserFrag extends AppFrag<UserUIOpe,UserDAOpe> implements OnRefreshListener,OnLoadmoreListener,ViewListener{

    @Override
    public void initNow() {
        super.initNow();
        if(OjectUtil.equals(getArguments().getString(UserValue.选取超级管理员KEY),UserValue.选取超级管理员)){
            getP().getU().setSwipe(false);
        }
    }

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getU().initRecycle();
        getP().getU().initRefresh(this,this);
        onRefresh(null);


    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,1);
                FragManager2.getInstance().start(getBaseUIAct(), get容器(),new NewFrag(),bundle);
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getU().finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().unitUsers(new UINetAdapter<UnitUserResBean>(this) {
            @Override
            public void onSuccess(UnitUserResBean o) {
                //o= new Test().getUnitUserResBean();
                getP().getU().LoadListData(o,UserFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()){
                    case R.id.smMenuViewRight:
                        UnitUserResBean.ResultsBean resultsBean  = (UnitUserResBean.ResultsBean) v.getTag(R.id.data);
                        int t = (int) v.getTag(R.id.type);
                        switch (t){
                            case 1:
                                Bundle bundle = new Bundle();
                                bundle.putString(UserValue.选取超级管理员KEY,UserValue.选取超级管理员);
                                bundle.putInt(ValueConstant.FARG_REQ,2);
                                FragManager2.getInstance().start(getBaseUIAct(), get容器(),new UserFrag(),bundle);
                                break;
                            case 0:
                                switch (resultsBean.getBindCompanyState()){
                                    case LoginResBean.BIND_UNIT_STATE_BINDED:
                                        if(getP().getD().canUnBind(resultsBean)){
                                            getP().getD().unBindUser(resultsBean.getUserId(), new UINetAdapter<UnBindResBean>(getBaseUIAct()) {
                                                @Override
                                                public void onResult(boolean success, String msg, UnBindResBean o) {
                                                    super.onResult(success, msg, o);
                                                    onRefresh(null);
                                                }
                                            });
                                        }
                                        break;
                                    default:
                                        getP().getD().addUser(resultsBean.getUserId(), new UINetAdapter<AddUserResBean>(this) {
                                            @Override
                                            public void onSuccess(AddUserResBean o) {
                                                onRefresh(null);
                                            }
                                        });
                                        break;
                                }
                                break;
                        }
                        break;
                    case R.id.smContentView:
                        UnitUserResBean.ResultsBean data  = (UnitUserResBean.ResultsBean) v.getTag(R.id.data);
                        if(OjectUtil.equals(getArguments().getString(UserValue.选取超级管理员KEY),UserValue.选取超级管理员)){
                            getArguments().putSerializable(ValueConstant.DATA_DATA,data);
                           getBaseUIAct().onBackPressed();
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public void onResult(int res, Bundle bundle) {
        super.onResult(res, bundle);
        switch (res){
            case 1:
                if(bundle==null || bundle.getBoolean(ValueConstant.FARG_TYPE,false)){
                    return;
                }
                if(bundle.getBoolean(ValueConstant.FARG_TYPE,false)){
                    onRefresh(null);
                }
                break;
            case 2:
                if(bundle==null || bundle.getSerializable(ValueConstant.DATA_DATA)==null){
                    return;
                }
                UnitUserResBean.ResultsBean data = (UnitUserResBean.ResultsBean) bundle.getSerializable(ValueConstant.DATA_DATA);
                getP().getD().setUserRole(data.getUserId(), new UINetAdapter<UserRoleRes>(this) {
                    @Override
                    public void onSuccess(UserRoleRes o) {
                        super.onSuccess(o);
                        getBaseAct().onBackPressed();
                        ((MainAct)getBaseAct()).go网络获取用户信息重新加载();
                    }
                });
                break;
        }
    }
}
