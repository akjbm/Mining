package com.connect.combine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.connect.combine.R;
import com.connect.combine.bean.NewsResponseBean;

import java.util.List;

public class AnnouncementAdapter extends BaseQuickAdapter<NewsResponseBean.DataBean.RawsBean, BaseViewHolder> {

    public AnnouncementAdapter(int layoutResId, List<NewsResponseBean.DataBean.RawsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsResponseBean.DataBean.RawsBean rawsBean) {
        baseViewHolder.setText(R.id.tv_title, rawsBean.getTitle());
        baseViewHolder.setText(R.id.tv_time, rawsBean.getCreate_time());
    }
}
