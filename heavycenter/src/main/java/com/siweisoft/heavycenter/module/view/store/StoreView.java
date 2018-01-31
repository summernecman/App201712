package com.siweisoft.heavycenter.module.view.store;

//by summer on 2018-01-08.

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lib.util.ScreenUtil;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.module.view.progress.ProgressView;

public class StoreView extends RelativeLayout {

    private TextView minGoodTV;

    private TextView maxGoodTV;

    private TextView minStoreTV;

    private TextView maxStoreTV;

    private TextView currentTV;

    private ProgressView progressView;

    private View minView;

    private View maxView;



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
        currentTV = findViewById(R.id.tv_current);
        minView = findViewById(R.id.ll_min);
        maxView = findViewById(R.id.ll_max);
        progressView = findViewById(R.id.progress);

    }

    public void setTxt(float minstore,float mingood,float maxstore,float maxgood,float now){

        maxView.setVisibility(View.VISIBLE);
        minView.setVisibility(View.VISIBLE);
        currentTV.setVisibility(View.VISIBLE);
        minStoreTV.setText("仓库安全:"+StringUtil.getStr(minstore)+"t");
        minGoodTV.setText("物料安全:"+StringUtil.getStr(mingood)+"t");
        maxStoreTV.setText("仓库最大:"+StringUtil.getStr(maxstore)+"t");
        maxGoodTV.setText("物料最大:"+StringUtil.getStr(maxgood)+"t");
        currentTV.setText("当前:"+StringUtil.getStr(now)+"t");

        RelativeLayout.LayoutParams p = (LayoutParams) currentTV.getLayoutParams();
        p.leftMargin = (int) ((ScreenUtil.w-ScreenUtil.最小DIMEN *20)*now/(Math.min(maxstore,maxgood)));
        if(p.leftMargin>((ScreenUtil.w-ScreenUtil.最小DIMEN *20)/3)){
            currentTV.setBackgroundResource(R.drawable.icon_hv_main_store_tip_rd);
            p.leftMargin = (int) (p.leftMargin- (StringUtil.getStr(now).length()+2)*ScreenUtil.字宽度);
        }
        currentTV.setLayoutParams(p);

        RelativeLayout.LayoutParams p1 = (LayoutParams) minView.getLayoutParams();
        p1.leftMargin = (int) ((ScreenUtil.w-ScreenUtil.最小DIMEN *20)*(Math.max(mingood,minstore))/((Math.min(maxstore,maxgood))));
        if(p1.leftMargin>((ScreenUtil.w-ScreenUtil.最小DIMEN *20)/2)){
            minView.setBackgroundResource(R.drawable.icon_hv_main_store_rt);
            p1.leftMargin = (int) (p1.leftMargin- (StringUtil.getStr(now).length()+2)*ScreenUtil.字宽度);
            p1.leftMargin = p1.leftMargin- minView.getWidth();
        }
        minView.setLayoutParams(p1);

        progressView.setValues(Math.min(maxstore,maxgood),Math.max(mingood,minstore),now);

    }
}
