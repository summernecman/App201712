package com.siweisoft.heavycenter.module.view.weight;

//by summer on 2018-01-08.

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.lib.bean.databean.XYBean;
import com.android.lib.util.ColorUtil;
import com.android.lib.util.ScreenUtil;
import com.siweisoft.heavycenter.R;

import java.util.ArrayList;

public class WeightView extends View{

    private int w;

    private int h;

    private double r;

    private double lengh = ScreenUtil.mw*15;

    private ArrayList<WeightBean[]> p = new ArrayList<>();
    private int num = 180;
    private double e = 360/180d;

    private Paint paint = new Paint();



    public WeightView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        w = getWidth();
        h = getHeight();
        r = Math.min(w,h)/2;
        init();
    }

    public void init(){
        if(p.size()>0){
            return;
        }
        setLayerType( LAYER_TYPE_SOFTWARE , null);
        for(int i=0;i<180;i++){
            WeightBean[] beans  = new WeightBean[]{new WeightBean(),new WeightBean()};
            beans[0].x = -r*Math.sin(Math.toRadians(e*i))+w/2;
            beans[0].y = r*Math.cos(Math.toRadians(e*i))+h/2;
            beans[0].color = ColorUtil.getInstance().getGradualColor(getResources().getColor(R.color.color_hv_weightview_start),getResources().getColor(R.color.color_hv_weightview_end),i/180d);
            beans[1].x = -(r-lengh)*Math.sin(Math.toRadians(e*i))+w/2;
            beans[1].y = (r-lengh)*Math.cos(Math.toRadians(e*i))+h/2;
            p.add(beans);
        }
        paint.setAntiAlias(true);
        paint.setStrokeWidth(3);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<p.size();i++){
            paint.setColor(p.get(i)[0].color);
            paint.setShadowLayer(3,0,0,p.get(i)[0].color);
            canvas.drawLine((float) p.get(i)[0].x,(float)p.get(i)[0].y,(float)p.get(i)[1].x,(float)p.get(i)[1].y,paint);
        }
    }
}
