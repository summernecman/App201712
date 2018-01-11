package com.siweisoft.heavycenter.module.acct.login;

//by summer on 2017-12-14.

import android.content.Context;

import com.android.lib.network.netadapter.UINetAdapter;
import com.android.lib.network.news.NetArrayI;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginReqBean;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.acct.regist.RegistReqBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityReqBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginDAOpe extends AppDAOpe {


    public LoginDAOpe(Context context) {
        super(context);
    }

    public String getImageUrl(){
        return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1513325040026&di=9e408824bb71605801a3e73997457851&imgtype=0&src=http%3A%2F%2Fbbs.static.coloros.com%2Fdata%2Fattachment%2Fforum%2F201503%2F06%2F183706dti1utuig1rqa13y.jpg";
    }

    public void login(LoginReqBean reqBean, NetI<LoginResBean> adapter){
        NetDataOpe.onLogin(getActivity(), NetValue.获取地址("/user/appLogin"),reqBean,adapter);
    }

    public void getCity(CityReqBean reqBean, NetArrayI<CityResBean> adapter){
        NetDataOpe.getCity(getActivity(),reqBean,adapter);
    }

    public void saveProMapInfo(){
        ArrayList<CityResBean> cityResBeans =  LocalValue.getCitysInfo();
        HashMap<String,CityResBean.ProvinceListBean> map = new HashMap<>();
        for(int i=0;i<cityResBeans.size();i++){
            for(int j=0;j<cityResBeans.get(i).getProvinceList().size();j++){
                map.put(cityResBeans.get(i).getProvinceList().get(j).getValue(),cityResBeans.get(i).getProvinceList().get(j));
            }
        }
        LocalValue.saveProMap(map);
    }

    public void initDATA(){
        List<CityResBean.ProvinceListBean> pro = new ArrayList<>();

        HashMap<String,CityResBean.ProvinceListBean> map = LocalValue.getProMap();
        for(int i=0;i<LocalValue.pros.length;i++){
            CityResBean.ProvinceListBean provinceListBean = map.get(LocalValue.pros[i]);
            if(provinceListBean==null){
                provinceListBean = new CityResBean.ProvinceListBean();
                provinceListBean.setValue("");
            }
            pro.add(provinceListBean);
        }
        LocalValue.saveProlList(pro);
    }
}
