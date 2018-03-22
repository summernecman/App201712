package com.siweisoft.service.ui.setting.collect;

//by summer on 17-08-28.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.data.netd.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.ui.video.videocontainer.VideoContainerFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class CollecFrag extends BaseServerFrag<CollectUIOpe, CollectDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener {

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getU().initRefresh(this,this);

    }

    @Override
    public void initNow() {
        super.initNow();
        setTitleBean(new TitleBean("返回", "收藏", ""));
        getP().getD().setPagestart(0);
        getP().getD().setPagesize(5);
        Value.getUserInfo().setPagesize(getP().getD().getPagesize());
        Value.getUserInfo().setPagestart(getP().getD().getPagestart());
        getP().getD().getCollection(getBaseAct(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getVideos().clear();
                if (o != null) {
                    getP().getD().getVideos().addAll((ArrayList<VideoBean>) o);
                }
                getP().getU().initList(getP().getD().getVideos(), CollecFrag.this, new MyRecyclerView.OnScroll() {
                    @Override
                    public void onScrollToEnd(MyRecyclerView myRecyclerView) {
                        initData2();
                    }
                });
                getP().getD().setPagestart(getP().getD().getPagestart() + 1);
                getP().getU().finishRefresh();
            }
        });
    }


    public void initData2() {
        Value.getUserInfo().setPagesize(getP().getD().getPagesize());
        Value.getUserInfo().setPagestart(getP().getD().getPagestart());
        getP().getD().getCollection(getBaseAct(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<VideoBean> a = (ArrayList<VideoBean>) o;
                if (a == null || a.size() == 0) {
                    ToastUtil.getInstance().showShort(getActivity(), "加载完毕");
                }
                if (a != null) {
                    getP().getD().getVideos().addAll(a);
                }
                getP().getU().loadMore();
                getP().getD().setPagestart(getP().getD().getPagestart() + 1);
                getP().getU().finishLoadmore();
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()) {
                    case R.id.play:
                        VideoContainerFrag playFrag = new VideoContainerFrag();
                        playFrag.setArguments(new Bundle());
                        playFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, (Serializable) v.getTag(R.id.data));
                        FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, playFrag);
                        break;
                    case R.id.iv_head:
                        UserInfoFrag userInfoFrag = new UserInfoFrag();
                        userInfoFrag.setArguments(new Bundle());
                        userInfoFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getVideos().get((Integer) v.getTag(R.id.position)).getOtherUser());
                        FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, userInfoFrag);
                        break;
                }
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        initData2();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        initNow();
    }
}
