package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.interf.view.OnAppItemClickListener;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ScreenUtil;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.jaeger.library.StatusBarUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainUIOpe extends AppUIOpe<ActMainBinding> {


    public MainUIOpe(Context context) {
        super(context);
        bind.incloud.leftDrawer.getLayoutParams().width = (int) (ScreenUtil.w *80/ 100);
        bind.incloud.leftDrawer.requestLayout();
//        StatusBarUtil.setColorForDrawerLayout(getActivity(),bind.drawerLayout, getActivity().getColor(R.color.red));
        StatusBarUtil.setTranslucentForDrawerLayout(getActivity(),bind.drawerLayout,0);
    }



    public void setBottomMenuViewData(ArrayList<BottomMenuBean> data, OnAppItemSelectListener listener){
        bind.bottommenu.initItems(data);
        bind.bottommenu.setOnAppItemClickListener(listener);
    }

    public void initPages(FragmentActivity activity, final ArrayList<Fragment> pages){
//        bind.content.setOffs?(new AppBasePagerAdapter2(activity.getSupportFragmentManager(),context,pages));
        for(int i=0;i<pages.size();i++){
            FragmentUtil2.getInstance().add(activity, R.id.content,pages.get(i));
        }
    }

    public void setCurrentItem(ArrayList<Fragment> pages,int item){
//        if(bind.content.getAdapter().getCount()<=item){
//            return;
//        }
//        bind.content.setCurrentItem(item);
        FragmentUtil2.getInstance().showAndHidden(getActivity(),pages,item);
    }
}
