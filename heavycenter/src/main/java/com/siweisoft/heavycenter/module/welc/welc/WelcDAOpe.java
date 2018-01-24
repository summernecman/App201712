package com.siweisoft.heavycenter.module.welc.welc;

//by summer on 2017-12-14.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;
import com.siweisoft.heavycenter.data.netd.user.info.UserInfoReqBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WelcDAOpe extends AppDAOpe {

    public WelcDAOpe(Context context) {
        super(context);
    }

    public String getImageUrl(){
        return "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2137016978,933113517&fm=27&gp=0.jpg";
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

    public void getInfo(NetI<LoginResBean> adapter){
        UserInfoReqBean userInfoReqBean = new UserInfoReqBean();
        userInfoReqBean.setId(LocalValue.getLoginInfo().getUserId());
        userInfoReqBean.setIsApp(1);
        NetDataOpe.User.getInfo(getActivity(),userInfoReqBean,adapter);
    }
}
