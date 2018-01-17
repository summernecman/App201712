package com.siweisoft.heavycenter.module.main.order.news.rule;

//by summer on 2018-01-17.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.netd.mana.good.specs.SpecsRes;
import com.siweisoft.heavycenter.data.netd.order.rule.Rule;

public class RuleFrag extends BaseUIFrag<RuleUIOpe,RuleDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getRuleRes(), RuleFrag.this);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.type:
                Rule rule  = (Rule) v.getTag(R.id.data);
                getArguments().putSerializable(ValueConstant.DATA_DATA2,rule);
                getBaseUIActivity().onBackPressed();
                break;
        }
    }
}
