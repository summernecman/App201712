package com.siweisoft.service.ui.user.userlist;

//by summer on 17-09-06.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.view.refreshlayout.MaterialRefreshLayout;
import com.android.lib.view.refreshlayout.MaterialRefreshListener;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class UserListFrag extends BaseServerFrag<UserListUIOpe, UserListDAOpe> implements ViewListener {

    OnFinishListener onFinishListener;

    @Override
    public void initdelay() {
        // getP().getD().setUserBean((UserBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initRefresh(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                initNow();
                materialRefreshLayout.finishRefresh();
            }
        });
    }

    @Override
    public void initNow() {
        super.initNow();
        setTitleBean(new TitleBean("返回", "联系人", ""));
        getP().getD().getUserListWithOutMe(getBaseAct(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initList((ArrayList<UserBean>) o, UserListFrag.this);
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        getP().getD().setUserBean((UserBean) v.getTag(R.id.data));
        FragmentUtil2.getInstance().removeTop(getActivity(), Value.getNowRoot());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (onFinishListener != null) {
            onFinishListener.onFinish(getP().getD().getUserBean());
        }
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }
}
