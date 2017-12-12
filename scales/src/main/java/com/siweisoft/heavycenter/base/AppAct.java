package com.siweisoft.heavycenter.base;

//by summer on 2017-12-08.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.jaeger.library.StatusBarUtil;
import com.siweisoft.heavycenter.R;

public class AppAct<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseUIActivity<A,B> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
