package com.siweisoft.heavycenter.module.main.msg;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.ope.BaseUIOpe;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.databinding.FragMainMsgBinding;

import java.util.ArrayList;

public class MsgUIOpe extends BaseUIOpe<FragMainMsgBinding>{

    public MsgUIOpe(Context context) {
        super(context);
    }

    public void initPages(Fragment fragment,ArrayList<Fragment> pages){
        bind.llCntent.setOffscreenPageLimit(pages.size());
        bind.llCntent.setAdapter(new AppBasePagerAdapter2(fragment.getChildFragmentManager(),context,pages));
        bind.topview.setViewPager(bind.llCntent);
    }



}
