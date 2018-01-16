package com.siweisoft.heavycenter.module.mana.store.news;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.add.NewStoreResBean;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                if(getP().getU().canGo()){
                    getP().getD().newStore(getP().getU().getNewStoreReqBean(getP().getD().getNewStoreReqBean()), new UINetAdapter<NewStoreResBean>(getActivity()) {
                        @Override
                        public void onResult(boolean success, String msg, NewStoreResBean o) {
                            super.onResult(success, msg, o);
                            if(success){
                                getBaseUIActivity().onBackPressed();
                            }
                        }
                    });
                }
                break;
        }
    }
}
