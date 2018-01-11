package com.siweisoft.heavycenter.module.main.msg;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;
import com.siweisoft.heavycenter.module.main.msg.all.AllFrag;
import com.siweisoft.heavycenter.module.main.msg.car.CarFrag;
import com.siweisoft.heavycenter.module.main.msg.publics.PubFrag;
import com.siweisoft.heavycenter.module.main.msg.sys.SysFrag;
import com.siweisoft.heavycenter.module.main.msg.trans.TransFrag;

import java.util.ArrayList;

public class MsgDAOpe extends BaseDAOpe {

    public MsgDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getPages(){
        ArrayList<Fragment> pages = new ArrayList<>();
        pages.add(new AllFrag());
        pages.add(new TransFrag());
        pages.add(new CarFrag());
        pages.add(new PubFrag());
        pages.add(new SysFrag());
        return pages;
    }


}
