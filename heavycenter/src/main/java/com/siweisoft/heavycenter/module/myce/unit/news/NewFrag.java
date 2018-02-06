package com.siweisoft.heavycenter.module.myce.unit.news;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.StringUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.unit.news.NewResBean;
import com.siweisoft.heavycenter.data.netd.unit.update.UpdateUnitRes;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.myce.unit.addr.AddrFrag;
import com.siweisoft.heavycenter.module.myce.unit.area.prov.ProvFrag;
import com.siweisoft.heavycenter.module.myce.unit.list.ListDAOpe;
import com.siweisoft.heavycenter.module.myce.unit.list.ListFrag;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {

    public static final String 展示单位信息 = "展示单位信息";

    public static final String 新建单位 = "新建单位";

    public static final String 修改单位信息 = "修改单位信息";

    @Override
    public void initdelay() {
        super.initdelay();
        switch (getArguments().getString(ValueConstant.DATA_TYPE)){
            case 展示单位信息:
                getP().getD().getInfo(getArguments().getInt(ValueConstant.DATA_DATA,-1),new UINetAdapter<UnitInfo>(activity) {
                    @Override
                    public void onSuccess(UnitInfo o) {
                        getP().getD().setUnit(o);
                        getP().getU().initinfo(getP().getD().getUnit());
                    }
                });
                break;
            case 新建单位:
                break;
            case 修改单位信息:
                getP().getU().updateInfo();
                getP().getD().getInfo(getArguments().getInt(ValueConstant.DATA_DATA,-1),new UINetAdapter<UnitInfo>(activity) {
                    @Override
                    public void onSuccess(UnitInfo o) {
                        getP().getD().setUnit(o);
                        getP().getD().getUnit().setBelongAreaDes(getP().getD().getUnit().getBelongArea());
                        getP().getD().getUnit().setBelongArea(getP().getD().getUnit().getBelongAreaNo());
                        getP().getU().initinfo(getP().getD().getUnit());
                    }
                });
                break;
        }

    }


    @OnClick({R.id.unitaddr,R.id.upunit,R.id.area,R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        final Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.unitaddr:
                bundle.putInt(ValueConstant.FARG_REQ,2);
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,new AddrFrag(),bundle);
                break;
            case R.id.upunit:
                bundle.putInt(ValueConstant.DATA_DATA, ListDAOpe.UP_UNIT);
                bundle.putInt(ValueConstant.FARG_REQ,3);
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,new ListFrag(),bundle);
                break;
            case R.id.area:
                bundle.putInt(ValueConstant.FARG_REQ,4);
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,new ProvFrag(),bundle);
                break;
            case R.id.ftv_right2:
                switch (getArguments().getString(ValueConstant.DATA_TYPE)){
                    case 展示单位信息:
                        getP().getD().unBinUnit(new UINetAdapter<UnBindResBean>(activity) {
                            @Override
                            public void onSuccess(UnBindResBean o) {
                                getP().getD().getUserInfo(new UINetAdapter<LoginResBean>(getContext()) {
                                    @Override
                                    public void onSuccess(LoginResBean o) {
                                        LocalValue.save登录返回信息(o);
                                        ((MainAct)getBaseUIActivity()).reStart();
                                    }
                                });
                            }
                        });
                        break;
                    case 新建单位:
                        if(getP().getU().canGo()){
                            getP().getD().createUnit(getP().getU().getNewReqBean(getP().getD().getUnit()), new UINetAdapter<NewResBean>(getContext()) {
                                @Override
                                public void onSuccess(NewResBean o) {
                                    getArguments().putBoolean(ValueConstant.DATA_RES,true);
                                    getBaseUIActivity().onBackPressed();
                                }
                            });
                        }
                        break;
                    case 修改单位信息:
                        if(getP().getU().canGo()){
                            getP().getD().updateUnit(getP().getU().getUpdateUnitReq(getP().getD().getUnit()), new UINetAdapter<UpdateUnitRes>(getActivity()) {
                                @Override
                                public void onSuccess(UpdateUnitRes o) {
                                    getP().getD().getInfo(getArguments().getInt(ValueConstant.DATA_DATA,-1),new UINetAdapter<UnitInfo>(activity) {
                                        @Override
                                        public void onSuccess(UnitInfo o) {
                                            getP().getD().setUnit(o);
                                            getP().getD().getUnit().setBelongAreaDes(getP().getD().getUnit().getBelongArea());
                                            getP().getD().getUnit().setBelongArea(getP().getD().getUnit().getBelongAreaNo());
                                            getP().getU().initinfo(getP().getD().getUnit());
                                        }
                                    });
                                }
                            });
                        }
                        break;
                }
                break;
        }
    }

    @Override
    public void onRestart(int res, Bundle bundle) {
        super.onRestart(res, bundle);
        switch (res){
            case 2:
                if(bundle==null || bundle.getSerializable(ValueConstant.DATA_DATA)==null){
                    return;
                }
                UnitInfo unitInfo = (UnitInfo) bundle.getSerializable( ValueConstant.DATA_DATA);
                getP().getD().getUnit().setCompanyLng(unitInfo.getCompanyLng());
                getP().getD().getUnit().setCompanyLat(unitInfo.getCompanyLat());
                getP().getD().getUnit().setCompanyAddress(unitInfo.getCompanyAddress());
                getP().getU().initinfo(getP().getD().getUnit());
                break;
            case 3:
                if(bundle!=null && bundle.getSerializable(ValueConstant.DATA_DATA2)!=null){
                    UnitInfo unit = (UnitInfo) bundle.getSerializable(ValueConstant.DATA_DATA2);
                    getP().getD().getUnit().setParentCompanyName(unit.getCompanyName());
                    getP().getD().getUnit().setParentCompanyId(unit.getId());
                    getP().getU().initinfo(getP().getD().getUnit());
                }
                break;
            case 4:
                    if(bundle==null){
                    return ;
                }
                getP().getD().getUnit().setBelongArea(StringUtil.getStr(bundle.getString(ValueConstant.DATA_RES2)));
                getP().getD().getUnit().setBelongAreaDes(StringUtil.getStr(bundle.getString(ValueConstant.DATA_RES)));
                getP().getU().initinfo(getP().getD().getUnit());
                break;
        }
    }
}
