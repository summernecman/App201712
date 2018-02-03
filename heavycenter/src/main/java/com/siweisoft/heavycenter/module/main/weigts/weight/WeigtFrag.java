package com.siweisoft.heavycenter.module.main.weigts.weight;

//by summer on 2017-12-11.

import android.view.View;

import butterknife.OnClick;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;


public class WeigtFrag extends AppFrag<WeigtUIOpe,WeigtDAOpe> {

    @Override
    public void initNow() {
        super.initNow();
    }


    @OnClick({R.id.tv_weight,R.id.ll_mz,R.id.ll_pz,R.id.ll_kc})
    public void onClick(final View v) {
        String title = "";
        switch (v.getId()){
            case R.id.tv_weight:
              title ="校验";
                break;
            case R.id.ll_mz:
                title ="毛重校验";
                break;
            case R.id.ll_pz:
                title ="皮重校验";
                break;
            case R.id.ll_kc:
                title ="扣除校验";
                break;
        }

        getP().getU().showTip(title, new View.OnClickListener() {
            @Override
            public void onClick(View vv) {
                switch (vv.getId()){
                    case R.id.tv_sure:
                        double d  = (double) vv.getTag(R.id.data);
                        switch (v.getId()){
                            case R.id.tv_weight:
                                getP().getD().getWeightRes().setWeight(d);
                                break;
                            case R.id.ll_kc:
                                getP().getD().getWeightRes().setDeduct(d);
                                break;
                        }
                        break;
                    case R.id.tv_close:

                        break;
                }
                getP().getU().getFragManager2().finish(getBaseUIActivity(), MainAct.地磅, true);
            }
        });
    }
}
