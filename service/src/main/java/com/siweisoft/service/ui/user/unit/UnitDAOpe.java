package com.siweisoft.service.ui.user.unit;

//by summer on 17-09-11.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.user.UserBean;

public class UnitDAOpe extends BaseDAOpe {



    public void updateUnitInfo(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.User.updateUnit(context,userBean, onFinishListener);
    }
}
