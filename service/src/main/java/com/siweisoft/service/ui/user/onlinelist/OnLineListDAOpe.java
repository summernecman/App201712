package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.hyphenate.chat.EMChatRoom;
import com.siweisoft.service.bean.AllUserBean;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.OnlineChangeListener;
import com.siweisoft.service.ui.main.RoleInfo;

import java.util.ArrayList;
import java.util.List;

public class OnLineListDAOpe extends BaseDAOpe {

    RoleInfo roleInfo;


    private EMChatRoom emChatRoom;

    private OnlineChangeListener onlineChangeListener = new OnlineChangeListener();

    private ArrayList<UserBean> onlines = new ArrayList<>();



    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }




    public void getUsersInfoByPhone(Context context,ArrayList<UserBean> data, OnFinishListener onFinishListener) {
        NetDataOpe.User.getUsersInfoByPhone(context,data, onFinishListener);
    }

    public void getUnTypeUserList(Context context,UserBean data, OnFinishListener onFinishListener) {
        NetDataOpe.User.getUnTypeUserList(context,data, onFinishListener);
    }


    public void getOtherUsersInfoByPhone(Context context,AllUserBean data, OnFinishListener onFinishListener) {
        NetDataOpe.User.getOtherUsersInfoByPhone(context,data, onFinishListener);
    }

    public void getOtherUsersInfoByPhone(Context context,List<String> strs, OnFinishListener onFinishListener) {
        ArrayList<UserBean> userBeen = new ArrayList<>();
        for (int i = 0; i < strs.size(); i++) {
            UserBean userBean = new UserBean(strs.get(i));
            userBeen.add(userBean);
        }
        AllUserBean allUserBean = new AllUserBean();
        allUserBean.setOther(userBeen);
        allUserBean.setMe(Value.getUserInfo());
        NetDataOpe.User.getOtherUsersInfoByPhone(context,allUserBean, onFinishListener);
    }

    public void getUserContactsByUserIdAndType(Context context,UserBean userBean,OnFinishListener listener){
        NetDataOpe.User.getUserContactsByUserIdAndType(context,userBean,listener);
    }



    public EMChatRoom getEmChatRoom() {
        return emChatRoom;
    }

    public void setEmChatRoom(EMChatRoom emChatRoom) {
        this.emChatRoom = emChatRoom;
    }

    public OnlineChangeListener getOnlineChangeListener() {
        return onlineChangeListener;
    }

    public ArrayList<UserBean> getOnlines() {
        return onlines;
    }

    public void setOnlines(ArrayList<UserBean> onlines) {
        this.onlines = onlines;
    }

    public boolean isOnline(String phone) {
        for (int i = 0; i < getOnlines().size(); i++) {
            if (getOnlines().get(i).getPhone().equals(phone)) {
                if (getOnlines().get(i).getState() == UserBean.STATE_ONLINE) {
                    return true;
                }
            }
        }
        return false;
    }
}
