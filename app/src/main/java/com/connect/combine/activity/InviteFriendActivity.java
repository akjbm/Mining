package com.connect.combine.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.connect.base.utils.ClipboardUtils;
import com.connect.combine.R;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.utils.BitmapUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteFriendActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.exit)
    FrameLayout exit;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_invite_code)
    TextView tvInviteCode;
    @BindView(R.id.tv_save_pic)
    TextView tvSavePic;
    @BindView(R.id.tv_copy_link)
    TextView tvCopyLink;
    @BindView(R.id.fl_save_pic)
    FrameLayout savePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
        ButterKnife.bind(this);
        checkPermission();
        tvInviteCode.setText(AppConstant.InviteCode);
    }

    @OnClick({R.id.exit, R.id.tv_invite_code, R.id.tv_save_pic, R.id.tv_copy_link})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.tv_invite_code:
               // tvInviteCode.setText(AppConstant.InviteCode);
                break;
            case R.id.tv_save_pic:
                try {
                    Bitmap bitmap = BitmapUtils.createBitmap(savePic);
                    BitmapUtils.saveBitmap1(bitmap);
                    showToast(getString(R.string.save_success));
                }catch (Exception e){

                }
                break;
            case R.id.tv_copy_link:
                try {
                    ClipboardUtils.copyText(AppConstant.GLOBAL_CONTEXT, tvInviteCode.getText().toString());
                    showToast(getString(R.string.fuzhi_chenggong));
                }catch (Exception e)
                {

                }
                break;
        }
    }


    private void checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android 6.0以上
            int writePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (writePermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
            }else{

            }
        }else{//android 6.0以下

        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && grantResults[0]
                    == PackageManager.PERMISSION_GRANTED) {//允许

            } else {//拒绝

            }
        }
    }


}
