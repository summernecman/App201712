package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.content.Intent;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.SPUtil;
import com.android.lib.util.ToastUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.R;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.setting.setting.tip.TipFrag;

import butterknife.OnClick;

public class SettingFrag extends BaseServerFrag<SettingUIOpe, SettingDAOpe> {

    @Override
    public void initdelay() {
        super.initdelay();
        setTitleBean(new TitleBean("返回", "设置", ""));
        getP().getU().setVersion(getP().getD().getVersion());
    }
    @OnClick({R.id.ll_set,R.id.ll_check,R.id.ll_clear})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_set:
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        SPUtil.getInstance().saveBoolean(Value.autologin,false);
                        ((ServieApp) activity.getApplication()).exit();
                    }

                    @Override
                    public void onError(int code, String error) {
                        SPUtil.getInstance().saveBoolean(Value.autologin,false);
                        ((ServieApp) activity.getApplication()).exit();
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }
                });
                break;
            case R.id.ll_check:
                getP().getD().CheckVersion(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        boolean up = (boolean) o;
                        if(up){
                            ToastUtil.getInstance().showLong(activity,"检测到新版本,开始下载");
                            Intent intent = new Intent(activity,DownloadService.class);
                            intent.putExtra("downUrl",getP().getD().appBean.getUrl());
                            activity.startService(intent);
                        }else{
                            ToastUtil.getInstance().showLong(activity,"已经是最新版本");
                        }
                    }
                });
                break;
            case R.id.ll_clear:
                TipFrag tipFrag = new TipFrag();
                FragmentUtil2.getInstance().add(activity, Value.FULLSCREEN, tipFrag);
                tipFrag.setOnFinishListener(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTopRightNow(activity, Value.FULLSCREEN);
                        switch (((View) o).getId()) {
                            case R.id.tv_yes:
                                getP().getD().clear(activity);
                                break;
                            case R.id.tv_no:
                                ToastUtil.getInstance().showLong(activity,"你可以到手机存储根目录  videorecord  文件夹下手动清理");
                                break;
                        }
                    }
                });
                break;
        }

    }


}
