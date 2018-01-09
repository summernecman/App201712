package com.siweisoft.heavycenter.data.netd;

//by summer on 2018-01-09.

import android.content.Context;
import android.net.NetworkRequest;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.network.netadapter.UINetAdapter;
import com.siweisoft.heavycenter.data.netd.acct.code.CodeReqBean;
import com.siweisoft.heavycenter.data.netd.acct.forget.ForGetReqBean;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginReqBean;
import com.siweisoft.heavycenter.data.netd.acct.regist.RegistReqBean;

public class NetDataOpe {

    public static void getCode(Context context, String moudle, CodeReqBean reqBean, OnNetWorkReqAdapter adapter) {
        NetWork.postData(context,moudle,reqBean,adapter);
    }

    public static void onRegist(Context context,String moudle, RegistReqBean reqBean, UINetAdapter adapter) {

        NetWork.postData(context,moudle,reqBean,adapter);
    }

    public static void onLogin(Context context,String moudle, LoginReqBean reqBean, UINetAdapter adapter) {
        NetWork.postData(context,moudle,reqBean,adapter);
    }

    public static void updatePwd(Context context, String moudle, ForGetReqBean reqBean, UINetAdapter adapter) {
        NetWork.postData(context,moudle,reqBean,adapter);
    }
}
