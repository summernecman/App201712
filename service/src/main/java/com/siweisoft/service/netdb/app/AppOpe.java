package com.siweisoft.service.netdb.app;

//by summer on 2017-12-29.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.siweisoft.service.netdb.NetValue;
import com.siweisoft.service.netdb.agree.AgreeBean;

public class AppOpe extends BaseDAOpe implements AppI {



    public AppOpe(Context context) {
        super(context);
    }

    @Override
    public void CheckVersion(final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/app/getAppVersion"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                AppBean bean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), AppBean.class);
                onFinishListener.onFinish(bean);
            }
        });
    }
}
