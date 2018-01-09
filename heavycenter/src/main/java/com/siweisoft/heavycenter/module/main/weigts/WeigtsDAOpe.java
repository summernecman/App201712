package com.siweisoft.heavycenter.module.main.weigts;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.module.main.weigts.weight.WeigtFrag;

import java.util.ArrayList;

public class WeigtsDAOpe extends AppDAOpe {

    public WeigtsDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getPages(){
        ArrayList<Fragment> pages = new ArrayList<>();
        for(int i=0;i<5;i++){
            pages.add(new WeigtFrag());
        }
        return pages;
    }
}
