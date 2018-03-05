package com.siweisoft.heavycenter.module.upunit;

//by summer on 2017-12-18.

import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.BR;
import com.android.lib.R;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.databinding.FragDialogListBinding;

import java.util.List;

public class TitleTipUIOpe extends BaseUIOpe<FragDialogListBinding> {


    public void init(List<String> strs, ViewListener listener){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
        bind.recycle.setAdapter(new AppsDataBindingAdapter(getActivity(), R.layout.item_txt, BR.item_txt,strs,listener));
    }
}
