package com.siweisoft.heavycenter.module.myce.unit.info;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindResBean;

import butterknife.OnClick;

public class InfoFrag extends AppFrag<InfoUIOpe,InfoDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getD().getInfo(new UINetAdapter<UnitInfo>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, UnitInfo o) {
                super.onResult(success, msg, o);
                getP().getU().initinfo(o);
            }
        });
    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
            getP().getU().showTip(new View.OnClickListener() {
                @Override
                public void onClick(View vv) {
                    switch (vv.getId()){
                        case R.id.tv_close:
                            break;
                        case R.id.tv_sure:
                            getP().getD().unBinUnit(new UINetAdapter<UnBindResBean>(getActivity()) {
                                @Override
                                public void onResult(boolean success, String msg, UnBindResBean o) {
                                    super.onResult(success, msg, o);
                                    if(success){
                                       // LocalValue.getLoginInfo().set
                                    }
                                }
                            });
                            break;
                    }
                }
            });
            break;
        }
    }
}
