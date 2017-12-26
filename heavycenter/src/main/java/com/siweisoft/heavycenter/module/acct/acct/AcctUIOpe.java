package com.siweisoft.heavycenter.module.acct.acct;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.ActAcctBinding;

import java.util.ArrayList;

public class AcctUIOpe extends AppUIOpe<ActAcctBinding> {

    public AcctUIOpe(Context context) {
        super(context);
    }


    public void initPages(FragmentActivity activity,ArrayList<Fragment> pages){
        FragManager.getInstance().addsNoAnim(activity, R.id.content_acct,pages);
        FragManager.getInstance().showAndHidden(activity,pages,0);
    }

    public void showAndHidden(FragmentActivity activity,ArrayList<Fragment> pages,Class c){
        for(int i=0;i<pages.size();i++){
            if(c.getName().equals(pages.get(i).getClass().getName())){
                FragManager.getInstance().showAndHiddenWithAnim(activity,pages,i);
                return;
            }
        }

    }

}
