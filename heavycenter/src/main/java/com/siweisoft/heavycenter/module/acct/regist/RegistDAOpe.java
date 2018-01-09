package com.siweisoft.heavycenter.module.acct.regist;

//by summer on 2017-12-14.

import android.content.Context;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.network.netadapter.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.thread.ThreadUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.acct.code.CodeReqBean;
import com.siweisoft.heavycenter.data.netd.acct.code.CodeResBean;
import com.siweisoft.heavycenter.data.netd.acct.regist.RegistReqBean;

public class RegistDAOpe extends AppDAOpe {

    private ThreadUtil threadUtil = new ThreadUtil();

    public RegistDAOpe(Context context) {
        super(context);
    }

    public String getImageUrl(){
        return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513325040026&di=9e408824bb71605801a3e73997457851&imgtype=0&src=http%3A%2F%2Fbbs.static.coloros.com%2Fdata%2Fattachment%2Fforum%2F201503%2F06%2F183706dti1utuig1rqa13y.jpg";
    }

    public void regist(RegistReqBean reqBean, UINetAdapter adapter){
        reqBean.setIdentityType(RegistReqBean.IDENTITY_TYPE_PHONE);
        NetDataOpe.onRegist(getActivity(), NetValue.获取地址("/user/insertAPP"),reqBean,adapter);
    }

    public ThreadUtil getThreadUtil() {
        return threadUtil;
    }

    public void getCode(CodeReqBean reqBean){
        NetDataOpe.getCode(getActivity(), NetValue.获取地址("/user/getSecurityCode"), reqBean, new OnNetWorkReqAdapter(getActivity()) {
            @Override
            public void onNetWorkResult(boolean success, BaseResBean o) {
                CodeResBean codeResBean = GsonUtil.getInstance().fromJson(o.getData().toString(),CodeResBean.class);
                ToastUtil.getInstance().showLong(getActivity(),codeResBean.getMessage());
            }
        });
    }
}
