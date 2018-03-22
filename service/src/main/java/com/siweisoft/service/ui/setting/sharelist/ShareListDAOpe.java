package com.siweisoft.service.ui.setting.sharelist;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.share.ShareBean;
import com.siweisoft.service.data.netd.video.VideoBean;

import java.util.ArrayList;

public class ShareListDAOpe extends BaseDAOpe {



    ArrayList<VideoBean> videos = new ArrayList<>();

    private int pagesize = 5;

    private int pagestart = 0;



    public void getSharesByReceipt(Context context,ShareBean shareBean, OnFinishListener onFinishListener) {
        NetDataOpe.Share.getSharesByReceiptWithLimit(context,shareBean, onFinishListener);
    }

    public ArrayList<VideoBean> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<VideoBean> videos) {
        this.videos = videos;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPagestart() {
        return pagestart;
    }

    public void setPagestart(int pagestart) {
        this.pagestart = pagestart;
    }
}
