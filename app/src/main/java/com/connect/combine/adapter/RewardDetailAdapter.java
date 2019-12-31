package com.connect.combine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.connect.combine.R;
import com.connect.combine.bean.RewardDetailBean;

import java.util.List;

public class RewardDetailAdapter extends BaseQuickAdapter<RewardDetailBean.DataBean.RewardBean, BaseViewHolder> {

    private String time = "";

    public RewardDetailAdapter(int layoutResId, List<RewardDetailBean.DataBean.RewardBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RewardDetailBean.DataBean.RewardBean rawsBean) {
        String[] s = rawsBean.getCreate_time().split(" ");
        String s1 = s[0];
        if (s1.compareTo(time) > 0) {
            baseViewHolder.setVisible(R.id.fl_time, true);
            time = s1;
            baseViewHolder.setText(R.id.tv_time, s1);
        } else {
            baseViewHolder.setGone(R.id.fl_time, true);
        }
        baseViewHolder.setText(R.id.tv_name, rawsBean.getType_name());
        baseViewHolder.setText(R.id.tv_suanli, "+" + rawsBean.getAmount() + getContext().getString(R.string.suanli));
        //baseViewHolder.setText(R.id.tv_time, rawsBean.getCreate_time());
    }
}
