package com.siweisoft.heavycenter.module.myce.name;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.acct.rename.ReNameResBean;
import com.siweisoft.heavycenter.module.main.MainAct;

import butterknife.OnClick;

public class NameFrag extends AppFrag<NameUIOpe,NameDAOpe> {

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                getP().getD().reName(getP().getU().getReNameReqBean(), new UINetAdapter<ReNameResBean>(getActivity()) {
                    @Override
                    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                        stopLoading();
                        if("200".equals(baseResBean.getCode())){
                            LoginResBean loginResBean = LocalValue.getLoginInfo();
                            loginResBean.setTrueName(getP().getU().getReNameReqBean().getTrueName());
                            LocalValue.saveLoginInfo(loginResBean);
                            ToastUtil.getInstance().showLong(getActivity(),baseResBean.getMessage());
                            ((MainAct)getActivity()).getP().getD().getMyceFrag().getP().getU().initUI(null);
                        }
                       getBaseUIActivity().onBackPressed();
                    }
                });
                break;
        }
    }

}
