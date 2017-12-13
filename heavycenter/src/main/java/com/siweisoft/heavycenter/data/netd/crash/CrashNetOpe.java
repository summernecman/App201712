package com.siweisoft.heavycenter.data.netd.crash;

//by summer on 2017-12-12.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.heavycenter.data.netd.NetApi;

public class CrashNetOpe extends BaseDAOpe{

    public CrashNetOpe(Context context) {
        super(context);
    }

    public static void setCrash(Context context,String txt){
        BaseReqBean baseReqBean = new BaseReqBean();
        CrashBean crash = new CrashBean();
        crash.setCreatedtime(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        crash.setError(txt);
        CrashBean.UserBeanBean userBeanBean = new CrashBean.UserBeanBean();
        userBeanBean.setId(0);
        userBeanBean.setName("");
        userBeanBean.setPhone("");
        userBeanBean.setUsertype(0);
        crash.setUserBean(userBeanBean);
        crash.setPlatform(context.getPackageName());
        baseReqBean.setData(GsonUtil.getInstance().toJson(crash));
        NetWork.postData(context, NetApi.奔溃日志, baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean success, BaseResBean o) {

            }
        });
    }
}
