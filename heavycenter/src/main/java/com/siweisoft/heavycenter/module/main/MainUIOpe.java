package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;

import com.android.lib.base.adapter.HomePageAdapter;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ScreenUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.ActMainBinding;
import com.siweisoft.heavycenter.module.myce.MyceFrag;
import com.siweisoft.heavycenter.module.myce.unit.nobind.NoBindFrag;

import java.util.ArrayList;

public class MainUIOpe extends AppUIOpe<ActMainBinding> {

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
        FragManager2.getInstance().setAnim(false).start(getActivity(),MainAct.个人中心,bind.incloud.leftDrawer.getId(),myceFrag);
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
            boolean load = false;
            @Override
            public void onFinish(Object o) {
                if(!load){
                  for(int i=0;i<pages.size();i++){
                      FragManager2.getInstance().setAnim(false).start(getActivity(),pages.get(i).getName(),pages.get(i).getContainerView().getId(),pages.get(i).getFragment());
                      getActivity().setMoudle(pages.get(i).getName());
                  }
                    load = true;
                }
            }
        }));
    }

    public void nobind(){
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(NoBindFrag.class.getName());
        if(fragment!=null){
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.show(fragment);
            transaction.commitNow();
        }else{
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.add(MainAct.主界面ID,new NoBindFrag(),NoBindFrag.class.getName());
            transaction.commitNow();
        }
    }


    public void hideshowunbind(boolean show){
        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(NoBindFrag.class.getName());
        if(fragment==null){
            return;
        }
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if(show){
            transaction.show(fragment);
        }else{
            transaction.hide(fragment);
        }
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
}
