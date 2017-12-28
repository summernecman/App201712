package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.hyphenate.chat.EMChatRoom;
import com.siweisoft.service.bean.AllUserBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserI;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.RoleInfo;

import java.util.ArrayList;
import java.util.List;

public class OnLineListDAOpe extends BaseDAOpe {

    RoleInfo roleInfo;

    UserNetOpe userI;

    private EMChatRoom emChatRoom;

    private OnlineChangeListener onlineChangeListener = new OnlineChangeListener();

    private ArrayList<UserBean> onlines = new ArrayList<>();


    public OnLineListDAOpe(Context context) {
        super(context);
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }



    public UserI getUserI() {
        if (userI == null) {
            userI = new UserNetOpe(context);
        }
        return userI;
    }

    public void getUsersInfoByPhone(ArrayList<UserBean> data, OnFinishListener onFinishListener) {
        getUserI().getUsersInfoByPhone(data, onFinishListener);
    }

    public void getUnTypeUserList(UserBean data, OnFinishListener onFinishListener) {
        getUserI().getUnTypeUserList(data, onFinishListener);
    }


    public void getOtherUsersInfoByPhone(AllUserBean data, OnFinishListener onFinishListener) {
        getUserI().getOtherUsersInfoByPhone(data, onFinishListener);
    }

    public void getOtherUsersInfoByPhone(List<String> strs, OnFinishListener onFinishListener) {
        ArrayList<UserBean> userBeen = new ArrayList<>();
        for (int i = 0; i < strs.size(); i++) {
            UserBean userBean = new UserBean(strs.get(i));
            userBeen.add(userBean);
        }
        AllUserBean allUserBean = new AllUserBean();
        allUserBean.setOther(userBeen);
        allUserBean.setMe(Value.getUserInfo());
        getUserI().getOtherUsersInfoByPhone(allUserBean, onFinishListener);
    }

    public void getUserContactsByUserIdAndType(UserBean userBean,OnFinishListener listener){
        getUserI().getUserContactsByUserIdAndType(userBean,listener);
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
