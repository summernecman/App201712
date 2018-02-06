package com.android.lib.view.dialog.list;

//by summer on 2017-12-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.R;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.interf.view.OnAppItemClickListener;
import com.android.lib.base.listener.ViewListener;

import java.util.List;

public class DialogListFrag extends BaseUIFrag<DialogListUIOpe,DialogListDAOpe> implements ViewListener{


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
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                int pos = (int) v.getTag(R.id.position);
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
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
