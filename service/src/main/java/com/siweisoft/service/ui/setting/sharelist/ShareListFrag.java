package com.siweisoft.service.ui.setting.sharelist;

//by summer on 17-08-28.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.view.recyclerview.MyRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.data.netd.share.ShareBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.data.netd.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;
import com.siweisoft.service.ui.video.videocontainer.VideoContainerFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class ShareListFrag extends BaseServerFrag<ShareListUIOpe, ShareListDAOpe> implements ViewListener ,OnRefreshListener,OnLoadmoreListener {

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getU().initRefresh(this,this);

    }

    @Override
    public void initNow() {
        super.initNow();
        setTitleBean(new TitleBean("返回", "分享", ""));
        ShareBean shareBean = new ShareBean();
        shareBean.setReceiptid(Value.getUserInfo().getId());
        getP().getD().setPagestart(0);
        getP().getD().setPagesize(5);
        shareBean.setPagesize(getP().getD().getPagesize());
        shareBean.setPagestart(getP().getD().getPagestart());
        getP().getD().getSharesByReceipt(getBaseAct(),shareBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getD().getVideos().clear();
                if (o != null) {
                    getP().getD().getVideos().addAll((ArrayList<VideoBean>) o);
                }
                getP().getU().initList(getP().getD().getVideos(), ShareListFrag.this, new MyRecyclerView.OnScroll() {
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
        ShareBean shareBean = new ShareBean();
        shareBean.setReceiptid(Value.getUserInfo().getId());
        shareBean.setPagesize(getP().getD().getPagesize());
        shareBean.setPagestart(getP().getD().getPagestart());
        getP().getD().getSharesByReceipt(getBaseAct(),shareBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                if (o != null) {
                    getP().getD().getVideos().addAll((ArrayList<VideoBean>) o);
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
                    default:
                        UserInfoFrag userInfoFrag = new UserInfoFrag();
                        userInfoFrag.setArguments(new Bundle());
                        UserBean userBean = new UserBean();
                        userBean.setPhone(getP().getD().getVideos().get((Integer) v.getTag(R.id.position)).getOthername());
                        userInfoFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, userBean);
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
