package com.siweisoft.heavycenter.module.view.store;

//by summer on 2018-01-08.

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.R;

public class StoreView extends RelativeLayout {

    private TextView minGoodTV;

    private TextView maxGoodTV;

    private TextView minStoreTV;

    private TextView maxStoreTV;


    public StoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_main_store_detail_top,this,true);
        minGoodTV = (TextView) findViewById(R.id.tv_minwuliao);
        maxGoodTV = (TextView) findViewById(R.id.tv_maxwuliao);
        minStoreTV = (TextView) findViewById(R.id.tv_minstore);
        maxStoreTV = (TextView) findViewById(R.id.tv_maxstore);

    }

    public void setTxt(float minstore,float mingood,float maxstore,float maxgood){
        minStoreTV.setText("仓库安全:"+StringUtil.getStr(minstore)+"t");
        minGoodTV.setText("物料安全:"+StringUtil.getStr(mingood)+"t");
        maxStoreTV.setText("仓库最大:"+StringUtil.getStr(maxstore)+"t");
        maxGoodTV.setText("物料最大:"+StringUtil.getStr(maxgood)+"t");

    }
}
