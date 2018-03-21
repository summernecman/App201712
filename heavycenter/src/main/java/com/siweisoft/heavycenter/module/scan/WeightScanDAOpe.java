package com.siweisoft.heavycenter.module.scan;

//by summer on 2018-01-25.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.locd.scan.user.UserInfo;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.scan.weight.WeightReq;
import com.siweisoft.heavycenter.data.netd.scan.weight.WeightRes;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;

public class WeightScanDAOpe extends BaseDAOpe {


    public WeightScanDAOpe() {
    }


    public void logic(final AppFrag appFrag, UserInfo userInfo){


        if( LocalValue.get登录返回信息().getUserType()== UserTypeReqBean.驾驶员){
            ToastUtil.getInstance().showShort(appFrag.getBaseAct(),"称重");
            //默认扫码地磅处理
            WeightReq weightReq = new WeightReq();
            weightReq.setCompanyId(userInfo.getCompanyID());
            weightReq.setDbNo(userInfo.getNo());
            weightReq.setUserId(StringUtil.getStr(LocalValue.get登录返回信息().getUserId()));
            NetDataOpe.Scan.triggerWeigh(appFrag.getContext(), weightReq,new NetAdapter<WeightRes>(appFrag.getBaseAct()){
                @Override
                public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                    super.onNetFinish(haveData, url, baseResBean);
                    ToastUtil.getInstance().showLong(appFrag.getBaseAct(), GsonUtil.getInstance().toJson(baseResBean));
                }
            });
            return;
        }

        if( LocalValue.get登录返回信息().getUserType()== UserTypeReqBean.非驾驶员){
            ToastUtil.getInstance().showLong(appFrag.getBaseAct(), "查看地磅信息");
            return;
        }
    }
}
