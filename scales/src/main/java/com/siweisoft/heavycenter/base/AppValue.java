package com.siweisoft.heavycenter.base;

//by summer on 2017-12-08.

import com.android.lib.constant.UrlConstant;

public class AppValue extends UrlConstant {

    public static String DOMAIN = "www.summernecro.com:8079";
    public static String HTTP = "http://";
    public static String PROJECT = "/server";

    public static String FILE = "";

    public static String getURL(String module){
        return  HTTP+DOMAIN+PROJECT+module;
    }
}
