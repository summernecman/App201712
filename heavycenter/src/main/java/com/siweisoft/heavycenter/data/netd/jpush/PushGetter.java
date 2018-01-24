package com.siweisoft.heavycenter.data.netd.jpush;

//by summer on 2017-12-25.

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.lib.util.ToastUtil;

import cn.jpush.android.api.JPushInterface;

public class PushGetter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtil.getInstance().showShort(context,intent.getAction());
        if(intent.getExtras()!=null){
            Bundle bundle = intent.getExtras();
            ToastUtil.getInstance().showShort(context,bundle.getString(JPushInterface.EXTRA_TITLE)+":"+bundle.getString(JPushInterface.EXTRA_MESSAGE)+":"+bundle.getString(JPushInterface.EXTRA_EXTRA));
        }
    }
}
