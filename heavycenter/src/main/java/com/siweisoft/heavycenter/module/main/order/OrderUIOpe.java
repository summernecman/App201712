package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.data.netd.order.ordernum.OrderNumRes;
import com.siweisoft.heavycenter.databinding.FragMainOrderBinding;

import java.util.ArrayList;

public class OrderUIOpe extends BaseUIOpe<FragMainOrderBinding>{






    public void initPages(Fragment fragment, final ArrayList<Fragment> pages){
        bind.llCntent.setOffscreenPageLimit(pages.size());
        bind.llCntent.setAdapter(new AppBasePagerAdapter2(fragment.getChildFragmentManager(),context,pages));
        bind.topview.setViewPager(bind.llCntent);
        bind.scrollmenu.setViewPager(bind.llCntent);
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

    public void refreshTopMenu(OrderNumRes orderNumRes){
        if(orderNumRes==null){
            return;
        }
        bind.topview.setTxt(0, "新订单("+StringUtil.getStr(orderNumRes.getNewFhCount()+orderNumRes.getNewShCount())+")");
        bind.topview.setTxt(1, "进行中("+StringUtil.getStr(orderNumRes.getIngFhCount()+orderNumRes.getIngShCount())+")");
        bind.topview.setTxt(2, "已完成("+StringUtil.getStr(orderNumRes.getDoneFhCount()+orderNumRes.getDoneShCount())+")");
    }



}
