package com.siweisoft.heavycenter.module.main.weights.地磅模块;

//by summer on 2017-12-11.

import android.support.v4.app.Fragment;

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.view.viewpager.MyViewPager;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.databinding.FragMainWeigtsBinding;
import com.siweisoft.heavycenter.BR;

import java.util.ArrayList;

public class 地磅模块界面 extends AppUIOpe<FragMainWeigtsBinding> {


    public void initRefresh(){

    }

    public void init地磅仪表(BaseUIFrag fragment, final ArrayList<Fragment> pages){
        bind.viewpager.init(fragment, pages, new MyViewPager.OnPageSelected() {
            @Override
            public void onPageSelected(int position) {
                BaseUIFrag baseUIFrag = (BaseUIFrag) pages.get(position);
                baseUIFrag.onFristVisible();
            }
        });
        bind.topview.setViewPager(bind.viewpager);
    }



    public void showBottomView(boolean sel){
        if(sel){
            bind.bottomSel.getRoot().setVisibility(View.VISIBLE);
            bind.bottom.getRoot().setVisibility(View.GONE);
        }else{
            bind.bottomSel.getRoot().setVisibility(View.GONE);
            bind.bottom.getRoot().setVisibility(View.VISIBLE);
        }
    }


    public void initUI(WeightMsg weightMsg){
        bind.bottom.setVariable(BR.frag_main_weigts_bottom,weightMsg.getMessage());
    }
}
