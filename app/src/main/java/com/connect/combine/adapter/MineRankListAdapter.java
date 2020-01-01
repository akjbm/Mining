package com.connect.combine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.connect.combine.R;
import com.connect.combine.bean.RankListResponseBean;

import java.util.List;

public class MineRankListAdapter extends BaseQuickAdapter<RankListResponseBean.DataBean, BaseViewHolder> {

    public MineRankListAdapter(int layoutResId, List<RankListResponseBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RankListResponseBean.DataBean rawsBean) {
        baseViewHolder.setText(R.id.tv_name, rawsBean.getName());
        baseViewHolder.setText(R.id.tv_account, rawsBean.getMinerID());
        baseViewHolder.setText(R.id.tv_suanli, rawsBean.getCapacity());
        if (baseViewHolder.getLayoutPosition() == 0) {
            baseViewHolder.setGone(R.id.tv_rank, true);
            baseViewHolder.setGone(R.id.iv_rank, false);
            baseViewHolder.setImageResource(R.id.iv_rank, R.mipmap.first);
        } else if (baseViewHolder.getLayoutPosition() == 1) {
            baseViewHolder.setGone(R.id.tv_rank, true);
            baseViewHolder.setGone(R.id.iv_rank, false);
            baseViewHolder.setImageResource(R.id.iv_rank, R.mipmap.second);
        } else if (baseViewHolder.getLayoutPosition() == 3) {
            baseViewHolder.setGone(R.id.tv_rank, true);
            baseViewHolder.setGone(R.id.iv_rank, false);
            baseViewHolder.setImageResource(R.id.iv_rank, R.mipmap.third);
        } else {
            baseViewHolder.setGone(R.id.tv_rank, false);
            baseViewHolder.setGone(R.id.iv_rank, true);
            baseViewHolder.setText(R.id.tv_rank, baseViewHolder.getLayoutPosition());

        }

    }
}
