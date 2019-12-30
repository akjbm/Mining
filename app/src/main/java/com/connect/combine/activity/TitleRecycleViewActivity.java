package com.connect.combine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.connect.combine.R;
import com.connect.combine.adapter.AnnouncementAdapter;
import com.connect.combine.adapter.FriendAdapter;
import com.connect.combine.adapter.InvestRecordAdapter;
import com.connect.combine.adapter.TixianRecordAdapter;
import com.connect.combine.bean.BannerResponseBean;
import com.connect.combine.bean.FriendBean;
import com.connect.combine.bean.InvestResponseBean;
import com.connect.combine.bean.NewsResponseBean;
import com.connect.combine.bean.TiXianRecordBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.HttpConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TitleRecycleViewActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_content)
    RecyclerView rcvContent;
    @BindView(R.id.ll_RecyclerView_title)
    LinearLayout llRecyclerViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_recycle_view);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int mode = intent.getIntExtra("mode", -1);
        if (mode == 0) {
            llRecyclerViewTitle.setVisibility(View.GONE);
            String date = intent.getStringExtra("data");
            Type type = new TypeToken<List<NewsResponseBean.DataBean.RawsBean>>() {
            }.getType();
            tvTitle.setText(getString(R.string.gonggao_zhongxin));
            List<NewsResponseBean.DataBean.RawsBean> contents = new Gson().fromJson(date, type);
            AnnouncementAdapter announcementAdapter = new AnnouncementAdapter(R.layout.item_anno_layout, contents);
            rcvContent.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL,false));
            rcvContent.setAdapter(announcementAdapter);
        }else  if(mode==1){
            String date = intent.getStringExtra("data");
            llRecyclerViewTitle.setVisibility(View.VISIBLE);
            Type type = new TypeToken<FriendBean.DataBean.DescendantBean>() {
            }.getType();
            List<FriendBean.DataBean.DescendantBean> contents = new Gson().fromJson(date, type);
            tvTitle.setText(getString(R.string.wode_kuangyou));
            FriendAdapter friendAdapter = new FriendAdapter(R.layout.item_friend_layout, contents);
            rcvContent.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL,false));
            rcvContent.setAdapter(friendAdapter);

        }else if(mode==2){
            tvTitle.setText(getString(R.string.tixian_jilu));
            llRecyclerViewTitle.setVisibility(View.GONE);
            OkGo.<TiXianRecordBean>get(HttpConstant.Ti_xian_Record).execute(new JsonCallback<TiXianRecordBean>() {
                @Override
                public void onSuccess(Response<TiXianRecordBean> response) {
                    TiXianRecordBean body = response.body();
                    if(body!=null&&body.getData()!=null)
                    {
                        TiXianRecordBean.DataBean data = body.getData();
                        List<TiXianRecordBean.DataBean.WithdrawBean> withdraw = data.getWithdraw();
                        TixianRecordAdapter tixianRecordAdapter = new TixianRecordAdapter(R.layout.item_tixian_record_layout, withdraw);
                        rcvContent.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL,false));
                        rcvContent.setAdapter(tixianRecordAdapter);
                    }
                }
            });


        }else if(mode==3)
        {
            tvTitle.setText(getString(R.string.chongzhi_jilu));
            llRecyclerViewTitle.setVisibility(View.GONE);
            OkGo.<InvestResponseBean>get(HttpConstant.Invest).execute(new JsonCallback<InvestResponseBean>() {
                @Override
                public void onSuccess(Response<InvestResponseBean> response) {
                    InvestResponseBean body = response.body();
                    if(body!=null&&body.getCode()==0)
                    {
                        InvestResponseBean.DataBean data = body.getData();
                        if(data!=null)
                        {
                            List<InvestResponseBean.DataBean.InvestBean> invest = data.getInvest();
                            InvestRecordAdapter investRecordAdapter = new InvestRecordAdapter(R.layout.item_chongzhi_layout, invest);
                            rcvContent.setLayoutManager(new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL,false));
                            rcvContent.setAdapter(investRecordAdapter);
                        }

                    }
                }
            });


        }

    }

    @OnClick({R.id.fl_close, R.id.tv_title, R.id.rcv_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                finish();
                break;
            case R.id.tv_title:
                break;
            case R.id.rcv_content:
                break;
        }
    }
}
