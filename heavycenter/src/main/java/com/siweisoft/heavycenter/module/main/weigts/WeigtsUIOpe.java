package com.siweisoft.heavycenter.module.main.weigts;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMainWeigtsBinding;

import java.util.ArrayList;

public class WeigtsUIOpe extends AppUIOpe<FragMainWeigtsBinding> {

    public WeigtsUIOpe(Context context) {
        super(context);
    }

    public void initRefresh(){
    }

    public void initPages(Fragment fragment, ArrayList<Fragment> pages){
        bind.viewpager.setOffscreenPageLimit(pages.size());
        bind.viewpager.setAdapter(new AppBasePagerAdapter2(fragment.getChildFragmentManager(),context,pages));
        bind.topview.setViewPager(bind.viewpager);
        bind.scrollmenu.setViewPager(bind.viewpager);
    }
}
