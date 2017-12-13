package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.network.netadapter.UINetAdapter;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.onNetWrokResAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.module.main.count.CountFrag;
import com.siweisoft.heavycenter.module.main.order.OrderFrag;
import com.siweisoft.heavycenter.module.main.store.StoreFrag;
import com.siweisoft.heavycenter.module.main.trans.TransFrag;
import com.siweisoft.heavycenter.module.main.weigt.WeigtFrag;
import com.siweisoft.heavycenter.data.netd.NetApi;

import java.util.ArrayList;

public class MainDAOpe extends AppDAOpe {


    private ArrayList<Fragment> pages = new ArrayList<>();

    private ArrayList<BottomMenuBean> menudata = new ArrayList<>();

    public MainDAOpe(Context context) {
        super(context);
        initBottomMenuViewData();
        initPages();
    }

    protected ArrayList<BottomMenuBean> initBottomMenuViewData(){
        if(menudata==null){
            menudata = new ArrayList<>();
        }
        menudata.clear();
        menudata.add(new BottomMenuBean("地磅", R.drawable.drawable_bed));
        menudata.add(new BottomMenuBean("运输单", R.drawable.drawable_bed));
        menudata.add(new BottomMenuBean("订单", R.drawable.drawable_bed));
        menudata.add(new BottomMenuBean("仓库", R.drawable.drawable_bed));
        menudata.add(new BottomMenuBean("盘库", R.drawable.drawable_bed));
        return menudata;
    }

    protected ArrayList<Fragment> initPages(){
        if(pages==null){
            pages = new ArrayList<>();
        }
        pages.clear();
        pages.add(new WeigtFrag());
        pages.add(new TransFrag());
        pages.add(new OrderFrag());
        pages.add(new StoreFrag());
        pages.add(new CountFrag());
        LogUtil.E(pages.get(1).getId());
        return pages;
    }

    public void post(){
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData("");
        NetWork.postData(context, NetApi.getURL(NetApi.测试接口), baseReqBean, new onNetWrokResAdapter() {
            @Override
            public void onNetWorkResult(boolean success, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o));
            }
        });
    }

    public ArrayList<Fragment> getPages() {
        return pages;
    }

    public ArrayList<BottomMenuBean> getMenudata() {
        return menudata;
    }
}
