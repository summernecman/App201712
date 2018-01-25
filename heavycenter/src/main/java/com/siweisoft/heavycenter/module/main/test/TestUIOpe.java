package com.siweisoft.heavycenter.module.main.test;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;

import com.android.lib.base.adapter.HomePageAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.ActMainBinding;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.myce.MyceFrag;
import com.siweisoft.heavycenter.module.myce.unit.nobind.NoBindFrag;

import java.util.ArrayList;

public class TestUIOpe extends AppUIOpe<ActMainBinding> {

    private boolean load = false;

    private int pos_content= 0;

    private int pos_drawer= -1;


    public TestUIOpe(Context context) {
        super(context);
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

//        pos_drawer = FragManager.getInstance().addId(bind.incloud.leftDrawer.getId());
//        FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),pos_drawer,myceFrag);

        FragManager2.getInstance().setAnim(false).start(getActivity(),MainAct.个人中心,bind.incloud.leftDrawer.getId(),myceFrag);

//        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(bind.incloud.leftDrawer.getId(),myceFrag);
//        fragmentTransaction.commitNow();


    }

    public void initPages(final ArrayList<BottomMenuBean> pages,OnAppItemSelectListener listener){
        FragManager2.getInstance().clear();
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

                  for(int i=0;i<pages.size();i++){
                      FragManager2.getInstance().setAnim(false).start(getActivity(),pages.get(i).getName(),pages.get(i).getContainerView().getId(),pages.get(i).getFragment());
                  }
                    load = true;
                }
            }
        }));
    }

    public void nobind(){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.add(MainAct.主界面ID,new NoBindFrag(),NoBindFrag.class.getName());
        transaction.commitNow();
    }

    public void removenobind(){
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(NoBindFrag.class.getName());
        if(fragment==null){
            return;
        }
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(fragment);
        transaction.commitNow();
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