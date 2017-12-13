package com.siweisoft.heavycenter.data.netd;

//by summer on 2017-12-08.

import com.android.lib.constant.UrlConstant;

public class NetApi extends UrlConstant{

    public static final String 测试接口 = "/user/getOutCallTimeDistribution";

    public static final String 奔溃日志 = "http://www.summernecro.com:8079/server/crash/sendCrash";


    public static String getURL(String module){
        return  HTTP前缀+域名+项目名+module;
    }
}
