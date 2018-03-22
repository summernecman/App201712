package com.siweisoft.service.ui.user.rename;

//by summer on 17-08-30.

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;

public class RenameDAOpe extends BaseDAOpe {

    UserBean userBean;



    public RenameDAOpe() {
        userBean = new UserBean();
        userBean.setPhone(Value.getUserInfo().getPhone());
        userBean.setName(Value.getUserInfo().getName());
    }

    public UserBean getUserBean() {
        return userBean;
    }


}
