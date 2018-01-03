package com.siweisoft.heavycenter.module.mana.car;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.module.mana.car.my.MyFrag;
import com.siweisoft.heavycenter.module.mana.car.receipt.ReceiptFrag;
import com.siweisoft.heavycenter.module.mana.car.send.SendFrag;

import java.util.ArrayList;

public class CarDAOpe extends AppDAOpe {
    public CarDAOpe(Context context) {
        super(context);
    }


    public ArrayList<Fragment> getPages(int index){
        ArrayList<Fragment> pages = new ArrayList<>();
        SendFrag sendFrag = new SendFrag(); sendFrag.setIndex(index);pages.add(sendFrag);
        MyFrag myFrag = new MyFrag(); myFrag.setIndex(index);pages.add(myFrag);
        ReceiptFrag receiptFrag = new ReceiptFrag(); receiptFrag.setIndex(index);pages.add(receiptFrag);
        return pages;
    }

}
