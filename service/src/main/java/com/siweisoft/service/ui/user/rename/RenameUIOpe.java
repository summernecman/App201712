package com.siweisoft.service.ui.user.rename;

//by summer on 17-08-30.

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragRenameBinding;
import com.siweisoft.service.data.netd.user.UserBean;

public class RenameUIOpe extends BaseUIOpe<FragRenameBinding> {



    public void initInfo(UserBean userBean) {
        bind.setUsername(userBean);
    }
}
