package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Intent;
import android.view.View;

import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.siweisoft.heavycenter.R;
import com.android.lib.view.dialog.list.DialogListFrag;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.order.news.NewOrderFrag;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class OrderFrag extends AppFrag<OrderUIOpe,OrderDAOpe> {


    @Override
    public void lazyInit() {
        getP().getU().initPages(fragment,getP().getD().getPages());

    }

    @OnClick({R.id.ftv_right,R.id.ftv_title,R.id.ftv_right2})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)activity).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right:
                IntentIntegrator.forSupportFragment(OrderFrag.this).initiateScan();
                break;
            case R.id.ftv_title:
                List<String> strs = new ArrayList<>();
                for(int i=0;i<10;i++){
                    strs.add("fdfdsfsd"+i);
                }
                DialogListFrag frag = new DialogListFrag();
                frag.init(strs);
                FragmentUtil2.getInstance().addNoAnim(activity,R.id.act_main,frag);
                break;
            case R.id.ftv_right2:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.订单,MainAct.订单ID,new NewOrderFrag());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            LogUtil.E(result.getContents());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
