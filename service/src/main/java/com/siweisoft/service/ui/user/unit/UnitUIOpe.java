package com.siweisoft.service.ui.user.unit;

//by summer on 17-09-11.

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.StringUtil;
import com.siweisoft.service.databinding.FragRenameBinding;
import com.siweisoft.service.data.netd.unit.UnitBean;

public class UnitUIOpe extends BaseUIOpe<FragRenameBinding> {



    public void initUnit(UnitBean unitBean) {
        if (unitBean == null) {
            return;
        }
        bind.etInput.setText(StringUtil.getStr(unitBean.getUnitname()));
    }
}
