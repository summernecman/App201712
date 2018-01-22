package com.siweisoft.heavycenter.module.welc.welc;

//by summer on 2017-12-14.

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.android.lib.network.news.NetAdapter;
import com.android.lib.util.IntentUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.other.city.CityReqBean;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;

import java.util.ArrayList;

import butterknife.OnClick;

public class WelcAct extends AppAct<WelcUIOpe,WelcDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initBg(getP().getD().getImageUrl());


        NetDataOpe.getCity(activity,new CityReqBean(),new NetAdapter<ArrayList<CityResBean>>(activity){

            @Override
            public void onResult(boolean success, String msg, final ArrayList<CityResBean> o) {
                super.onResult(success, msg, o);
                if(success){
                   new AsyncTask<String, String, String>() {
                       @Override
                       protected String doInBackground(String... strings) {
                           LocalValue.saveCitysInfo(o);
                           getP().getD().saveProMapInfo();
                           getP().getD().initDATA();
                           return null;
                       }

                       @Override
                       protected void onPostExecute(String s) {
                           super.onPostExecute(s);
                           IntentUtil.startActivityWithFinish(WelcAct.this, AcctAct.class,null);
                       }
                   }.execute();
                }
            }
        });
    }

    @OnClick({R.id.image})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.image:

                break;
        }
    }
}
