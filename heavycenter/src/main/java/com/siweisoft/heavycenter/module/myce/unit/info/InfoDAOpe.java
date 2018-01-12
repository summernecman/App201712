package com.siweisoft.heavycenter.module.myce.unit.info;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.unit.info.InfoReqBean;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindResBean;

public class InfoDAOpe extends AppDAOpe {


    public InfoDAOpe(Context context) {
        super(context);
    }

    public void getInfo(NetI<UnitInfo> adapter){
        InfoReqBean infoReqBean = new InfoReqBean();
        infoReqBean.setId(LocalValue.getLoginInfo().getCompanyId());
        NetDataOpe.Unit.getInfo(getActivity(),infoReqBean,adapter);
    }

    public void unBinUnit(NetI<UnBindResBean> adapter){
        UnBindReqBean unBindReqBean = new UnBindReqBean();
        unBindReqBean.setId(LocalValue.getLoginInfo().getUserId());
        unBindReqBean.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        NetDataOpe.User.unBinUnit(getActivity(),unBindReqBean,adapter);
    }
}
