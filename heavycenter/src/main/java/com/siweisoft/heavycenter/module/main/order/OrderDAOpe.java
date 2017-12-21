package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.heavycenter.module.main.msg.all.AllFrag;
import com.siweisoft.heavycenter.module.main.msg.car.CarFrag;
import com.siweisoft.heavycenter.module.main.msg.publics.PubFrag;
import com.siweisoft.heavycenter.module.main.msg.sys.SysFrag;
import com.siweisoft.heavycenter.module.main.msg.trans.TransFrag;
import com.siweisoft.heavycenter.module.main.order.begin.BeginFrag;
import com.siweisoft.heavycenter.module.main.order.doing.DoingFrag;
import com.siweisoft.heavycenter.module.main.order.done.DoneFrag;

import java.util.ArrayList;

public class OrderDAOpe extends BaseDAOpe {

    public OrderDAOpe(Context context) {
        super(context);
    }


    public ArrayList<Fragment> getPages(){
        ArrayList<Fragment> pages = new ArrayList<>();
        pages.add(new BeginFrag());
        pages.add(new DoingFrag());
        pages.add(new DoneFrag());
        return pages;
    }

}
