package com.siweisoft.heavycenter.module.mana.carmana;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.module.mana.carmana.my.MyFrag;
import com.siweisoft.heavycenter.module.mana.carmana.receipt.ReceiptFrag;
import com.siweisoft.heavycenter.module.mana.carmana.send.SendFrag;

import java.util.ArrayList;

public class CarDAOpe extends AppDAOpe {
    public CarDAOpe(Context context) {
        super(context);
    }


    public ArrayList<Fragment> getPages(){
        ArrayList<Fragment> pages = new ArrayList<>();
        pages.add(new SendFrag());
        pages.add(new MyFrag());
        pages.add(new ReceiptFrag());
        return pages;
    }

}
