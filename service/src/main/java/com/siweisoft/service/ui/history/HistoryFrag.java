package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.HistoryBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.video.videorecord.VideoRecordFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class HistoryFrag extends BaseServerFrag<HistoryUIOpe, HistoryDAOpe> implements ViewListener ,OnRefreshListener{

    @Override
    public void initdelay() {
        getP().getU().initRefresh(this);
    }

    @Override
    public void initNow() {
        super.initNow();
        setTitleBean(new TitleBean("", "呼叫历史", ""));
        getP().getD().getVideos(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initList((ArrayList<HistoryBean>) o, HistoryFrag.this);
                getP().getU().finishRefresh();
            }
        });
        if (Value.getUserInfo().getUsertype() != UserBean.USER_TYPE_CUSTOMER) {
//            getP().getD().getUnUploadVideoNum(new OnFinishListener() {
//                @Override
//                public void onFinish(Object o) {
//                    setTitleBean(new TitleBean("", "呼叫历史", StringUtil.getStr(o).equals("0") ? "" : StringUtil.getStr(o)));
//                }
//            });
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right2:

                break;
        }
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type) {
            case ViewListener.TYPE_ONCLICK:
                VideoRecordFrag videoRecordFrag = new VideoRecordFrag();
                videoRecordFrag.setArguments(new Bundle());
                videoRecordFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, (Serializable) v.getTag(R.id.data));
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_ONE, videoRecordFrag);
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        initNow();
    }
}
