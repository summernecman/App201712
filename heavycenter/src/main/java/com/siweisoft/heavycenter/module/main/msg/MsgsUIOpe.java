package com.siweisoft.heavycenter.module.main.msg;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.siweisoft.heavycenter.databinding.FragMainMsgsBinding;

import java.util.ArrayList;

public class MsgsUIOpe extends BaseUIOpe<FragMainMsgsBinding>{


    public void initPages(Fragment fragment, final ArrayList<Fragment> pages){
        bind.llCntent.setOffscreenPageLimit(pages.size());
        bind.llCntent.setAdapter(new AppBasePagerAdapter2(fragment.getChildFragmentManager(),context,pages));
        bind.topview.setViewPager(bind.llCntent);
        final BaseOnPagerChangeListener baseOnPagerChangeListener = new BaseOnPagerChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                LogUtil.E("fdfdfdfdf"+position);
                if(pages.get(position) instanceof BaseUIFrag){
                    BaseUIFrag baseUIFrag = (BaseUIFrag) pages.get(position);
                    baseUIFrag.onFristVisible();
                }
            }
        };
        bind.llCntent.addOnPageChangeListener(baseOnPagerChangeListener);
        bind.llCntent.post(new Runnable() {
            @Override
            public void run() {
                baseOnPagerChangeListener.onPageSelected(0);
            }
        });
    }



}
