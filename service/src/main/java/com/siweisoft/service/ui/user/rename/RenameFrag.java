package com.siweisoft.service.ui.user.rename;

//by summer on 17-08-30.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;

import butterknife.OnClick;
import butterknife.Optional;

public class RenameFrag extends BaseServerFrag<RenameUIOpe, RenameDAOpe> {

    @Override
    public void initdelay() {
        setTitleBean(new TitleBean("返回", "重命名", ""));
        getP().getU().initInfo(getP().getD().getUserBean());
    }
    @Optional
    @OnClick({R.id.button})
    public void onClickEvent(View v) {
        switch (v.getId()) {
            case R.id.button:
                NetDataOpe.User.setName(getActivity(),getP().getD().getUserBean(), new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        UserBean bean = (UserBean) o;
                        Value.getUserInfo().setName(bean.getName());
                        FragmentUtil2.getInstance().removeTopRightNow(getActivity(), Value.getNowRoot());
                    }
                });
                break;
        }
    }
}
