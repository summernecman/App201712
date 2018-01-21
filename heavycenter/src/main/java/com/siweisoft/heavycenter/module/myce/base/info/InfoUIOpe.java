package com.siweisoft.heavycenter.module.myce.base.info;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.base.fragment.BaseUIFrag;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.databinding.FragMyceSetAboutBinding;
import com.siweisoft.heavycenter.databinding.FragScanInfoBinding;

public class InfoUIOpe extends AppUIOpe<FragScanInfoBinding>{


    public InfoUIOpe(Context context) {
        super(context);
    }

    @Override
    public void initUI(BaseUIFrag baseUIFrag) {
        super.initUI(baseUIFrag);
        bind.setVariable(BR.frag_scan_info, LocalValue.getLoginInfo());
    }
}
