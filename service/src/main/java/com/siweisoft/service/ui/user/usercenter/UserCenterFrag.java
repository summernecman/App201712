package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.util.FragmentUtil2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.video.VideoTimeBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.setting.aboutus.AboutUsFrag;
import com.siweisoft.service.ui.setting.account.AccountFrag;
import com.siweisoft.service.ui.setting.collect.CollecFrag;
import com.siweisoft.service.ui.setting.feedback.FeedBackFrag;
import com.siweisoft.service.ui.setting.remarklist.RemarkListFrag;
import com.siweisoft.service.ui.setting.setting.SettingFrag;
import com.siweisoft.service.ui.setting.sharelist.ShareListFrag;
import com.siweisoft.service.ui.user.unit.UnitFrag;
import com.siweisoft.service.ui.user.userheadname.UserHeadNameFrag;

import java.util.HashMap;

import butterknife.OnClick;
import butterknife.Optional;

public class UserCenterFrag extends BaseServerFrag<UserCenterUIOpe, UserCenterDAOpe> implements OnRefreshListener{

    @Override
    public void initdelay() {
        getP().getU().initRefresh(this);
    }

    @Override
    public void initNow() {
        super.initNow();
        setTitleBean(new TitleBean("", "个人中心", ""));
        getP().getU().initHead();
        getP().getD().getUserCallInfo(getBaseAct(),new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                VideoTimeBean res = (VideoTimeBean) o;
                getP().getU().initCallInfo(res);
            }
        });

        UserCenterDAOpe.getUserTips(getBaseAct(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initTips(getP().getD().mapTolist((HashMap<Integer, TipBean>) o));
            }
        });

        getP().getD().getCommentNumByUserName(getBaseAct(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initCommentNum((String) o);
            }
        });

        getP().getD().getShareNumByUserPhone(getBaseAct(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initShareNum((String) o);
            }
        });


        getP().getD().getCollectionNumByUserId(getBaseAct(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initCollectNum((String) o);
            }
        });

        getP().getU().finishRefresh();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        getP().getU().initHead();
    }

    @Optional
    @OnClick({R.id.ll_head, R.id.ll_remark, R.id.ll_collect, R.id.ll_account, R.id.ll_feedback, R.id.aboutus, R.id.set, R.id.ll_share, R.id.ll_unit})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new UserHeadNameFrag());
                break;
            case R.id.ll_remark:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new RemarkListFrag());
                break;
            case R.id.ll_collect:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new CollecFrag());
                break;
            case R.id.ll_account:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new AccountFrag());
                break;
            case R.id.ll_feedback:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new FeedBackFrag());
                break;
            case R.id.aboutus:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new AboutUsFrag());
                break;
            case R.id.set:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new SettingFrag());
                break;
            case R.id.ll_share:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new ShareListFrag());
                break;
            case R.id.ll_unit:
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_THREE, new UnitFrag());
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        initNow();
    }
}
