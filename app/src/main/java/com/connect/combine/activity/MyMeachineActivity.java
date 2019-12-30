package com.connect.combine.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.connect.combine.R;
import com.connect.combine.bean.MeachineResponseBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.HttpConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyMeachineActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_suanli)
    TextView tvSuanli;
    @BindView(R.id.tv_power)
    TextView tvPower;
    @BindView(R.id.tv_place)
    TextView tvPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meachine);
        ButterKnife.bind(this);
        OkGo.<MeachineResponseBean>get(HttpConstant.Meachine).execute(new JsonCallback<MeachineResponseBean>() {
            @Override
            public void onSuccess(Response<MeachineResponseBean> response) {
                MeachineResponseBean body = response.body();
                if (body != null && body.getCode() == 0) {
                    MeachineResponseBean.DataBean data = body.getData();
                    if (data != null && data.getMachine().size() > 0) {
                        MeachineResponseBean.DataBean.MachineBean machineBean = data.getMachine().get(0);
                        Glide.with(getBaseContext()).load(machineBean.getImages()).into(ivPic);
                        tvVersion.setText(machineBean.getModel());
                        tvSuanli.setText(machineBean.getCapacity());
                        tvPower.setText(machineBean.getPower());
                        tvPlace.setText(machineBean.getPosition());
                    }else
                    {

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
