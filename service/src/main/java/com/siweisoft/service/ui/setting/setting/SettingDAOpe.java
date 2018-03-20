package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.ToastUtil;
import com.siweisoft.service.netdb.app.AppBean;
import com.siweisoft.service.netdb.app.AppI;
import com.siweisoft.service.netdb.app.AppOpe;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;

import java.io.File;

public class SettingDAOpe extends BaseDAOpe {

    UserI userI;

    AppI appI;

    AppBean appBean;

    public SettingDAOpe() {
        userI = new UserNetOpe();
    }

    public void CheckVersion(final OnFinishListener onFinishListener){
        getAppI().CheckVersion(new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                 appBean = (AppBean) o;
                if(appBean==null){
                    onFinishListener.onFinish(false);
                    return;
                }
                PackageManager pm = getActivity().getPackageManager();//context为当前Activity上下文
                PackageInfo pi = null;
                try {
                    pi = pm.getPackageInfo(getActivity().getPackageName(), 0);
                    if(pi.versionCode<appBean.getVersioncode()){
                        onFinishListener.onFinish(true);
                        return;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                    onFinishListener.onFinish(false);
                    return;
                }
                onFinishListener.onFinish(false);
            }
        });
    }


    public AppI getAppI() {
        if(appI==null){
            appI =  new AppOpe();
        }
        return appI;
    }


    public String getVersion(){
        PackageManager pm = getActivity().getPackageManager();//context为当前Activity上下文
        PackageInfo pi  = null;
        try {
            pi = pm.getPackageInfo(getActivity().getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void clear(Context context){
        File file = new File(Environment.getExternalStorageDirectory(),"videorecord");
        if(file.exists()){
            File[] files = file.listFiles();
            for(int i=0;files!=null && i<files.length;i++){
                files[i].delete();
            }
            ToastUtil.getInstance().showLong(context,"清除成功");
            return;
        }
        ToastUtil.getInstance().showLong(context,"清除失败");
    }
}
