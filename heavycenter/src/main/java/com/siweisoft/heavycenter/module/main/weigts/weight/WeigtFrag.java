package com.siweisoft.heavycenter.module.main.weigts.weight;

//by summer on 2017-12-11.

import android.view.View;

import butterknife.OnClick;

import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;

import java.text.DecimalFormat;


public class WeigtFrag extends AppFrag<WeigtUIOpe,WeigtDAOpe> {


    @Override
    public void initdelay() {
        super.initdelay();
        getP().getU().init(getP().getD().getWeightMsg());
    }


    public void refresh(){
        if(getP()!=null&&getP().getU()!=null){
            getP().getU().init(getP().getD().getWeightMsg());
        }
    }


    @OnClick({R.id.tv_weight,R.id.tv_mz,R.id.tv_pz,R.id.tv_kc})
    public void onClick(final View v) {
        String title = "";
        switch (v.getId()){
            case R.id.tv_weight:
              title ="校验";
                break;
            case R.id.tv_mz:
                title ="毛重校验";
                break;
            case R.id.tv_pz:
                title ="皮重校验";
                break;
            case R.id.tv_kc:
                title ="扣除校验";
                break;
        }

        getP().getU().showTip(title,getP().getD().getWeightMsg(), new View.OnClickListener() {
            @Override
            public void onClick(View vv) {
                switch (vv.getId()){
                    case R.id.tv_sure:
                        double d  = (double) vv.getTag(R.id.data);
                        switch (v.getId()){
                            case R.id.tv_weight:
                                DecimalFormat df = new DecimalFormat("#.#");
                                getP().getU().bind.tvWeight.setText(StringUtil.getStr(Double.parseDouble(df.format(d))));
                                getP().getD().getWeightMsg().getMessage().setWeigh(d);
                                break;
                            case R.id.tv_kc:
                                getP().getD().getWeightMsg().getMessage().setKc(d);
                                break;
                        }
                        break;
                    case R.id.tv_close:

                        break;
                }
                getP().getU().getFragManager2().finish(getBaseUIAct(), get容器(), true);
            }
        });
    }
}
