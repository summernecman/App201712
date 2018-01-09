package com.siweisoft.heavycenter.module.acct.repwd;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.base.interf.OnLoadingInterf;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.acct.code.CodeResBean;

import butterknife.OnClick;

public class RepwdFrag extends AppFrag<RepwdUIOpe,RepwdDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initBg(getP().getD().getImageUrl());

    }


    @OnClick({R.id.regist,R.id.code})
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()){
            case R.id.regist:
                if(getP().getU().go()){
                    getP().getD().updatePwd(getP().getU().getforGetReqBean(), new UINetAdapter(activity) {
                        @Override
                        public void onNetWorkResult(boolean success, BaseResBean o) {
                            CodeResBean codeResBean = GsonUtil.getInstance().fromJson(o.getData().toString(),CodeResBean.class);
                            if(codeResBean.getCode().equals("200")){
                                ToastUtil.getInstance().showLong(getContext(),"跟换密码成功");
                                FragManager.getInstance().finish(getActivity().getSupportFragmentManager(),getIndex());
                            }
                        }
                    });
                }
                break;
            case R.id.code:
                getP().getD().getCode(getP().getU().getCodeReqBean());
                v.setEnabled(false);
                getP().getD().getThreadUtil().run(1000, new OnLoadingInterf() {
                    @Override
                    public Void onStarLoading(Object o) {
                        getP().getU().bind.code.setText((60 - (int) o) + "s");
                        if ((60 - (int) o) <= 0) {
                            getP().getU().bind.code.setEnabled(true);
                            getP().getD().getThreadUtil().stop();
                            getP().getU().bind.code.setText("重新获取验证码");
                        }
                        return null;
                    }

                    @Override
                    public Void onStopLoading(Object o) {
                        return null;
                    }
                });
                break;
        }
    }

}
