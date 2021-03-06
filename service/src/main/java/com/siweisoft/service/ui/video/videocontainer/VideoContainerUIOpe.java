package com.siweisoft.service.ui.video.videocontainer;

//by summer on 2017-11-08.

import android.support.v4.app.Fragment;
import android.view.View;

import com.android.lib.base.activity.BaseActivity;
import com.android.lib.base.adapter.AppBasePagerAdapter;
import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragVideocontainerBinding;
import com.siweisoft.service.data.netd.video.VideoBean;

import java.util.ArrayList;

public class VideoContainerUIOpe extends BaseUIOpe<FragVideocontainerBinding> {



    public void initViewPager(Fragment f, final ArrayList<Fragment> fragments) {
        bind.vpVp.setOffscreenPageLimit(fragments.size());
        BaseActivity activity = (BaseActivity) context;
        bind.vpVp.setAdapter(new AppBasePagerAdapter(f.getChildFragmentManager(), context,fragments) );
    }

    public void initClick(View.OnClickListener listener) {
        bind.ivCollect.setOnClickListener(listener);
        bind.ivShare.setOnClickListener(listener);
        bind.ftvRight2.setOnClickListener(listener);
    }

    public void switchFragment() {
        bind.vpVp.setCurrentItem(1 - bind.vpVp.getCurrentItem(), true);
    }

    public void initSwitchIcon(VideoBean videoBean) {
        if (videoBean.getVideodetail().size() > 0) {
            bind.ftvRight2.setVisibility(View.VISIBLE);
        } else {
            bind.ftvRight2.setVisibility(View.GONE);
        }
    }

    public void setCollect(boolean collect) {
        bind.ivCollect.setSelected(collect);
    }

}
