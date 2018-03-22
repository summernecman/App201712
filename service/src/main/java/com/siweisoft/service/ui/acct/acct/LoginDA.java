package com.siweisoft.service.ui.acct.acct;

//by summer on 2018-03-22.

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.system.PermissionUtil;
import com.siweisoft.service.data.locd.PermissionsDA;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDA extends BaseDAOpe {

    private PermissionsDA permissionsDA = new PermissionsDA();

    private PermissionUtil permissionUtil = new PermissionUtil();

}
