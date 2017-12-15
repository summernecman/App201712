package com.siweisoft.heavycenter.module.acct.acct;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.module.acct.login.LoginFrag;
import com.siweisoft.heavycenter.module.acct.regist.RegistFrag;
import com.siweisoft.heavycenter.module.acct.repwd.RepwdFrag;

import java.util.ArrayList;

public class AcctDAOpe extends AppDAOpe {

    private ArrayList<Fragment> frags;

    public AcctDAOpe(Context context) {
        super(context);
        initPages();
    }

    private void initPages(){
        frags = new ArrayList<>();
        frags.add(new LoginFrag());
        frags.add(new RegistFrag());
        frags.add(new RepwdFrag());
    }

    public Fragment getShowFrag(){
        for(int i=0;i<frags.size();i++){
            if(!frags.get(i).isHidden()){
                return frags.get(i);
            }
        }
        return frags.get(0);
    }

    public ArrayList<Fragment> getFrags(){
        return frags;
    }

}
