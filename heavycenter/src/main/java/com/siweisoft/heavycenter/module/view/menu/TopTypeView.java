package com.siweisoft.heavycenter.module.view.menu;

//by summer on 2017-12-19.

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siweisoft.heavycenter.R;

import java.util.ArrayList;

public class TopTypeView extends RelativeLayout {




    private String[] strs;

    private ViewPager viewPager;

    private ArrayList<TextView> textViews = new ArrayList<>();

    public TopTypeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_main_msg_top,this,true);
        LinearLayout viewGroup = (LinearLayout) findViewById(R.id.ll_root);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.style_toptypeview);
        String txts = a.getString(R.styleable.style_toptypeview_txts);
        strs = txts.split(",");
        for(int i=0;i<strs.length;i++){
            TextView t= (TextView) LayoutInflater.from(context).inflate(R.layout.item_main_msg_top_txt,null);
            t.setText(strs[i]);
            viewGroup.addView(t,new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1));
            textViews.add(t);
        }
    }



    public void setIndex(int index){
        if(index>=strs.length){
            index = strs.length;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             
            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<textViews.size();i++){
                    if(position==i){
                        textViews.get(i).setTextColor(getResources().getColorStateList(R.color.color_hv_yelll));
                        textViews.get(i).setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    }else{
                        textViews.get(i).setTextColor(getResources().getColorStateList(R.color.white));
                        textViews.get(i).setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setTxt(int index,String str){
        if(index<0 && index>=str.length()){
            return;
        }
        textViews.get(index).setText(str);
    }


}
