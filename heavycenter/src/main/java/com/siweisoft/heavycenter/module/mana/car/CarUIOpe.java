package com.siweisoft.heavycenter.module.mana.car;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.util.LogUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragManaCarBinding;

import java.util.ArrayList;

public class CarUIOpe extends AppUIOpe<FragManaCarBinding> {



    public void initPages(Fragment fragment, ArrayList<Fragment> pages){
        if(!fragment.isAdded()){
           return;
        }
        bind.llCntent.setOffscreenPageLimit(pages.size());
        bind.llCntent.setAdapter(new AppBasePagerAdapter2(fragment.getChildFragmentManager(),context,pages));
        bind.topview.setViewPager(bind.llCntent);
        bind.scrollmenu.setViewPager(bind.llCntent);
    }



}
