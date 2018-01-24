package com.siweisoft.heavycenter.module.scan;

import android.content.Context;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.good.list.GoodListRes;
import com.siweisoft.heavycenter.module.main.weigts.weight.WeigtFrag;

/**
 * Created by summer on 2018/1/25 0:33.
 */

public class ScanDAOpe extends BaseDAOpe {


    public ScanDAOpe(Context context ){
        super(context);
    }

    public void logic(final AppFrag appFrag,String data){
        switch (appFrag.getClass().getSimpleName()){
            case "WeightFrag":
                NetDataOpe.Mana.Good.listGood(context, null, new UINetAdapter<GoodListRes>(context) {
                    @Override
                    public void onResult(boolean success, String msg, GoodListRes o) {
                        super.onResult(success, msg, o);
                        if(success){
                            WeigtFrag weigtFrag = (WeigtFrag) appFrag;
                            weigtFrag.getP().getD();
                            weigtFrag.getP().getU();
                        }
                    }
                });
                break;
            default:

                break;

        }
    }
}
