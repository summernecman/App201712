package com.siweisoft.service;

//by summer on 2017-07-03.

import android.content.Context;
import android.content.Intent;

import com.android.lib.aplication.LibAplication;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.exception.exception.CrashHander;
import com.android.lib.service.main.AppService;
import com.android.lib.util.ImagePickerLoader;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.system.SystemUtil;
import com.hyphenate.chat.EMClient;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.NetValue;
import com.siweisoft.service.data.netd.crash.CrashBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.EMChatOpe;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.sms.SMSSDK;


public class ServieApp extends LibAplication implements OnFinishListener {

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_base_nurse, android.R.color.white);
                return new ClassicsHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_base_nurse, android.R.color.white);
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }



    @Override
    public void onCreate() {
        super.onCreate();
        if(!SystemUtil.isAppInit(this)){
            CrashHander.getInstance().init(this, this);
            SPUtil.getInstance().init(this);
            x.Ext.init(this);
            x.Ext.setDebug(false); //输出debug日志，开启会影响性能
            NetValue.setIsOffice(false);
            NetValue.保存域名到文件(this,NetValue.正式域名);
            LogUtil.E("onCreate"+System.currentTimeMillis());
            startService(new Intent(this, AppService.class));
            startService(new Intent(getBaseContext(), com.siweisoft.service.AppServer.class));

            new EMChatOpe().initEM(this);

            SMSSDK.getInstance().initSdk(this);
            SMSSDK.getInstance().setDebugMode(false);

            JPushInterface.init(this);
            JPushInterface.setDebugMode(false);
            LogUtil.CAN_LOGIN = true;
        }
    }

    /**
     * 初始化图片加载了imagepicker图片选择器
     */
    public void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new ImagePickerLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }


    @Override
    public void onFinish(Object o) {
        SPUtil.getInstance().saveBoolean(Value.autologin, false);
        if (Value.getRoom() != null) {
            EMClient.getInstance().chatroomManager().leaveChatRoom(Value.getRoom().getId());
            EMClient.getInstance().logout(true);
        }

        final CrashBean crashBean = new CrashBean();
        crashBean.setError((String) o);
        crashBean.setCreatedtime(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        crashBean.setUserBean(Value.getUserInfo());
        NetDataOpe.Crash.sendCrash(this,crashBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                exit();
            }
        });
    }
}
