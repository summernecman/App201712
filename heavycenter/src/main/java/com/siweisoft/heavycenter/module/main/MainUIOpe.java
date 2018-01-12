package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;

import com.android.lib.base.adapter.HomePageAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.ActMainBinding;
import com.siweisoft.heavycenter.module.myce.MyceFrag;
import com.siweisoft.heavycenter.module.myce.unit.nobind.NoBindFrag;

import java.util.ArrayList;
import java.util.List;

public class MainUIOpe extends AppUIOpe<ActMainBinding> {

    private boolean load = false;

    private int pos_content= 0;

    private int pos_drawer= -1;


    public MainUIOpe(Context context) {
        super(context);
        bind.incloud.leftDrawer.getLayoutParams().width = (int) (ScreenUtil.w *80/ 100);
        bind.incloud.leftDrawer.requestLayout();
    }



    public void setBottomMenuViewData(ArrayList<BottomMenuBean> data){
        bind.bottommenu.initItems(data);
        bind.bottommenu.setIndex(0);
    }


    public void initDrawerMenu(MyceFrag myceFrag ){


//        @SuppressLint("RestrictedApi") List<Fragment> fragmentList = getActivity().getSupportFragmentManager().getFragments();
//        for(int i=0;fragmentList!=null && i<fragmentList.size();i++){
//            if(fragmentList.get(i) instanceof  MyceFrag){
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//                transaction.remove(fragmentList.get(i));
//                transaction.commitAllowingStateLoss();
//            }
//        }

        pos_drawer = FragManager.getInstance().addId(bind.incloud.leftDrawer.getId());
        FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),pos_drawer,myceFrag);
    }

    public void initPages(final ArrayList<BottomMenuBean> pages,OnAppItemSelectListener listener){
        bind.content.setOffscreenPageLimit(pages.size());
        bind.bottommenu.setViewPager(bind.content);
        bind.bottommenu.setOnAppItemClickListener(listener);
        final ArrayList<View> views = new ArrayList<>();
        for(int i=0;i<pages.size();i++){
            views.add(pages.get(i).getContainerView());
        }
        bind.content.setAdapter(new HomePageAdapter(context, views, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                if(!load){
                    for(int i=0;i<views.size();i++){
                        FragManager2.getInstance().start(getActivity(),views.get(i).getId(),pages.get(i).getFragment(),null,i);
                    }
                    load = true;
                }
            }
        }));
    }

    public void nobind(){
        boolean have = false;
        @SuppressLint("RestrictedApi") List<Fragment> fragmentList = getActivity().getSupportFragmentManager().getFragments();
        for(int i=0;fragmentList!=null && i<fragmentList.size();i++){
            if(fragmentList.get(i) instanceof  NoBindFrag){
                have = true;
                break;
            }
        }


        if(!have){
            NoBindFrag noBindFrag = new NoBindFrag();
            FragManager.getInstance().add(getActivity(),MainAct.ID_CONTENT,noBindFrag);
        }
    }

    public void removenobind(){
        @SuppressLint("RestrictedApi") List<Fragment> fragmentList = getActivity().getSupportFragmentManager().getFragments();
        for(int i=0;fragmentList!=null && i<fragmentList.size();i++){
            if(fragmentList.get(i) instanceof  NoBindFrag){
                FragManager.getInstance().removeJustInNormal(getActivity(),fragmentList.get(i));
                break;
            }
        }
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
