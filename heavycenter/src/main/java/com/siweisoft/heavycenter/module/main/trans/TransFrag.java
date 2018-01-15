package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.trans.detail.TransDetailFrag;
import com.siweisoft.heavycenter.module.main.trans.search.SearchFrag;

import butterknife.OnClick;

public class TransFrag extends AppFrag<TransUIOpe,TransDAOpe> implements ViewListener,OnRefreshListener {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this);
        getP().getU().initRecycle();
        //getP().getU().LoadListData(getP().getD().getData(),this);
        getP().getU().LoadListData(getP().getD().getData(),this);
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.E("setUserVisibleHint:"+isVisibleToUser);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),new TransDetailFrag());
                break;
        }
    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)getActivity()).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right2:
                getP().getU().search(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        if((boolean) (o)){
                            getP().getD().setSearchFrag(new SearchFrag());
                            FragManager.getInstance().addJustInNormal(activity,R.id.rl_trans,getP().getD().getSearchFrag(),R.anim.anim_push_up_in, R.anim.anim_push_up_out);
                        }else{
                            if(getP().getD().getSearchFrag()!=null){
                                FragManager.getInstance().removeJustInNormal(activity,getP().getD().getSearchFrag(),R.anim.anim_push_up_in, R.anim.anim_push_up_out);
                            }
                        }
                    }
                });

                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(2000);
        getP().getU().LoadListData(getP().getD().getData(),this);
    }
}
