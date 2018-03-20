package com.siweisoft.service.ui.user.unit;

//by summer on 17-09-11.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.netdb.user.UserBean;

public class UnitDAOpe extends BaseDAOpe {



    public void updateUnitInfo(UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.User.updateUnit(getActivity(),userBean, onFinishListener);
    }
}
