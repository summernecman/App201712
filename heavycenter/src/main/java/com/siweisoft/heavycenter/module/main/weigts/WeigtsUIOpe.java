package com.siweisoft.heavycenter.module.main.weigts;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
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

    public void initUI(WeightMsg weightMsg){
        bind.tvContent.setText(StringUtil.getStr(weightMsg.getMessage().getContent()));
        bind.tvGoodname.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getProductName()));
        bind.tvSpes.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getSpecification()));
        bind.tvComp.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getShdwName()));
        if(LocalValue.get登录返回信息().getAbbreviationName().equals(weightMsg.getMessage().getOrder().getFhdwName())){
            bind.tvComp.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getShdwName()));
            bind.tvType.setText("发往");
        }else{
            bind.tvComp.setText(StringUtil.getStr(weightMsg.getMessage().getOrder().getFhdwName()));
            bind.tvType.setText("来自");
        }



    }
}
