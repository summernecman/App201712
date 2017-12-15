package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.ActMainBinding;

import java.util.ArrayList;

public class MainUIOpe extends AppUIOpe<ActMainBinding> {


    public MainUIOpe(Context context) {
        super(context);
//        bind.incloud.leftDrawer.getLayoutParams().width = (int) (ScreenUtil.w *80/ 100);
//        bind.incloud.leftDrawer.requestLayout();
    }



    public void setBottomMenuViewData(ArrayList<BottomMenuBean> data){
        bind.bottommenu.initItems(data);
        bind.bottommenu.setIndex(0);
    }


    public void initPages(FragmentActivity activity, final ArrayList<Fragment> pages,OnAppItemSelectListener listener){
        bind.content.setOffscreenPageLimit(pages.size());
        bind.content.setAdapter(new AppBasePagerAdapter2(activity.getSupportFragmentManager(),context,pages));
//        for(int i=0;i<pages.size();i++){
//            FragmentUtil2.getInstance().addNoAnim(activity, R.id.content,pages.get(i));
//        }
        bind.bottommenu.setViewPager(bind.content);
        bind.bottommenu.setOnAppItemClickListener(listener);
    }

    public void setCurrentItem(ArrayList<Fragment> pages,int item){
        if(bind.content.getAdapter().getCount()<=item){
            return;
        }
        bind.content.setCurrentItem(item);
//        FragmentUtil2.getInstance().showAndHidden(getActivity(),pages,item);
    }

    public void switchDrawer(){
//        if(bind.drawerLayout.isDrawerOpen(Gravity.LEFT)){
//            bind.drawerLayout.closeDrawer(Gravity.LEFT);
//        }else{
//            bind.drawerLayout.openDrawer(Gravity.LEFT);
//        }

    }
}
