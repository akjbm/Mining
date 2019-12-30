package com.connect.combine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.connect.combine.R;
import com.connect.combine.bean.FriendBean;
import com.connect.combine.bean.InvestResponseBean;

import java.util.List;

public class InvestRecordAdapter extends BaseQuickAdapter<InvestResponseBean.DataBean.InvestBean, BaseViewHolder> {

    public InvestRecordAdapter(int layoutResId, List<InvestResponseBean.DataBean.InvestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, InvestResponseBean.DataBean.InvestBean rawsBean) {
        baseViewHolder.setText(R.id.tv_hash, rawsBean.getTrx_hash());
        baseViewHolder.setText(R.id.tv_value, rawsBean.getAmount());
        baseViewHolder.setText(R.id.tv_time, rawsBean.getCreate_time());
        int status = rawsBean.getStatus();
        if(status==0)
        {
            baseViewHolder.setText(R.id.tv_status, getContext().getString(R.string.yiwancheng));
        }else if(status==1)
        {
            baseViewHolder.setText(R.id.tv_status, getContext().getString(R.string.shibai));
        }else if(status==2)
        {
            baseViewHolder.setText(R.id.tv_status, getContext().getString(R.string.shenhezhong));
        }else
        {
            baseViewHolder.setText(R.id.tv_status, "");
        }
    }
}
