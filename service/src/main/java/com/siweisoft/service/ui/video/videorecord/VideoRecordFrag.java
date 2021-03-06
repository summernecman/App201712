package com.siweisoft.service.ui.video.videorecord;

//by summer on 17-08-23.

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
import com.siweisoft.service.bean.ContactBean;
import com.siweisoft.service.bean.HistoryBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.data.netd.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.ui.video.seach.SeachBean;
import com.siweisoft.service.ui.video.seach.SeachFrag;
import com.siweisoft.service.ui.video.videocontainer.VideoContainerFrag;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.Optional;

public class VideoRecordFrag extends BaseServerFrag<VideoRecordUIOpe, VideoRecordDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener {

    @Override
    public void initNow() {
        super.initNow();
        getP().getU().initRefresh(this,this);
        setTitleBean(new TitleBean("返回", "录像", "", "搜索"));
        getP().getD().setHistoryBean((HistoryBean) getArguments().getSerializable(Value.DATA_DATA));
        ContactBean contactBean = new ContactBean();
        contactBean.setFromid(Value.getUserInfo().getId());
        contactBean.setPagesize(5);
        getP().getD().setPageindex(0);
        contactBean.setPagestart(getP().getD().getPageindex());
        contactBean.setToid(getP().getD().getHistoryBean().getUserBean().getId());
        getP().getD().getVideosByBothUserIdWithLimit(getBaseAct(),contactBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getVideos().clear();
                if (o != null) {
                    getP().getD().getVideos().addAll((ArrayList<VideoBean>) o);
                }
                getP().getU().initList(getP().getD().getVideos(), VideoRecordFrag.this, new MyRecyclerView.OnScroll() {
                    @Override
                    public void onScrollToEnd(MyRecyclerView myRecyclerView) {
                        initData2();
                    }
                });
                getP().getU().finishRefresh();
                getP().getD().setPageindex(getP().getD().getPageindex() + 1);
            }
        });

    }


    public void initData2() {
        super.initNow();
        ContactBean contactBean = new ContactBean();
        contactBean.setFromid(Value.getUserInfo().getId());
        contactBean.setPagesize(5);
        contactBean.setPagestart(getP().getD().getPageindex());
        contactBean.setToid(getP().getD().getHistoryBean().getUserBean().getId());
        getP().getD().getVideosByBothUserIdWithLimit(getBaseAct(),contactBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<VideoBean> a = (ArrayList<VideoBean>) o;
                if (a == null || a.size() == 0) {
                    ToastUtil.getInstance().showShort(getActivity(), "加载完毕");
                }
                if (o != null) {
                    getP().getD().getVideos().addAll((ArrayList<VideoBean>) o);
                }
                getP().getU().loadmore();
                getP().getD().setPageindex(getP().getD().getPageindex() + 1);
                getP().getU().finishLoadmore();
            }
        });

    }




    @Optional
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right2:
                final SeachFrag seachFrag = new SeachFrag();
                seachFrag.setArguments(new Bundle());
                seachFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getSeachBean());
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_ONE, seachFrag);
                seachFrag.setOnFinishListener(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTop(getActivity(), Value.ROOTID_ONE);
                        getP().getD().setSeachBean((SeachBean) o);
                        initNow();
                    }
                });
                break;
        }
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
                        FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_ONE, playFrag);
                        break;
                    case R.id.iv_head:
                        UserInfoFrag userInfoFrag = new UserInfoFrag();
                        userInfoFrag.setArguments(new Bundle());
                        userInfoFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getVideos().get((Integer) v.getTag(R.id.position)).getOtherUser());
                        FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_ONE, userInfoFrag);
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
