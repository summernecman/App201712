package com.siweisoft.service.ui.user.userlist;

//by summer on 17-09-06.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.netdb.user.UserBean;

public class UserListDAOpe extends BaseDAOpe {


    UserBean userBean;


    public void getUserListWithOutMe(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.User.getUserListWithOutMe(context,userBean, onFinishListener);
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
