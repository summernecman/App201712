package com.android.lib.view.dialog.center;

//by summer on 2017-12-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.R;
import com.android.lib.base.fragment.BaseUIFrag;

public class DiaLogCenterFrag extends BaseUIFrag<DialogCenterUIOpe,DialogCenterDAOpe> {

    private View v;

    public void setV(View v) {
        this.v = v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewGroup group = (ViewGroup) view.findViewById(R.id.rl_lll);
        if(v!=null){
            group.addView(v,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        group.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
