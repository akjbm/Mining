package com.connect.combine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.connect.combine.R;
import com.connect.combine.bean.BuyMeachineResponseBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.constant.HttpConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyMeachineActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.tv_buy_meachine)
    TextView tvBuyMeachine;
    @BindView(R.id.iv_meachine_pic)
    ImageView ivMeachinePic;
    @BindView(R.id.tv_meachine_name)
    TextView tvMeachineName;
    @BindView(R.id.tv_suanli)
    TextView tvSuanli;
    @BindView(R.id.tv_power)
    TextView tvPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_meachine);
        ButterKnife.bind(this);
        OkGo.<BuyMeachineResponseBean>get(HttpConstant.MeachineQuery).execute(new JsonCallback<BuyMeachineResponseBean>() {
            @Override
            public void onSuccess(Response<BuyMeachineResponseBean> response) {
                BuyMeachineResponseBean body = response.body();
                if (body != null && body.getCode() == 0) {
                    BuyMeachineResponseBean.DataBean data = body.getData();
                    if (data != null) {
                        List<BuyMeachineResponseBean.DataBean.RawsBean> raws = data.getRaws();
                        if (raws != null && raws.size() > 0) {
                            BuyMeachineResponseBean.DataBean.RawsBean rawsBean = raws.get(0);
                            tvMeachineName.setText(rawsBean.getName());
                            tvPower.setText(rawsBean.getPower() + "W");
                            tvSuanli.setText(rawsBean.getCapacity() + "TH/S");
                            Glide.with(getBaseContext()).load(rawsBean.getImage()).into(ivMeachinePic);

                        }
                    }
                }
            }
        });


    }

    @OnClick({R.id.fl_close, R.id.tv_buy_meachine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                finish();
                break;
            case R.id.tv_buy_meachine:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, ChongZhiActivity.class));
                break;
        }
    }
}
