package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.ToastUtil;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.app.AppBean;

import java.io.File;

public class SettingDAOpe extends BaseDAOpe {



    AppBean appBean;


    public void CheckVersion(final Context context, final OnFinishListener onFinishListener){
        NetDataOpe.App.CheckVersion(context,new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                 appBean = (AppBean) o;
                if(appBean==null){
                    onFinishListener.onFinish(false);
                    return;
                }
                PackageManager pm = context.getPackageManager();//context为当前Activity上下文
                PackageInfo pi = null;
                try {
                    pi = pm.getPackageInfo(context.getPackageName(), 0);
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




    public String getVersion(Context context){
        PackageManager pm = context.getPackageManager();//context为当前Activity上下文
        PackageInfo pi  = null;
        try {
            pi = pm.getPackageInfo(context.getPackageName(), 0);
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
