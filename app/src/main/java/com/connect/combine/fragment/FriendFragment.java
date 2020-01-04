package com.connect.combine.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.connect.base.BaseFragment;
import com.connect.base.utils.BarUtils;
import com.connect.combine.R;
import com.connect.combine.activity.InviteFriendActivity;
import com.connect.combine.activity.RankListActivity;
import com.connect.combine.activity.TitleRecycleViewActivity;
import com.connect.combine.activity.WebViewActivity;
import com.connect.combine.bean.FriendBean;
import com.connect.combine.bean.MinePoolResponseBean;
import com.connect.combine.bean.UserInfoBackBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.constant.HttpConstant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FriendFragment extends BaseFragment {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.tv_rules)
    TextView tvRules;
    @BindView(R.id.tv_invite)
    TextView tvInvite;
    @BindView(R.id.tv_friends)
    TextView tvFriends;
    @BindView(R.id.tv_person_num)
    TextView tvPersonNum;
    @BindView(R.id.tv_total_invest)
    TextView tvTotalInvest;
    @BindView(R.id.tv_total_profit)
    TextView tvTotalProfit;
    @BindView(R.id.tv_total_hash)
    TextView tvTotalHash;
    @BindView(R.id.tv_countdown_time)
    TextView tvCountdownTime;
    @BindView(R.id.tv_mine_number)
    TextView tvMineNumber;
    @BindView(R.id.tv_rank_list)
    TextView tvRankList;
    private FriendBean.DataBean data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        ButterKnife.bind(this, view);
        int statusBarHeight = BarUtils.getStatusBarHeight(getContext());
        view.findViewById(R.id.status_bar).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("FriendBean", "FriendBean");
        OkGo.<FriendBean>get(HttpConstant.KuangyouInfo).execute(new JsonCallback<FriendBean>() {
            @Override
            public void onSuccess(Response<FriendBean> response) {
                FriendBean body = response.body();
                if (body != null && body.getCode() == 0) {
                    data = body.getData();

                }
            }
        });
        OkGo.<MinePoolResponseBean>get(HttpConstant.MinePool).execute(new JsonCallback<MinePoolResponseBean>() {
            @Override
            public void onSuccess(Response<MinePoolResponseBean> response) {
                MinePoolResponseBean body = response.body();
                if (body != null && body.getData() != null) {
                    MinePoolResponseBean.DataBean data = body.getData();
                    int time = data.getTime();
                    String total = data.getTotal();
                    tvTotalHash.setText(total);
                    int day = time / 86400;
                    int hour = (time - 86400 * day) / 3600;
                    int minutes = (time - 86400 * day - 3600 * hour) / 60;
                    String finalTime = "";
                    if (day != 0) {
                        finalTime = finalTime + day + "天";
                    }
                    if (hour != 0) {
                        finalTime = finalTime + hour + "小时";
                    }
                    if (minutes != 0) {
                        finalTime = finalTime + minutes + "分钟";
                    }

                    tvCountdownTime.setText(finalTime);
                    tvMineNumber.setText(getString(R.string.gongyou_kuangzhu, data.getNodeCnt() + ""));

                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        //OkGo.getInstance().cancelAll();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //  OkGo.getInstance().cancelAll();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserInfoBackBean.DataBean.WalletBean event) {

        String children_cnt = event.getChildren_cnt();
        String descendant_cnt = event.getDescendant_cnt();
        if (TextUtils.isEmpty(children_cnt)) {
            tvPersonNum.setText(0 + "人");
        } else {
            tvPersonNum.setText(children_cnt + "人");
        }
        if (TextUtils.isEmpty(descendant_cnt)) {
            tvTotalInvest.setText(0 + "人");
        } else {
            tvTotalInvest.setText(descendant_cnt + "人");
        }
        String value = TextUtils.isEmpty(event.getMy_reward()) ? "0" : event.getMy_reward();
        tvTotalProfit.setText(value + getString(R.string.suanli));
    }

    @OnClick({R.id.tv_rules, R.id.tv_invite, R.id.tv_friends, R.id.tv_rank_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rules:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, WebViewActivity.class));
                break;
            case R.id.tv_invite:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, InviteFriendActivity.class));
                break;
            case R.id.tv_friends:
                Intent intent = new Intent(AppConstant.GLOBAL_CONTEXT, TitleRecycleViewActivity.class);
                intent.putExtra("mode", 1);
                if (data != null && data.getDescendant() != null && data.getDescendant().size() > 0) {
                    String json = new Gson().toJson(data.getDescendant());
                    intent.putExtra("data", json);
                    startActivity(intent);
                } else {
                    startActivity(intent);
                }
                break;
            case R.id.tv_rank_list:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, RankListActivity.class));
                break;
        }
    }


}
