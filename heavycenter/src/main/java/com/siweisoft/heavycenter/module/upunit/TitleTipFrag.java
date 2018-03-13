package com.siweisoft.heavycenter.module.upunit;

//by summer on 2017-12-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.R;
import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.view.OnAppItemClickListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.fragment.two.FragManager2;
import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;

import java.util.List;

public class TitleTipFrag extends BaseUIFrag<TitleTipUIOpe,TitleTipDAOpe> implements ViewListener{


    private List<String> strs;

    private OnAppItemClickListener onAppItemsClickListener;



    public void init(List<String> strs){
        this.strs =strs;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getP().getU().init(strs,this);
        view.findViewById(R.id.llll).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        ViewAnimator.animate(getP().getU().bind.recycle).duration(300).zoomOut().onStop(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                FragManager2.getInstance()
                        .setAnim(false)
                        .setHideLast(false)
                        .finish((BaseUIActivity) getActivity(),get容器(),true);
            }
        }).start();
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                int pos = (int) v.getTag(R.id.position);
                ViewAnimator.animate(getP().getU().bind.recycle).duration(300).zoomOut().onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        FragManager2.getInstance()
                                .setAnim(false)
                                .setHideLast(false)
                                .finish((BaseUIActivity) getActivity(),get容器(),true);
                    }
                }).start();
                if(onAppItemsClickListener!=null){
                    onAppItemsClickListener.onAppItemClick(v,pos);
                }
                break;
        }
    }

    public void setOnAppItemsClickListener(OnAppItemClickListener onAppItemsClickListener) {
        this.onAppItemsClickListener = onAppItemsClickListener;
    }
}
