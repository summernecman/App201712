package com.android.lib.view.bottommenu;

import android.support.v4.app.Fragment;
import android.view.View;

public class BottomMenuBean {


    private String name;

    private int drawbleId;

    private Fragment fragment;

    private View containerView;

    public BottomMenuBean(String name, int drawbleId) {
        this.name = name;
        this.drawbleId = drawbleId;
    }

    public BottomMenuBean(String name, int drawbleId, Fragment fragment, View containerView) {
        this.name = name;
        this.drawbleId = drawbleId;
        this.fragment = fragment;
        this.containerView = containerView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawbleId() {
        return drawbleId;
    }

    public void setDrawbleId(int drawbleId) {
        this.drawbleId = drawbleId;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public View getContainerView() {
        return containerView;
    }

    public void setContainerView(View containerView) {
        this.containerView = containerView;
    }
}