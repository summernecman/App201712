package com.android.lib.network.news;

import com.android.lib.network.bean.res.BaseResBean;

import java.util.ArrayList;

public interface NetArrayI<T> {

    boolean onNetStart(String url, String gson);

    void onNetFinish(boolean haveData, String url, BaseResBean baseResBean);

    void onResult(boolean success, String msg, ArrayList<T> o);

    void onProgress(long total, long current);


}