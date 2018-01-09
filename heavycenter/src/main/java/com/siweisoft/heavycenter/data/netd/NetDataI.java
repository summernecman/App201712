package com.siweisoft.heavycenter.data.netd;

//by summer on 2018-01-09.

import com.android.lib.network.bean.res.BaseResBean;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginReqBean;
import com.siweisoft.heavycenter.data.netd.acct.regist.RegistReqBean;

public interface NetDataI {

    public void onRegist(RegistReqBean reqBean, BaseResBean resBean);

    public void onLogin(LoginReqBean reqBean, BaseResBean resBean);


}
