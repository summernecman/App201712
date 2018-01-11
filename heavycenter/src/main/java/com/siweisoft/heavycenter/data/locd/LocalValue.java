package com.siweisoft.heavycenter.data.locd;

//by summer on 2018-01-10.

import com.android.lib.util.GsonUtil;
import com.android.lib.util.SPUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginReqBean;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LocalValue {

    public static final String ACCOUT ="HV_ACCOUT";

    public static final String PSWD ="HV_PSWD";

    public static final String LOGIN_INFO ="HV_LOGIN_INFO";

    public static final String LOGIN_REQ = "HV_LOGIN_REQ";

    public static final String AREA_INFO = "AREA_INFO";

    public static final String AREA_MAP_INFO = "AREA_MAP_INFO";

    public static final String AREA_LIST_INFO = "AREA_LIST_INFO";

    public static final String[] pros = new String[]{
            "安徽","澳门","北京",
            "重庆","福建","",
            "广东","广西","贵州",
            "甘肃","河北","河南",
            "黑龙江","湖北","湖南",
            "海南","吉林","江西",
            "江苏","辽宁","内蒙古",
            "宁夏","青海","上海",
            "山东","山西","四川",
            "陕西","天津","台湾",
            "西藏","新疆","香港",
            "云南","浙江","",
    };



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

    public static ArrayList<CityResBean> getCitysInfo(){
        return GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(AREA_INFO),new TypeToken<ArrayList<CityResBean>>(){}.getType());
    }

    public static void saveCitysInfo(ArrayList<CityResBean> list){
        SPUtil.getInstance().saveStr(AREA_INFO,GsonUtil.getInstance().toJson(list));
    }


    public static HashMap<String,CityResBean.ProvinceListBean> getProMap(){
        return GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(AREA_MAP_INFO),new TypeToken<HashMap<String,CityResBean.ProvinceListBean>>(){}.getType());
    }

    public static void saveProMap(HashMap<String,CityResBean.ProvinceListBean> map){
        SPUtil.getInstance().saveStr(AREA_MAP_INFO,GsonUtil.getInstance().toJson(map));
    }


    public static List<CityResBean.ProvinceListBean> getProlList(){
        return GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(AREA_LIST_INFO),new TypeToken<List<CityResBean.ProvinceListBean>>(){}.getType());
    }

    public static void saveProlList(List<CityResBean.ProvinceListBean> list){
        SPUtil.getInstance().saveStr(AREA_LIST_INFO,GsonUtil.getInstance().toJson(list));
    }

    public static void saveUserId(int id){
        SPUtil.getInstance().saveInt("hv_id",id);
    }

    public static int getUserId(){
        return SPUtil.getInstance().getInt("hv_id");
    }
}
