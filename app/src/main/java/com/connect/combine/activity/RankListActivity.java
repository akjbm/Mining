package com.connect.combine.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.base.BaseActivity;
import com.connect.combine.R;
import com.connect.combine.adapter.GridItemDecoration;
import com.connect.combine.adapter.MineRankListAdapter;
import com.connect.combine.bean.RankListResponseBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.HttpConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RankListActivity extends BaseActivity {

    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.rcv_content)
    RecyclerView rcvContent;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_distance_suanli)
    TextView tvDistanceSuanli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_list);
        ButterKnife.bind(this);
        OkGo.<RankListResponseBean>get(HttpConstant.MineRank).execute(new JsonCallback<RankListResponseBean>() {
            @Override
            public void onSuccess(Response<RankListResponseBean> response) {
                RankListResponseBean body = response.body();
                if (body != null && body.getCode() == 0) {
                    List<RankListResponseBean.DataBean> data = body.getData();
                    if (data != null) {
                        int size = data.size();
                        if (size == 1) {
                            RankListResponseBean.DataBean dataBean = data.get(0);
                            tvName.setText(dataBean.getName());
                            tvDistanceSuanli.setText(dataBean.getCapacity() + getString(R.string.suanli));
                        }
                        if (size >= 1) {
                            RankListResponseBean.DataBean remove = data.remove(0);
                            tvName.setText(remove.getName());
                            tvDistanceSuanli.setText(remove.getCapacity() + getString(R.string.suanli));
                            MineRankListAdapter mineRankListAdapter = new MineRankListAdapter(R.layout.item_ranklist_layout, data);
                            rcvContent.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false));
                            GridItemDecoration decoration = GridItemDecoration.newBuilder().spanCount(1)
                                    .horizontalDivider(new ColorDrawable(0x88ff0000), 3, false)
                                    .build();
                            rcvContent.addItemDecoration(decoration);
                            rcvContent.setAdapter(mineRankListAdapter);
                        }
                    }

                }

            }
        });
    }

    @OnClick(R.id.fl_close)
    public void onViewClicked() {
        finish();
    }
}
