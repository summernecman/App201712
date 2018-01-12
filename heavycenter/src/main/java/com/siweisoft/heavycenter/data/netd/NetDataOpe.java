package com.siweisoft.heavycenter.data.netd;

//by summer on 2018-01-09.

import android.content.Context;
import android.support.annotation.IntegerRes;

import com.android.lib.network.news.NetArrayI;
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
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsReqBean;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresReqBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityReqBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListReqBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.unit.news.NewReqBean;
import com.siweisoft.heavycenter.data.netd.unit.news.NewResBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchReqBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchResBean;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUserResBean;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUsersReqBean;
import com.siweisoft.heavycenter.data.netd.user.info.InfoReqBean;
import com.siweisoft.heavycenter.data.netd.user.info.InfoResBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindResBean;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindResBean;
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

    public static void getCity(Context context, CityReqBean reqBean, NetArrayI<CityResBean> adapter) {
        NetGet.getData(context,NetValue.获取地址("/metadata/getCity"),reqBean,adapter);
    }

    public static class Unit{

        public static void search(Context context, SearchReqBean reqBean, NetI<SearchResBean> adapter) {
            NetGet.getData(context,NetValue.获取地址("/company/search"),reqBean,adapter);
        }

        public static void getInfo(Context context, com.siweisoft.heavycenter.data.netd.unit.info.InfoReqBean reqBean, NetI<UnitInfo> adapter) {
            NetGet.getData(context,NetValue.获取地址("/company/getDetail"),reqBean,adapter);
        }

        public static void unitUsers(Context context, UnitUsersReqBean reqBean, NetI<UnitUserResBean> adapter) {
            NetGet.getData(context,NetValue.获取地址("/user/list"),reqBean,adapter);
        }
    }

    public static class Msg{

        public static void list(Context context, MsgsReqBean reqBean, NetI<MsgsResBean> adapter) {
            NetGet.getData(context,NetValue.获取地址("/message/list"),reqBean,adapter);
        }

    }

    public static class User{

        public static void binUnit(Context context, BindReqBean reqBean, NetI<BindResBean> adapter) {
            NetGet.postData(context,NetValue.获取地址("/user/bindCompany"),reqBean,adapter);
        }

        public static void unBinUnit(Context context, UnBindReqBean reqBean, NetI<UnBindResBean> adapter) {
            NetGet.postData(context,NetValue.获取地址("/user/unBindCompany"),reqBean,adapter);
        }


        public static void getInfo(Context context, InfoReqBean reqBean, NetI<LoginResBean> adapter) {
            NetGet.getData(context,NetValue.获取地址("/user/getDetail"),reqBean,adapter);
        }

    }

    public static class Mana{

        public static class Store{

            public static void sotresInfo(Context context, StoresReqBean reqBean, NetI<StoresResBean> adapter) {
                NetGet.getData(context,NetValue.获取地址("/wareHouse/list"),reqBean,adapter);
            }

        }


        public static class Car{

            public static void Cars(Context context, CarsReqBean reqBean, NetI<CarsResBean> adapter) {
                NetGet.getData(context,NetValue.获取地址("/vehicle/list"),reqBean,adapter);
            }


        }

    }


}
