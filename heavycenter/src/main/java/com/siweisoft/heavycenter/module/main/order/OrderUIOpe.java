package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import com.siweisoft.heavycenter.module.test.SharedElementFragment1;

import java.util.ArrayList;

public class OrderUIOpe extends BaseUIOpe<FragMainOrderBinding>{






    public void initPages(Fragment fragment, final ArrayList<Fragment> pages){
        bind.llCntent.setOffscreenPageLimit(pages.size());

        final ArrayList<RelativeLayout> relativeLayouts = new ArrayList<>();
        for(int i=0;i<pages.size();i++){
            RelativeLayout relativeLayout = new RelativeLayout(getActivity());
            relativeLayout.setId(14345+i);
            relativeLayouts.add(relativeLayout);
//            SharedElementFragment1 sharedElementFragment1 = (SharedElementFragment1) pages.get(i);
//            sharedElementFragment1.iid = relativeLayout.getId();
        }

        bind.llCntent.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pages.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(relativeLayouts.get(position));
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(relativeLayouts.get(position).getId(),pages.get(position));
                transaction.commit();
                LogUtil.E("instantiateItem"+position);
                return relativeLayouts.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                LogUtil.E("destroyItem"+position);
                container.removeView(relativeLayouts.get(position));
            }
        });

        //bind.llCntent.setAdapter(new AppBasePagerAdapter2(fragment.getChildFragmentManager(),context,pages));
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
