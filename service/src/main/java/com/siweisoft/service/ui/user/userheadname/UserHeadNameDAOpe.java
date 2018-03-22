package com.siweisoft.service.ui.user.userheadname;

//by summer on 17-08-30.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;

public class UserHeadNameDAOpe extends BaseDAOpe {



    public void setHead(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        userBean.setPhone(Value.getUserInfo().getPhone());
        NetDataOpe.User.setHeadUrl(context,userBean, onFinishListener);
    }
}
