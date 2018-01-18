package com.siweisoft.heavycenter.module.mana.good.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.good.news.NewsGoodReq;
import com.siweisoft.heavycenter.databinding.FragManaGoodNewBinding;

public class NewUIOpe extends AppUIOpe<FragManaGoodNewBinding>{


    public NewUIOpe(Context context) {
        super(context);
    }

    public boolean canSpecsGo(NewsGoodReq newsGoodReq){
        if(newsGoodReq.getMaterielId()==-1){
            ToastUtil.getInstance().showShort(getActivity(),"请先选择物料");
            return false;
        }
        return true;
    }

    public boolean canGo(){
        if(NullUtil.isStrEmpty(bind.itemWuniaoname.getMidTvTxt())){
            ToastUtil.getInstance().showShort(getActivity(),"请选择物料");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemWuliaoguige.getMidTvTxt())){
            ToastUtil.getInstance().showShort(getActivity(),"请选择物料规格");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemCangku.getMidTvTxt())){
            ToastUtil.getInstance().showShort(getActivity(),"请选择仓库");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemMaxstock.getMidEtTxt())){
            ToastUtil.getInstance().showShort(getActivity(),"请输入最大库存");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemMinstock.getMidEtTxt())){
            ToastUtil.getInstance().showShort(getActivity(),"请输入安全库存");
            return false;
        }
        if(Float.parseFloat(bind.itemMaxstock.getMidEtTxt())<Float.parseFloat(bind.itemMinstock.getMidEtTxt())){
            ToastUtil.getInstance().showShort(getActivity(),"最大库存不应该小于安全库存");
            return false;
        }
        return true;
    }

    public void init(NewsGoodReq newsGoodReq){
        bind.itemWuniaoname.setMidTVTxt(StringUtil.getStr(newsGoodReq.getMaterielName()));
        bind.itemWuliaoguige.setMidTVTxt(StringUtil.getStr(newsGoodReq.getMaterielSpecName()));
        bind.itemArea.setMidTVTxt(StringUtil.getStr(newsGoodReq.getBelongAreaName()));
        bind.itemCangku.setMidTVTxt(StringUtil.getStr(newsGoodReq.getWarehouseName()));
    }

    public NewsGoodReq getNewsGoodReq(NewsGoodReq newsGoodReq){
        newsGoodReq.setMaxStock(Float.parseFloat(bind.itemMaxstock.getMidEtTxt().toString()));
        newsGoodReq.setMinStock(Float.parseFloat(bind.itemMinstock.getMidEtTxt().toString()));
        return newsGoodReq;
    }
}
