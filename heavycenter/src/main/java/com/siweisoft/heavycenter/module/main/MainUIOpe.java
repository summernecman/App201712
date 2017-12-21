package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;

import com.android.lib.base.adapter.HomePageAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.ActMainBinding;
import com.siweisoft.heavycenter.module.myce.MyceFrag;

import java.util.ArrayList;

public class MainUIOpe extends AppUIOpe<ActMainBinding> {

    private boolean load = false;

    private int pos_content= 0;

    private int pos_drawer= 0;


    public MainUIOpe(Context context) {
        super(context);
        bind.incloud.leftDrawer.getLayoutParams().width = (int) (ScreenUtil.w *80/ 100);
        bind.incloud.leftDrawer.requestLayout();
    }



    public void setBottomMenuViewData(ArrayList<BottomMenuBean> data){
        bind.bottommenu.initItems(data);
        bind.bottommenu.setIndex(0);
    }


    public void initDrawerMenu(){
        pos_drawer = FragManager.getInstance().addId(bind.incloud.leftDrawer.getId());
        AppFrag appFrag = new MyceFrag();
        FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),pos_drawer,appFrag);
    }

    public void initPages(final ArrayList<BottomMenuBean> pages,OnAppItemSelectListener listener){
        bind.content.setOffscreenPageLimit(pages.size());
        bind.bottommenu.setViewPager(bind.content);
        bind.bottommenu.setOnAppItemClickListener(listener);
        ArrayList<Integer> ids = new ArrayList<>();
        final ArrayList<View> views = new ArrayList<>();
        for(int i=0;i<pages.size();i++){
            ids.add(pages.get(i).getContainerView().getId());
            views.add(pages.get(i).getContainerView());
        }
        FragManager.getInstance().initIds(ids);
        pos_content = FragManager.getInstance().addId(MainAct.ID_CONTENT);

        bind.content.setAdapter(new HomePageAdapter(context, views, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                if(!load){
                    for(int i=0;i<views.size();i++){
                        FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),i,pages.get(i).getFragment());
                    }
                    load = true;
                }
            }
        }));
    }

    public void setCurrentItem(int item){
        if(bind.content.getAdapter().getCount()<=item){
            return;
        }
        bind.content.setCurrentItem(item);
    }

    public void switchDrawer(){
        if(bind.drawerLayout.isDrawerOpen(Gravity.LEFT)){
            bind.drawerLayout.closeDrawer(Gravity.LEFT);
        }else{
            bind.drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    public int getPos_content() {
        return pos_content;
    }

    public int getPos_drawer() {
        return pos_drawer;
    }
}
