package com.android.lib.network.news;

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.bean.BaseBean;
import com.android.lib.bean.FilesBean;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.interf.OnNetWorkReqInterf;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.http.cookie.DbCookieStore;
import org.xutils.x;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by ${viwmox} on 2016-04-26.
 */
public class NetGet {

    private NetGet() {

    }

    public static void postData(final Context context, final String url, final BaseBean reqBean, final NetI netI) {
        LogUtil.E("input-->" + url);
        final String jsonstr = GsonUtil.getInstance().toJson(reqBean);
        LogUtil.E("input-->" + jsonstr);
        if (!netI.onNetStart(url, jsonstr)) {
            BaseResBean res = new BaseResBean();
            res.setErrorCode(ValueConstant.ERROR_CODE_NET_NO_CONNETCT);
            res.setErrorMessage(ValueConstant.ERROR_STR_NET_NO_CONNETCT);
            // res.setData(jsonstr);
            netI.onNetFinish(false, url, res);
            return;
        }

        RequestParams requestParams = new RequestParams(url);
        requestParams.setUseCookie(true);
        requestParams.setHeader("Cookie", SPUtil.getInstance().getStr(ValueConstant.cookieFromResponse));
        Map<String, String> map = GsonUtil.getInstance().
                fromJson(jsonstr,
                        new TypeToken<Map<String, String>>() {
                        }.getType());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            requestParams.addBodyParameter(entry.getKey(), entry.getValue());
        }

        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String response) {
                LogUtil.E("output-->" + response);
                if (response == null) {
                    BaseResBean res = new BaseResBean();
                    res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
                    res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
                    netI.onNetFinish(false, url, res);
                } else {
                    BaseResBean baseResBean = GsonUtil.getInstance().fromJson(response,BaseResBean.class);
                    netI.onNetFinish(true, url, baseResBean);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                BaseResBean baseResBean = new BaseResBean();
                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
                baseResBean.setErrorMessage(ex.getMessage() == null ? "" : ex.getMessage());
                baseResBean.setException(true);
                netI.onNetFinish(false, url, baseResBean);
                LogUtil.E(ex == null ? "Throwable" : "Throwable-->" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.E("onCancelled-->" + cex);
            }

            @Override
            public void onFinished() {
                LogUtil.E("onFinished-->");
            }
        });
    }



    public static void getData(final Context context, final String url, final BaseBean reqBean, final NetI netI) {
        LogUtil.E("input-->" + url);
        final String jsonstr = GsonUtil.getInstance().toJson(reqBean);
        LogUtil.E("input-->" + jsonstr);
        if (!netI.onNetStart(url, jsonstr)) {
            BaseResBean res = new BaseResBean();
            res.setErrorCode(ValueConstant.ERROR_CODE_NET_NO_CONNETCT);
            res.setErrorMessage(ValueConstant.ERROR_STR_NET_NO_CONNETCT);
            // res.setData(jsonstr);
            netI.onNetFinish(false, url, res);
            return;
        }

        RequestParams requestParams = new RequestParams(url);
        requestParams.setUseCookie(true);
        requestParams.setHeader("Cookie", SPUtil.getInstance().getStr(ValueConstant.cookieFromResponse));
        Map<String, String> map = GsonUtil.getInstance().
                fromJson(jsonstr,
                        new TypeToken<Map<String, String>>() {
                        }.getType());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            requestParams.addBodyParameter(entry.getKey(), entry.getValue());
        }


        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String response) {
                LogUtil.E("output-->" + response);
                if (response == null) {
                    BaseResBean res = new BaseResBean();
                    res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
                    res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
                    netI.onNetFinish(false, url, res);
                } else {
                    BaseResBean baseResBean = GsonUtil.getInstance().fromJson(response,BaseResBean.class);
                    LogUtil.E(baseResBean.getResult());
                    netI.onNetFinish(true, url, baseResBean);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                BaseResBean baseResBean = new BaseResBean();
                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
                baseResBean.setErrorMessage(ex.getMessage() == null ? "" : ex.getMessage());
                baseResBean.setException(true);
                netI.onNetFinish(false, url, baseResBean);
                LogUtil.E(ex == null ? "Throwable" : "Throwable-->" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.E("onCancelled-->" + cex);
            }

            @Override
            public void onFinished() {
                LogUtil.E("onFinished-->");
            }
        });
    }

    public static void getData(final Context context, final String url, final BaseBean reqBean, final NetArrayI netI) {
        LogUtil.E("input-->" + url);
        final String jsonstr = GsonUtil.getInstance().toJson(reqBean);
        LogUtil.E("input-->" + jsonstr);
        if (!netI.onNetStart(url, jsonstr)) {
            BaseResBean res = new BaseResBean();
            res.setErrorCode(ValueConstant.ERROR_CODE_NET_NO_CONNETCT);
            res.setErrorMessage(ValueConstant.ERROR_STR_NET_NO_CONNETCT);
            // res.setData(jsonstr);
            netI.onNetFinish(false, url, res);
            return;
        }

        RequestParams requestParams = new RequestParams(url);
        requestParams.setUseCookie(true);
        requestParams.setHeader("Cookie", SPUtil.getInstance().getStr(ValueConstant.cookieFromResponse));
        Map<String, String> map = GsonUtil.getInstance().
                fromJson(jsonstr,
                        new TypeToken<Map<String, String>>() {
                        }.getType());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            requestParams.addBodyParameter(entry.getKey(), entry.getValue());
        }


        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String response) {
                LogUtil.E("output-->" + response);
                if (response == null) {
                    BaseResBean res = new BaseResBean();
                    res.setErrorCode(ValueConstant.ERROR_CODE_RES_NULL);
                    res.setErrorMessage(ValueConstant.ERROR_STR_RES_NULL);
                    netI.onNetFinish(false, url, res);
                } else {
                    BaseResBean baseResBean = GsonUtil.getInstance().fromJson(response,BaseResBean.class);
                    LogUtil.E(baseResBean.getResult());
                    netI.onNetFinish(true, url, baseResBean);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                BaseResBean baseResBean = new BaseResBean();
                baseResBean.setErrorCode(ValueConstant.ERROR_CODE_VOLLEY_FAIL);
                baseResBean.setErrorMessage(ex.getMessage() == null ? "" : ex.getMessage());
                baseResBean.setException(true);
                netI.onNetFinish(false, url, baseResBean);
                LogUtil.E(ex == null ? "Throwable" : "Throwable-->" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.E("onCancelled-->" + cex);
            }

            @Override
            public void onFinished() {
                LogUtil.E("onFinished-->");
            }
        });
    }

}
