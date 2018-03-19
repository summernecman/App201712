package com.siweisoft.heavycenter.module.main.msg;

//by summer on 2017-12-11.

import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.adapter.AppBasePagerAdapter2;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.BaseOnPagerChangeListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.databinding.FragMainMsgsBinding;

import java.util.ArrayList;
import java.util.List;

public class MsgsUIOpe extends AppUIOpe<FragMainMsgsBinding> {


    @Override
    public void initUI() {
        super.initUI();
        initTitle(R.drawable.icon_hv_person,"消息",0,R.drawable.icon_hv_scan);
        ArrayList<String> strs = new ArrayList<>();
        for(int i = 0; i< MsgsReqBean.get消息类型().size(); i++){
            strs.add(MsgsReqBean.get消息类型().get(i).getName());
        }
        bind.topview.initTxtView(strs);
        final List<LoginResBean.BranchCompanyListBean> coms = LocalValue.get下级单位列表();
        if(coms!=null&&coms.size()>0){
            titleView.getMidTV().setText(StringUtil.getStr(coms.get(0).getAbbreviationName()));
            titleView.getMidIconIV().setImageResource(R.drawable.arrow_down);
            titleView.getMidIconIV().setVisibility(View.VISIBLE);
        }
    }

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
