package com.siweisoft.heavycenter.base;

//by summer on 2017-12-08.

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.android.lib.base.ope.BaseUIOpe;

public class AppUIOpe<A extends ViewDataBinding>  extends BaseUIOpe<A> {

    public AppUIOpe(){

    }

    public AppUIOpe(Context context) {
        super(context);
    }


}
