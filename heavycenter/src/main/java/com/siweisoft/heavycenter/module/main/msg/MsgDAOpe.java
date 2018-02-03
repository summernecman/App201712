package com.siweisoft.heavycenter.module.main.msg;

//by summer on 2017-12-11.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.msg.sys.SysFrag;

import java.util.ArrayList;

public class MsgDAOpe extends BaseDAOpe {



    public MsgDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getPages(){
        ArrayList<Fragment> pages = new ArrayList<>();
        for(int i=0;i<MsgsReqBean.消息类型.size();i++){
            BaseUIFrag frag = new SysFrag();
            frag.setArguments(new Bundle());
            frag.getArguments().putString(ValueConstant.DATA_INDEX,MsgsReqBean.消息类型.get(i));
            frag.getArguments().putString(ValueConstant.DATA_MOUDLE, MainAct.消息);
            pages.add(frag);
        }
        return pages;
    }


}
