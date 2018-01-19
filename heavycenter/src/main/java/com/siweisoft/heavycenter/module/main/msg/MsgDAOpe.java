package com.siweisoft.heavycenter.module.main.msg;

//by summer on 2017-12-11.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.module.main.msg.sys.SysFrag;

import java.util.ArrayList;

public class MsgDAOpe extends BaseDAOpe {



    public MsgDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getPages(){
        ArrayList<Fragment> pages = new ArrayList<>();
        BaseUIFrag all = new SysFrag();all.setArguments(new Bundle());all.getArguments().putString(ValueConstant.DATA_POSITION,MsgsReqBean.MESSAGE_CATE_ALL);pages.add(all);
        BaseUIFrag trans = new SysFrag();trans.setArguments(new Bundle());trans.getArguments().putString(ValueConstant.DATA_POSITION,MsgsReqBean.MESSAGE_CATE_TRANS);pages.add(trans);
        BaseUIFrag car = new SysFrag();car.setArguments(new Bundle());car.getArguments().putString(ValueConstant.DATA_POSITION,MsgsReqBean.MESSAGE_CATE_CAR);pages.add(car);
        BaseUIFrag pubs = new SysFrag();pubs.setArguments(new Bundle());pubs.getArguments().putString(ValueConstant.DATA_POSITION,MsgsReqBean.MESSAGE_CATE_PUB);pages.add(pubs);
        BaseUIFrag sys = new SysFrag();sys.setArguments(new Bundle());sys.getArguments().putString(ValueConstant.DATA_POSITION,MsgsReqBean.MESSAGE_CATE_SYS);pages.add(sys);
        return pages;
    }


}
