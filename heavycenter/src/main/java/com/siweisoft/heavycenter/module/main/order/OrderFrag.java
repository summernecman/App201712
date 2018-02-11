package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.android.lib.view.dialog.list.DialogListFrag;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.order.ordernum.OrderNumRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.main.order.begin.BeginFrag;
import com.siweisoft.heavycenter.module.main.order.news.NewOrderFrag;
import com.siweisoft.heavycenter.module.test.SharedElementFragment1;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class OrderFrag extends AppFrag<OrderUIOpe,OrderDAOpe> {


    @Override
    public void onFristVisibleDelayInit() {
        getP().getU().initPages(getFrag(),getP().getD().initPages());
        getP().getD().getOrderCount(new NetAdapter<OrderNumRes>(this){
            @Override
            public void onSuccess(OrderNumRes o) {
                getP().getU().refreshTopMenu(o);
            }
        });

    }

    @OnClick({R.id.ftv_right,R.id.ftv_title,R.id.ftv_right2})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)getBaseAct()).getP().getU().switchDrawer();
                break;
            case R.id.ftv_title:
                List<String> strs = new ArrayList<>();
                for(int i=0;i<10;i++){
                    strs.add("fdfdsfsd"+i);
                }
                DialogListFrag frag = new DialogListFrag();
                frag.init(strs);
                FragManager2.getInstance()
                        .setStartAnim(R.anim.top_in,R.anim.top_out,R.anim.top_in,R.anim.top_out)
                        .setFinishAnim(R.anim.top_out,R.anim.top_in)
                        .setHideLast(false)
                        .start(getBaseUIAct(),MainValue.订单,MainValue.订单ID,frag);
                break;
            case R.id.ftv_right2:

//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//
//                    SharedElementFragment1 detailFrag = new SharedElementFragment1();
//                    detailFrag.iid = MainValue.订单ID;
//                    getFragmentManager().beginTransaction()
//                            .add(detailFrag.iid,detailFrag)
//                            .addToBackStack(null)
//                            .commit();
//                    return;
//                }


                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,2);
                FragManager2.getInstance().start(getBaseUIAct(), MainValue.订单,MainValue.订单ID,new NewOrderFrag(),bundle);
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    MainAct mainAct = (MainAct) getActivity();
                    Intent intent = new Intent(mainAct, CaptureActivity.class);
                    getBaseUIAct().startActivityForResult(intent, ValueConstant.CODE_REQUSET);
                }
                break;
        }
    }

    @Override
    public void onResult(int res, Bundle bundle) {
        super.onResult(res, bundle);
        switch (res){
            case 2:
                if(bundle==null||!bundle.getBoolean(ValueConstant.DATA_DATA,false)){
                    return;
                }
                BeginFrag beginFrag = (BeginFrag) getP().getD().getPages().get(0);
                beginFrag.getP().getU().autoRefresh();
                break;
        }
    }
}
