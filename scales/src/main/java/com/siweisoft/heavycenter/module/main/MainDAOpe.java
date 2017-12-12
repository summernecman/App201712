package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.netadapter.UINetAdapter;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.base.AppValue;
import com.siweisoft.heavycenter.module.main.count.CountFrag;
import com.siweisoft.heavycenter.module.main.order.OrderFrag;
import com.siweisoft.heavycenter.module.main.store.StoreFrag;
import com.siweisoft.heavycenter.module.main.trans.TransFrag;
import com.siweisoft.heavycenter.module.main.weigt.WeigtFrag;
import com.siweisoft.heavycenter.tools.NetApi;

import java.util.ArrayList;

public class MainDAOpe extends AppDAOpe {


    private ArrayList<Fragment> pages = new ArrayList<>();

    public MainDAOpe(Context context) {
        super(context);

    }

    public void post(){
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData("");
        NetWork.postData(context, AppValue.getURL(NetApi.getUserList), baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean success, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o));
            }
        });
    }

    public ArrayList<BottomMenuBean> getBottomMenuViewData(){
        ArrayList<BottomMenuBean> data = new ArrayList<>();
        data.add(new BottomMenuBean("地磅", R.drawable.drawable_bed));
        data.add(new BottomMenuBean("运输单", R.drawable.drawable_bed));
        data.add(new BottomMenuBean("订单", R.drawable.drawable_bed));
        data.add(new BottomMenuBean("仓库", R.drawable.drawable_bed));
        data.add(new BottomMenuBean("盘库", R.drawable.drawable_bed));
        return data;
    }

    public ArrayList<Fragment> initPages(){
        pages.clear();
        pages.add(new WeigtFrag());
        pages.add(new TransFrag());
        pages.add(new OrderFrag());
        pages.add(new StoreFrag());
        pages.add(new CountFrag());
        return pages;
    }

    public ArrayList<Fragment> getPages() {
        return pages;
    }
}
