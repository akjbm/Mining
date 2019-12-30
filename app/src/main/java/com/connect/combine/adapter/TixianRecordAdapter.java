package com.connect.combine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.connect.combine.R;
import com.connect.combine.bean.TiXianRecordBean;

import java.util.List;

public class TixianRecordAdapter extends BaseQuickAdapter<TiXianRecordBean.DataBean.WithdrawBean, BaseViewHolder> {

    public TixianRecordAdapter(int layoutResId, List<TiXianRecordBean.DataBean.WithdrawBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TiXianRecordBean.DataBean.WithdrawBean rawsBean) {
        baseViewHolder.setText(R.id.tv_price, rawsBean.getToken_name() + "=" + rawsBean.getToken_price() + rawsBean.getAmount_token_name());
        baseViewHolder.setText(R.id.tv_suanli, "-" + rawsBean.getToken_amount());
        baseViewHolder.setText(R.id.tv_time, rawsBean.getCreate_time());
        int status = rawsBean.getStatus();
        if (status == 0) {
            baseViewHolder.setText(R.id.tv_status, getContext().getString(R.string.yiwancheng));
            baseViewHolder.setTextColor(R.id.tv_status, 0xff8f8f8f);
        } else if (status == 3) {
            baseViewHolder.setText(R.id.tv_status, getContext().getString(R.string.shenhezhong));
            baseViewHolder.setTextColor(R.id.tv_status, 0xffD78E00);
        } else {
            baseViewHolder.setText(R.id.tv_status, getContext().getString(R.string.shibai));
            baseViewHolder.setTextColor(R.id.tv_status, 0xffD73900);
        }
    }
}
