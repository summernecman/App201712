package com.siweisoft.service.ui.Constant;

//by summer on 2017-07-04.

import android.os.Environment;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.SPUtil;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMChatRoom;
import com.siweisoft.service.R;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.data.netd.videotip.VideoTipBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Value extends ValueConstant {


    public static int FULLSCREEN = R.id.rlroot;

    public static int ROOTID = R.id.serviceroot;

    public static int ROOTID_ONE = R.id.one;

    public static int ROOTID_TWO = R.id.two;

    public static int ROOTID_THREE = R.id.three;

    public static EMChatRoom room;

    public static String roomid;

    public static String ROOM;

    public static String VIDEO_TIPS = "VIDEO_TIPS";

    public static HashMap<String, VideoTipBean> videotips;

    public static void saveVideoTips(String videotips) {
        SPUtil.getInstance().saveStr(VIDEO_TIPS, videotips);

        Value.videotips = GsonUtil.getInstance().fromJson(videotips, new TypeToken<HashMap<String, VideoTipBean>>() {
        }.getType());
    }


    public static final String autologin = "autologin";

    public static HashMap<String, VideoTipBean> getVideotips() {
        if (Value.videotips == null) {
            Value.videotips = GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(VIDEO_TIPS), new TypeToken<HashMap<String, VideoTipBean>>() {
            }.getType());
        }
        return Value.videotips;
    }

    public static ArrayList<VideoTipBean> getVideotipsList() {
        HashMap<String, VideoTipBean> data = getVideotips();
        ArrayList<VideoTipBean> list = new ArrayList<>();
        if (data.keySet() != null) {
            Iterator<String> iterator = data.keySet().iterator();
            while (iterator.hasNext()) {
                list.add(data.get(iterator.next()));
            }
            return list;
        }
        return new ArrayList<>();
    }

    /**获取名称叫 其他 的videotips*/
    public static VideoTipBean getOtherVideotip() {
        HashMap<String, VideoTipBean> data = getVideotips();
        return data.get("0");
    }



    public static void saveRoom(EMChatRoom o) {
        SPUtil.getInstance().saveStr(ROOM, GsonUtil.getInstance().toJson(o));
        room = o;
    }

    public static EMChatRoom getRoom() {
        if (room == null) {
            room = GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(ROOM), EMChatRoom.class);
        }
        return room;
    }

    public static int position = 0;

    public static String CACHE_FILE = "videocache";

    public static ArrayList<Integer> root = new ArrayList<>();

    private static UserBean userBean;

    public static final String USERBEAN = "userbean";


    public static void saveUserInfo(UserBean o) {
        SPUtil.getInstance().saveStr(USERBEAN, GsonUtil.getInstance().toJson(o));
        userBean = o;
    }

    public static UserBean getUserInfo() {
        if (userBean == null) {
            userBean = GsonUtil.getInstance().fromJson(SPUtil.getInstance().getStr(USERBEAN), UserBean.class);
        }
        return userBean;
    }

    static {
        root.add(ROOTID_ONE);
        root.add(ROOTID_TWO);
        root.add(ROOTID_THREE);
    }

    public static Integer getNowRoot() {
        if (root.size() < 3) {
            root.clear();
            root.add(ROOTID_ONE);
            root.add(ROOTID_TWO);
            root.add(ROOTID_THREE);
        }
        return root.get(position);
    }

    public static void setPostion(int postion) {
        Value.position = postion;
    }

    public static File getCacheFile() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + CACHE_FILE);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


}
