package com.android.lib.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import com.android.lib.util.LogUtil;

/**
 * fragment布局基类
 */
public class BaseFrg extends Fragment {

    /**
     * fragment对应的activity的引用
     */
    protected FragmentActivity activity;
    /**
     * fragment自身的引用
     */
    protected Fragment fragment;

    private long uniqueid;

    private boolean isAttach = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uniqueid = System.currentTimeMillis();
        fragment = this;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            activity = (FragmentActivity) context;
        }
        isAttach = true;
    }

    public long getUniqueid() {
        return uniqueid;
    }

    public boolean isAttach() {
        return isAttach;
    }
}
