package com.android.lib.network.news;

import com.android.lib.bean.BaseBean;
import com.android.lib.network.bean.res.BaseResBean;

public interface NetI<T extends Object> {

    boolean onNetStart(String url, String gson);

    void onNetFinish(boolean haveData, String url, BaseResBean baseResBean);

    void onResult(boolean success, String msg, T o);

    void onProgress(long total, long current);


}