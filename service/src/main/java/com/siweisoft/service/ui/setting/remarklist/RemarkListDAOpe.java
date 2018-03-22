package com.siweisoft.service.ui.setting.remarklist;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.comment.CommentBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class RemarkListDAOpe extends BaseDAOpe {


    private int pageindex = 0;

    private int pagesize = 5;

    private ArrayList<CommentBean> list;


    public void getRemarks(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
        if (Value.getUserInfo().getUsertype() == UserBean.USER_TYPE_CUSTOMER) {
            NetDataOpe.Comment.getCommentByUserIdWithLimit(context,userBean, new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    ArrayList<CommentBean> res = (ArrayList<CommentBean>) o;
                    onFinishListener.onFinish(res);
                }
            });
        } else {
            NetDataOpe.Comment.getCommentByUserIdWithLimit(context,userBean, new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    ArrayList<CommentBean> res = (ArrayList<CommentBean>) o;
                    onFinishListener.onFinish(res);
                }
            });
        }

    }

    public int getPageindex() {
        return pageindex;
    }

    public void setPageindex(int pageindex) {
        this.pageindex = pageindex;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public ArrayList<CommentBean> getList() {
        return list;
    }

    public void setList(ArrayList<CommentBean> list) {
        this.list = list;
    }
}
