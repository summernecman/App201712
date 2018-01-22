package com.siweisoft.heavycenter.module.myce.unit.list;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchReqBean;
import com.siweisoft.heavycenter.databinding.FragMyceUnitListBinding;
import com.siweisoft.heavycenter.databinding.ItemMyceUnitBindBinding;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.view.center.DiaLogCenterFrag;

import java.util.List;

public class ListUIOpe extends AppUIOpe<FragMyceUnitListBinding>{

    private SearchReqBean searchReqBean = new SearchReqBean();

    public ListUIOpe(Context context) {
        super(context);
    }


    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(ListResBean o, final ViewListener listener) {
    if(o==null){
        return;
    }

        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_myce_unit_bind, BR.item_myce_unit_bind, o.getResults(),listener){

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                ItemMyceUnitBindBinding binding = (ItemMyceUnitBindBinding) holder.viewDataBinding;
                if(selecPos==position){
                    binding.ivSel.setSelected(true);
                }else{
                    binding.ivSel.setSelected(false);
                }
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                selecPos = (int) v.getTag(R.id.position);
                notifyDataSetChanged();
            }
        });

    }


    public void showTip(View.OnClickListener onClickListener){
        DiaLogCenterFrag diaLogCenterFrag = new DiaLogCenterFrag();
        diaLogCenterFrag.setCustomView(LayoutInflater.from(context).inflate(R.layout.frag_myce_unit_bind_tip_nullunit,null));
        diaLogCenterFrag.setOnClickListener(onClickListener,R.id.tv_y,R.id.tv_n,R.id.tv_close);
        FragManager2.getInstance().setStartAnim(R.anim.scale_in,R.anim.scale_out,R.anim.scale_in,R.anim.scale_out).setFinishAnim(R.anim.fade_in,R.anim.fade_out).start(getActivity(),MainAct.主界面,diaLogCenterFrag);
    }


    public SearchReqBean getSearchReqBean() {
        searchReqBean.setKeyword(bind.search.getEditText().getText().toString());
        return searchReqBean;
    }
    public void initRefresh(OnRefreshListener onRefreshListener){
        bind.refreshLayout.setOnRefreshListener(onRefreshListener);
    }

    public void finishRefresh(){
        bind.refreshLayout.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refreshLayout.finishLoadmore();
    }

    public void autoRefresh(){
        bind.refreshLayout.autoRefresh();
    }

    public void notifyDataSetChanged(){
        bind.recycle.getAdapter().notifyDataSetChanged();
    }
}
