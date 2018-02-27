package com.siweisoft.heavycenter.module.main.weigts;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.databinding.FragMainWeigtsBinding;

import java.util.ArrayList;

public class WeigtsUIOpe extends AppUIOpe<FragMainWeigtsBinding> {


    public void initRefresh(){
    }

    public void initPages(Fragment fragment, final ArrayList<Fragment> pages){
        bind.viewpager.setOffscreenPageLimit(pages.size());
        bind.viewpager.setAdapter(new AppBasePagerAdapter2(fragment.getChildFragmentManager(),context,pages));
        bind.topview.setViewPager(bind.viewpager);
        bind.scrollmenu.setViewPager(bind.viewpager);
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
        bind.viewpager.addOnPageChangeListener(baseOnPagerChangeListener);
        bind.viewpager.post(new Runnable() {
            @Override
            public void run() {
                baseOnPagerChangeListener.onPageSelected(0);
            }
        });
    }

    public void initUI(WeightMsg weightMsg){
        bind.bottom.tvContent.setText(StringUtil.getStr(weightMsg.getMessage().getContent()));
        bind.bottom.tvGoodname.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getProductName()));
        bind.bottom.tvSpes.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getSpecification()));
        bind.bottom.tvComp.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getShdwName()));
        if(LocalValue.get登录返回信息().getAbbreviationName().equals(weightMsg.getMessage().getOrder().getFhdwName())){
            bind.bottom.tvComp.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getShdwName()));
            bind.bottom.tvType.setText("发往");
        }else{
            bind.bottom.tvComp.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getFhdwName()));
            bind.bottom.tvType.setText("来自");
        }



    }
}
