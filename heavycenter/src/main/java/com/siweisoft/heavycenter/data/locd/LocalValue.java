package com.siweisoft.heavycenter.data.locd;

//by summer on 2018-01-10.

import com.android.lib.util.GsonUtil;
import com.android.lib.util.SPUtil;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginReqBean;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;

public class LocalValue {

    public static final String ACCOUT ="HV_ACCOUT";

    public static final String PSWD ="HV_PSWD";

    public static final String LOGIN_INFO ="HV_LOGIN_INFO";

    public static final String LOGIN_REQ = "HV_LOGIN_REQ";

    public static LoginResBean getLoginInfo(){
        return GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(LOGIN_INFO),LoginResBean.class);
    }

    public static void saveLoginInfo(LoginResBean loginResBean){
        SPUtil.getInstance().saveStr(LOGIN_INFO,GsonUtil.getInstance().toJson(loginResBean));
    }

    public static LoginReqBean getLoginReq(){
        return GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(LOGIN_REQ),LoginReqBean.class);
    }

    public static void saveLoginReq(LoginReqBean loginReqBean){
        SPUtil.getInstance().saveStr(LOGIN_REQ,GsonUtil.getInstance().toJson(loginReqBean));
    }

    public static void saveUserId(int id){
        SPUtil.getInstance().saveInt("hv_id",id);
    }

    public static int getUserId(){
        return SPUtil.getInstance().getInt("hv_id");
    }
}
