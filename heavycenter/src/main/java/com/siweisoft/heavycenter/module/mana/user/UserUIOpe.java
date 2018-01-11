package com.siweisoft.heavycenter.module.mana.user;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.unit.user.UnitUserResBean;
import com.siweisoft.heavycenter.databinding.FragManaUserBinding;

import java.util.List;

public class UserUIOpe extends AppUIOpe<FragManaUserBinding> {

    public UserUIOpe(Context context) {
        super(context);
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<UnitUserResBean.ResultsBean> data) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_user, BR.item_mana_user, data));

    }

}
