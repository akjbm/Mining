package com.connect.combine.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.connect.base.utils.SPUtils;
import com.connect.combine.R;
import com.connect.combine.bean.BaseBean;
import com.connect.combine.bean.UserInfoBackBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.constant.HttpConstant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_uid)
    TextView tvUid;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.exit_login)
    TextView exitLogin;
    @BindView(R.id.fl_close)
    FrameLayout flClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        OkGo.<UserInfoBackBean>get(HttpConstant.UserInfo).execute(new JsonCallback<UserInfoBackBean>() {
            @Override
            public void onSuccess(Response<UserInfoBackBean> response) {
                UserInfoBackBean body = response.body();
                if (body.getCode() == 0) {
                    String phone = body.getData().getUser().getPhone();
                    String name = body.getData().getUser().getName();
                    tvName.setText(name);
                    tvPhone.setText(phone);
                }
            }
        });
        tvVersion.setText("V" + getVersion(AppConstant.GLOBAL_CONTEXT));
    }

    @OnClick({R.id.rl_name, R.id.exit_login,R.id.fl_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                finish();
                break;
            case R.id.rl_name:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, ReplaceNameActivity.class));
                break;
            case R.id.exit_login:
                OkGo.<BaseBean>get(HttpConstant.Logout).execute(new JsonCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 0) {
                            showToast("退出登录成功");
                            SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
                            spUtils.putString(AppConstant.PHONE, "");
                            spUtils.putString(AppConstant.PASSWORD, "");
                        }
                    }
                });
                break;
        }
    }

    public static int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 1;
        }
    }

    public static String getVersion(Context context)//获取版本号
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "1.0.0";
        }
    }


}
