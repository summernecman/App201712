package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ScreenUtil;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainUIOpe extends AppUIOpe<ActMainBinding> {


    public MainUIOpe(Context context) {
        super(context);
        bind.incloud.leftDrawer.getLayoutParams().width = (int) (ScreenUtil.w *80/ 100);
        bind.incloud.leftDrawer.requestLayout();
    }

    public void setBottomMenuViewData(ArrayList<BottomMenuBean> data){
        bind.bottommenu.initItems(data);
    }

    public void initPages(FragmentActivity activity,ArrayList<Fragment> pages){
        for(int i=0;i<pages.size();i++){
            FragmentUtil2.getInstance().add(activity, R.id.content,pages.get(i));
        }
    }
}
