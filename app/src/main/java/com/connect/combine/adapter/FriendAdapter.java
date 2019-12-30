package com.connect.combine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.connect.combine.R;
import com.connect.combine.bean.FriendBean;

import java.util.List;

public class FriendAdapter extends BaseQuickAdapter<FriendBean.DataBean.DescendantBean, BaseViewHolder> {

    public FriendAdapter(int layoutResId, List<FriendBean.DataBean.DescendantBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, FriendBean.DataBean.DescendantBean rawsBean) {
        baseViewHolder.setText(R.id.tv_phone, rawsBean.getPhone());
        baseViewHolder.setText(R.id.tv_value, rawsBean.getTotal_invest());
        baseViewHolder.setText(R.id.tv_time, rawsBean.getCreate_time());
    }
}
