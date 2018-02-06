package com.siweisoft.heavycenter.module.main.order.news.rule;

//by summer on 2018-01-17.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.good.specs.SpecsReq;
import com.siweisoft.heavycenter.data.netd.mana.good.specs.SpecsRes;
import com.siweisoft.heavycenter.data.netd.order.rule.Rule;
import com.siweisoft.heavycenter.data.netd.order.rule.RuleRes;

import java.util.ArrayList;

public class RuleDAOpe extends BaseDAOpe {

    RuleRes ruleRes = new RuleRes();


    public RuleDAOpe() {
        initRule();
    }



    public void initRule(){
        if(ruleRes.getData()==null){
            ruleRes .setData(new ArrayList<Rule>());
        }
        ruleRes.getData().clear();
        ruleRes.getData().add(new Rule("zjSign","收货单位直接签收"));
        ruleRes.getData().add(new Rule("jsyQr","需驾驶员确认"));
        ruleRes.getData().add(new Rule("fhdwQr","需发货单位确认"));
    }

    public RuleRes getRuleRes() {
        return ruleRes;
    }
}
