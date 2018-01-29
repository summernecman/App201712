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
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.unit.news.NewResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.myce.unit.addr.AddrFrag;
import com.siweisoft.heavycenter.module.myce.unit.area.prov.ProvFrag;
import com.siweisoft.heavycenter.module.myce.unit.list.ListDAOpe;
import com.siweisoft.heavycenter.module.myce.unit.list.ListFrag;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {

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
                if(getP().getU().canGo()){
                    getP().getD().createUnit(getP().getU().getNewReqBean(getP().getD().getUnit()), new UINetAdapter<NewResBean>(getContext()) {
                        @Override
                        public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                            super.onNetFinish(haveData, url, baseResBean);
                            if("200".equals(baseResBean.getCode())){
                                getArguments().putBoolean(ValueConstant.DATA_RES,true);
                                getBaseUIActivity().onBackPressed();
                            }
                            stopLoading();
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onRestart(int res, Bundle bundle) {
        super.onRestart(res, bundle);
        switch (res){
            case 2:
                if(bundle==null || bundle.getString(ValueConstant.DATA_DATA)==null){
                    return;
                }
                getP().getD().getUnit().setCompanyAddress(bundle.getString(ValueConstant.DATA_DATA));
                getP().getU().initUI(getP().getD().getUnit());
                break;
            case 3:
                if(bundle!=null && bundle.getSerializable(ValueConstant.DATA_DATA2)!=null){
                    UnitInfo unit = (UnitInfo) bundle.getSerializable(ValueConstant.DATA_DATA2);
                    getP().getD().getUnit().setParentCompanyName(unit.getCompanyName());
                    getP().getD().getUnit().setParentCompanyId(unit.getId());
                    getP().getU().initUI(getP().getD().getUnit());
                }
                break;
            case 4:
                    if(bundle==null){
                    return ;
                }
                getP().getD().getUnit().setBelongArea(StringUtil.getStr(bundle.getString(ValueConstant.DATA_RES2)));
                getP().getD().getUnit().setBelongAreaDes(StringUtil.getStr(bundle.getString(ValueConstant.DATA_RES)));
                getP().getU().initUI(getP().getD().getUnit());
                break;
        }
    }
}
