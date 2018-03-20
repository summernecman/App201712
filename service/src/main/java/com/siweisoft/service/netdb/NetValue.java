package com.siweisoft.service.netdb;

//by summer on 2017-12-28.

import android.content.Context;

import com.android.lib.constant.UrlConstant;
import com.android.lib.util.NullUtil;
import com.android.lib.util.SPUtil;

public class NetValue extends UrlConstant {

    protected static final String 奔溃日志 = "http://www.summernecro.com:8079/server/crash/sendCrash";

    protected static final String HTTP前缀 = "http://";

    protected static  String 项目名 = "/sx";

    public static String 正式域名 = "sx.siweisoft.cn:8079";

    public static String 测试域名 = "192.168.20.58:8079";

    protected static String 域名 = 测试域名;


    protected static String 文件路径 = "/files";

    protected static boolean isOffice = true;

    public static String 获取地址(String module){
        return  HTTP前缀+域名+项目名+module;
    }


    public static String 获取文件路径(String module){
        return  HTTP前缀+域名+文件路径+module;
    }


    public static void 保存域名到文件(Context context,String 域名){
        SPUtil.getInstance().init(context).saveStr("域名", 域名);
        NetValue.域名= 域名;
    }

    public static String 获取域名从文件(Context context){
        String 域名 = SPUtil.getInstance().init(context).getStr("域名");
        if(NullUtil.isStrEmpty(域名)){
            域名 = NetValue.域名;
        }
        NetValue.域名= 域名;
        return 域名;
    }

}
