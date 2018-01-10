package com.siweisoft.heavycenter.data.netd;

//by summer on 2018-01-09.

import android.content.Context;

import com.android.lib.network.news.NetGet;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.netd.acct.code.CodeReqBean;
import com.siweisoft.heavycenter.data.netd.acct.code.CodeResBean;
import com.siweisoft.heavycenter.data.netd.acct.forget.ForGetReqBean;
import com.siweisoft.heavycenter.data.netd.acct.forget.ForGetResBean;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginReqBean;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutReqBean;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutResBean;
import com.siweisoft.heavycenter.data.netd.acct.regist.RegistReqBean;
import com.siweisoft.heavycenter.data.netd.acct.regist.RegistResBean;
import com.siweisoft.heavycenter.data.netd.acct.rename.ReNameReqBean;
import com.siweisoft.heavycenter.data.netd.acct.rename.ReNameResBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityReqBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListReqBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.data.netd.unit.news.NewReqBean;
import com.siweisoft.heavycenter.data.netd.unit.news.NewResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeResBean;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NetDataOpe {

    public static void getCode(Context context, String moudle, CodeReqBean reqBean, NetI<CodeResBean> adapter) {
        NetGet.postData(context,moudle,reqBean,adapter);
    }

    public static void onRegist(Context context,String moudle, RegistReqBean reqBean, NetI<RegistResBean> adapter) {

        NetGet.postData(context,moudle,reqBean,adapter);
    }

    public static void onLogin(Context context,String moudle, LoginReqBean reqBean, NetI<LoginResBean> adapter) {
        NetGet.postData(context,moudle,reqBean,adapter);
    }

    public static void updatePwd(Context context, String moudle, ForGetReqBean reqBean, NetI<ForGetResBean> adapter) {
        NetGet.postData(context,moudle,reqBean,adapter);
    }

    public static void setUserType(Context context, String moudle, UserTypeReqBean reqBean, NetI<UserTypeResBean> adapter) {
        NetGet.postData(context,moudle,reqBean,adapter);
    }

    public static void logOut(Context context, String moudle, LogOutReqBean reqBean, NetI<LogOutResBean> adapter) {
        NetGet.postData(context,moudle,reqBean,adapter);
    }

    public static void reName(Context context, ReNameReqBean reqBean, NetI<ReNameResBean> adapter) {
        NetGet.postData(context,NetValue.获取地址("/user/update"),reqBean,adapter);
    }

    public static void unitList(Context context, ListReqBean reqBean, NetI<ListResBean> adapter) {
        NetGet.getData(context,NetValue.获取地址("/company/list"),reqBean,adapter);
    }

    public static void unitCreate(Context context, NewReqBean reqBean, NetI<NewResBean> adapter) {
        NetGet.getData(context,NetValue.获取地址("/company/insert"),reqBean,adapter);
    }

    public static void getCity(Context context, CityReqBean reqBean, NetI<ArrayList<CityResBean>> adapter) {
        NetGet.getData(context,NetValue.获取地址("/metadata/getCity"),reqBean,adapter);
    }
}
