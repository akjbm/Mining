package com.connect.combine.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.connect.base.utils.ClipboardUtils;
import com.connect.base.utils.SizeUtils;
import com.connect.combine.R;
import com.connect.combine.bean.UserInfoBackBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.constant.HttpConstant;
import com.connect.combine.utils.BitmapUtils;
import com.github.yoojia.qrcode.qrcode.QRCodeEncoder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChongZhiActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.exit)
    FrameLayout exit;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_record)
    TextView tvRecord;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.tv_save_pic)
    TextView tvSavePic;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_copy)
    TextView tvCopy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_zhi);
        ButterKnife.bind(this);
        checkPermission();
        OkGo.<UserInfoBackBean>get(HttpConstant.UserInfo).execute(new JsonCallback<UserInfoBackBean>() {
            @Override
            public void onSuccess(Response<UserInfoBackBean> response) {
                UserInfoBackBean body = response.body();
                if (body.getCode() == 0) {
                    String phone = body.getData().getUser().getPhone();
                    String name = body.getData().getUser().getName();
                    AppConstant.InviteCode = body.getData().getUser().getInvite_code();
                    try {
                        UserInfoBackBean.DataBean.InvestAddrBean investAddr = body.getData().getInvestAddr();
                        String addr = investAddr.getAddr();
                        Bitmap qrCodeImage = new QRCodeEncoder.Builder()
                                .width(SizeUtils.dp2px(AppConstant.GLOBAL_CONTEXT, 155)) // 二维码图案的宽度
                                .height(SizeUtils.dp2px(AppConstant.GLOBAL_CONTEXT, 155))
                                .paddingPx(0) // 二维码的内边距
                                .marginPt(3) // 二维码的外边距
                                .build()
                                .encode(addr);
                        tvInfo.setText(addr);
                        ivQrcode.setImageBitmap(qrCodeImage);
                    } catch (Exception e) {
                        Bitmap qrCodeImage = new QRCodeEncoder.Builder()
                                .width(SizeUtils.dp2px(AppConstant.GLOBAL_CONTEXT, 155)) // 二维码图案的宽度
                                .height(SizeUtils.dp2px(AppConstant.GLOBAL_CONTEXT, 155))
                                .paddingPx(0) // 二维码的内边距
                                .marginPt(3) // 二维码的外边距
                                .build()
                                .encode("000000");
                        tvInfo.setText("000000");
                        ivQrcode.setImageBitmap(qrCodeImage);
                    }

                }
            }
        });


    }

    @OnClick({R.id.exit, R.id.title, R.id.tv_record, R.id.iv_qrcode, R.id.tv_save_pic, R.id.tv_info, R.id.tv_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.title:
                break;
            case R.id.tv_record:
                Intent intent = new Intent(AppConstant.GLOBAL_CONTEXT, TitleRecycleViewActivity.class);
                intent.putExtra("mode", 3);
                startActivity(intent);
                break;
            case R.id.iv_qrcode:
                break;
            case R.id.tv_save_pic:
                Bitmap bitmap = BitmapUtils.createBitmap(ivQrcode);
                BitmapUtils.saveBitmap(bitmap);
                break;
            case R.id.tv_info:
                break;
            case R.id.tv_copy:
                try {
                    ClipboardUtils.copyText(AppConstant.GLOBAL_CONTEXT, tvInfo.getText().toString());
                    showToast(getString(R.string.fuzhi_chenggong));
                } catch (Exception e) {

                    showToast("");
                }

                break;
        }
    }

    private void checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android 6.0以上
            int writePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (writePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            } else {

            }
        } else {//android 6.0以下

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {//允许

            } else {//拒绝

            }
        }
    }
}
