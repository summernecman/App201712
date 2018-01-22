package com.siweisoft.heavycenter.module.acct.regist;

//by summer on 2017-12-14.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnLoadingInterf;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.code.CodeResBean;
import com.siweisoft.heavycenter.data.netd.acct.regist.RegistResBean;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;
import com.siweisoft.heavycenter.module.acct.role.RoleFrag;

import org.jetbrains.annotations.Nullable;

import butterknife.OnClick;
import butterknife.Optional;

public class RegistFrag extends AppFrag<RegistUIOpe,RegistDAOpe> {

    @Override
    public void initData() {
        super.initData();

    }

    @Optional
    @OnClick({R.id.regist})
    public void onClick(final View v){
        super.onClick(v);
        switch (v.getId()){
            case R.id.regist:
                if(getP().getU().go()){
                    getP().getD().regist(getP().getU().getRegistReqBean(), new com.android.lib.network.news.UINetAdapter<RegistResBean>(getContext()) {
                        @Override
                        public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                            if(baseResBean.getCode().equals("200")){
                                ToastUtil.getInstance().showLong(getContext(),"注册成功");
                                LocalValue.saveLoginReq(getP().getU().getLoginReqBean());
                                getBaseUIActivity().onBackPressed();
                                Bundle bundle = new Bundle();bundle.putBoolean("regist",true);
                                FragManager2.getInstance().start((BaseUIActivity) activity, AcctAct.账号,AcctAct.账号ID,new RoleFrag(),bundle);
                            }else{
                                ToastUtil.getInstance().showLong(getContext(),baseResBean.getMessage());
                            }
                            stopLoading();
                        }
                    });
                }
                break;
            case R.id.tv_code:
                if(getP().getU().canGetCode()){
                    getP().getD().getCode(getP().getU().getCodeReqBean());
                    getP().getD().getThreadUtil().run(this,v,60,1000, new OnLoadingInterf() {
                        @Override
                        public Void onStarLoading(Object o) {
                            getP().getU().bind.code.getCodeText().setText((60 - (int) o) + "s");
                            if ((60 - (int) o) <= 0) {
                                getP().getU().bind.code.setEnabled(true);
                                getP().getD().getThreadUtil().stop();
                                getP().getU().bind.code.getCodeText().setText("重新获取验证码");
                            }
                            return null;
                        }

                        @Override
                        public Void onStopLoading(Object o) {
                            return null;
                        }
                    });
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getP().getD().getThreadUtil().stop();
    }
}
