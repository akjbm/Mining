package com.connect.combine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.connect.base.BaseFragment;
import com.connect.combine.R;
import com.connect.combine.activity.BuyMeachineActivity;
import com.connect.combine.activity.GetMoneyActivity;
import com.connect.combine.bean.UserInfoBackBean;
import com.connect.combine.constant.AppConstant;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalleFragment extends BaseFragment {

    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.tv_buy_meachine)
    TextView tvBuyMeachine;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tv_my_suanli)
    TextView tvMySuanli;
    @BindView(R.id.tv_total_output)
    TextView tvTotalOutput;
    @BindView(R.id.tv_yue)
    TextView tvYue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walle, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @OnClick({R.id.tv_tixian, R.id.tv_buy_meachine, R.id.tv_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tixian:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, GetMoneyActivity.class));
                break;
            case R.id.tv_buy_meachine:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, BuyMeachineActivity.class));
                break;
            case R.id.tv_detail:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(UserInfoBackBean.DataBean.WalletBean wallet) {
        String value= TextUtils.isEmpty(wallet.getMy_power())?"0":wallet.getMy_power();
        tvMySuanli.setText(value+getString(R.string.suanli));
        try {
            tvTotalOutput.setText(Integer.parseInt(wallet.getMy_output()) + Integer.parseInt(wallet.getMy_output())+getString(R.string.suanli));
        }catch (NumberFormatException e)
        {
            tvTotalOutput.setText("0"+getString(R.string.suanli));
        }
        String yue= TextUtils.isEmpty(wallet.getWithdrawable_amount())?"0":wallet.getWithdrawable_amount();
        tvYue.setText(yue+getString(R.string.suanli));

    }


}
