package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.heavycenter.module.main.order.begin.BeginFrag;
import com.siweisoft.heavycenter.module.main.order.doing.DoingFrag;
import com.siweisoft.heavycenter.module.main.order.done.DoneFrag;

import java.util.ArrayList;

public class OrderDAOpe extends BaseDAOpe {

    public OrderDAOpe(Context context) {
        super(context);
    }


    public ArrayList<Fragment> getPages(int index){
        ArrayList<Fragment> pages = new ArrayList<>();
        BeginFrag beginFrag = new BeginFrag();beginFrag.setIndex(index); pages.add(beginFrag);
        DoingFrag doingFrag = new DoingFrag(); doingFrag.setIndex(index); pages.add(doingFrag);
        DoneFrag doneFrag = new DoneFrag(); doneFrag.setIndex(index); pages.add(doneFrag);
        return pages;
    }

}
