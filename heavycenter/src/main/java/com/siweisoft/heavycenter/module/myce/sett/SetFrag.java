package com.siweisoft.heavycenter.module.myce.sett;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutResBean;

import butterknife.OnClick;

public class SetFrag extends AppFrag<SetUIOpe,SetDAOpe> {


    @OnClick({R.id.item_exit})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.item_exit:
                getP().getD().logOut(new UINetAdapter<LogOutResBean>(activity) {
                    @Override
                    public void onResult(boolean success, String msg, LogOutResBean o) {
                        super.onResult(success, msg, o);
                        LocalValue.setAutoLogin(false);
                        getActivity().finish();
                    }
                });
                break;
        }
    }
}
