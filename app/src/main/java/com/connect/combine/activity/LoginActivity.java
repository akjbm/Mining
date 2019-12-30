package com.connect.combine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.connect.base.utils.EncryptUtils;
import com.connect.base.utils.SPUtils;
import com.connect.combine.R;
import com.connect.combine.bean.BaseBean;
import com.connect.combine.bean.RegisterBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.constant.HttpConstant;
import com.connect.combine.utils.BitmapUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.pre_phone)
    TextView pre_phone;
    @BindView(R.id.iv_delete)
    ImageView iv_delete;
    int error_count;
    private SPUtils spUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //int statusBarHeight = BarUtils.getStatusBarHeight(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if(AppConstant.CURRENT_LANGUAGE==0)
        {
            pre_phone.setText("+86");
        }else if(AppConstant.CURRENT_LANGUAGE==1)
        {
            pre_phone.setText("+1");
        }
        BitmapUtils.check();

        spUtils = new SPUtils(getApplicationContext(), AppConstant.SP);
        //  findViewById(R.id.status_bar).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,statusBarHeight));
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.toString().length();

                //删除数字
                if (count == 0) {
                    if (length == 4) {
                        etPhone.setText(s.subSequence(0, 3));
                    }
                    if (length == 9) {
                        etPhone.setText(s.subSequence(0, 8));
                    }
                }
                //添加数字
                if (count == 1) {
                    if (length == 4) {
                        String part1 = s.subSequence(0, 3).toString();
                        String part2 = s.subSequence(3, length).toString();
                        etPhone.setText(part1 + " " + part2);
                    }
                    if (length == 9) {
                        String part1 = s.subSequence(0, 8).toString();
                        String part2 = s.subSequence(8, length).toString();
                        etPhone.setText(part1 + " " + part2);
                    }
                }
            }


            @Override
            public void afterTextChanged(Editable s) {
                //将光标移动到末尾
                etPhone.setSelection(etPhone.getText().toString().length());
                //处理s
                if(etPhone.getText().toString().length()>0){
                    iv_delete.setVisibility(View.VISIBLE);
                }else
                {
                    iv_delete.setVisibility(View.INVISIBLE);
                }
                setLogin();
            }

        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setLogin();
            }
        });

    }

    private void setLogin() {
        if (etPhone.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0) {
            tvLogin.setTextColor(Color.WHITE);
            tvLogin.setBackgroundResource(R.drawable.shape_light_green_raduis_4);
            tvLogin.setClickable(true);
        } else {
            tvLogin.setTextColor(0x77ffffff);
            tvLogin.setBackgroundResource(R.drawable.shape_trans_light_green_raduis_4);
            tvLogin.setClickable(false);
        }
    }


    @OnClick({R.id.tv_login, R.id.tv_forget_password, R.id.register,R.id.iv_delete,R.id.fl_close})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.fl_close:
                finish();
                break;
            case R.id.tv_login:
                String phone = etPhone.getText().toString().trim().replace(" ", "");
                String password = etPassword.getText().toString();
                String passwordMD5 = EncryptUtils.encryptMD5ToString(password);
                RegisterBean registerBean = new RegisterBean();
                registerBean.setPhone(phone);
                registerBean.setPassword(passwordMD5);
                String registerString = new Gson().toJson(registerBean);
                if(System.currentTimeMillis()-spUtils.getFloat(AppConstant.SP, 0L)<3600*1000)
                {
                    showToast(getString(R.string.jinzhidenglu1xiaoshi));
                    return;
                }
                OkGo.<BaseBean>post(HttpConstant.Login).upJson(registerString).execute(new JsonCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        int code = response.body().getCode();
                        Log.e("code", code+"");
                        if(code==0){
                            spUtils.putBoolean(AppConstant.IS_LOGIN, true);
                            spUtils.putString(AppConstant.PHONE, phone);
                            spUtils.putString(AppConstant.PASSWORD, passwordMD5);
                            finish();
                        }else {
                            error_count++;
                            if(error_count<4)
                            {
                                showToast(getString(R.string.zhanghaomimacuowu));
                            }else if(error_count==4)
                            {
                                showToast(getString(R.string.cuowu4ci));
                            }else{
                                showToast(getString(R.string.jinzhidenglu1xiaoshi));
                                spUtils.putLong(AppConstant.ERROR_TIME, System.currentTimeMillis());
                            }
                        }
                    }
                });
                break;
            case R.id.tv_forget_password:
                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                intent.putExtra("Mode", 0);
                startActivity(intent);
                break;
            case R.id.register:
                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                intent.putExtra("Mode", 1);
                startActivity(intent);
                break;
            case R.id.iv_delete:
                etPhone.setText("");
                break;
        }
    }
}
