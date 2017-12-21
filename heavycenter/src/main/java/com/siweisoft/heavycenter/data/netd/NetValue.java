package com.siweisoft.heavycenter.data.netd;

//by summer on 2017-12-21.

public class NetValue {

    public static final String 奔溃日志 = "http://www.summernecro.com:8079/server/crash/sendCrash";

    public static String 正式域名 = "192.168.1.205:8080";

    public static String 测试域名 = "192.168.1.205:8080";

    public static final String HTTP前缀 = "http://";

    public static final String 项目名 = "/zhongxin";

    public static final String 文件路径 = "";


    public static String 获取正式地址(String module){
        return  HTTP前缀+正式域名+项目名+module;
    }


    public static String 获取测试地址(String module){
        return  HTTP前缀+测试域名+项目名+module;
    }
}
