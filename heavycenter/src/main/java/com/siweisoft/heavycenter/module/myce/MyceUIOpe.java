package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.content.Context;
import android.view.View;

import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMyceBinding;

public class MyceUIOpe extends AppUIOpe<FragMyceBinding> {

    public MyceUIOpe(Context context) {
        super(context);
    }


    public void hideOrShowManageFunction(boolean show){
        int vis = View.VISIBLE;
        if(show){
            vis = View.VISIBLE;
        }else{
            vis = View.GONE;
        }
        bind.itemCar.setVisibility(vis);
        bind.itemGood.setVisibility(vis);
        bind.itemStore.setVisibility(vis);
        bind.itemUser.setVisibility(vis);
    }


}
