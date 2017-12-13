package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.LogUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.siweisoft.heavycenter.R;

import butterknife.OnClick;

public class OrderFrag extends BaseUIFrag<OrderUIOpe,OrderDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getData());
    }

    @OnClick({R.id.ftv_right})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ftv_right:
                IntentIntegrator.forSupportFragment(OrderFrag.this).initiateScan();
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
