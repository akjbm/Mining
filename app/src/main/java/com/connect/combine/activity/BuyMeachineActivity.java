package com.connect.combine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.connect.combine.R;
import com.connect.combine.constant.AppConstant;
import com.github.yoojia.qrcode.qrcode.QRCodeEncoder;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_meachine);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.fl_close, R.id.tv_buy_meachine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                finish();
                break;
            case R.id.tv_buy_meachine:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT,ChongZhiActivity.class));
                break;
        }
    }
}
