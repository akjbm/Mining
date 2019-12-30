package com.connect.combine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.connect.base.utils.EncryptUtils;
import com.connect.base.utils.RegexUtils;
import com.connect.base.utils.SPUtils;
import com.connect.combine.R;
import com.connect.combine.bean.BaseBean;
import com.connect.combine.bean.RegisterBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.constant.HttpConstant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.CookieJar;

public class RegisterActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_invite_num)
    EditText etInviteNum;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_get_verify_code)
    TextView tv_get_verify_code;
    @BindView(R.id.tv_pre_phone)
    TextView tv_pre_phone;
    @BindView(R.id.tv_yaoqingma)
    TextView tvYaoqingma;
    @BindView(R.id.ll_yaoqingma)
    LinearLayout llYaoqingma;
    @BindView(R.id.view_divider)
    View view_divider;
    private CountDownTimer countDownTimer;
    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  int statusBarHeight = BarUtils.getStatusBarHeight(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mode = getIntent().getIntExtra("Mode", 1);
        if (AppConstant.CURRENT_LANGUAGE == 0) {
            tv_pre_phone.setText("+86");
        } else if (AppConstant.CURRENT_LANGUAGE == 1) {
            tv_pre_phone.setText("+1");
        }
        if (mode == 1) {
            setRegisterLayout();
        } else if (mode == 0) {
            tvTitle.setText(R.string.checkPassword);
            tvYaoqingma.setVisibility(View.GONE);
            llYaoqingma.setVisibility(View.GONE);
            view_divider.setVisibility(View.GONE);
            tvRegister.setText(R.string.changePassword);
            setChangePasswordLayout();
        }

        // findViewById(R.id.status_bar).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,statusBarHeight));
    }

    private void setRegisterLayout() {
        tvRegister.setClickable(false);
        etInviteNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setStatusRegister();

            }
        });
        etVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setStatusRegister();
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
                setStatusRegister();
            }
        });
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
                setStatusRegister();
            }

        });
    }

    private void setChangePasswordLayout() {
        tvRegister.setClickable(false);
        etVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setStatusRegister1();
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
                setStatusRegister1();
            }
        });
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
                setStatusRegister1();
            }

        });
    }

    private void setStatusRegister1() {
        if (etPhone.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0 &&
                etVerifyCode.getText().toString().length() > 0 ) {
            tvRegister.setTextColor(Color.WHITE);
            tvRegister.setBackgroundResource(R.drawable.shape_light_green_raduis_4);
            tvRegister.setClickable(true);
        } else {
            tvRegister.setTextColor(0x77ffffff);
            tvRegister.setBackgroundResource(R.drawable.shape_trans_light_green_raduis_4);
            tvRegister.setClickable(false);
        }
    }

    private void setStatusRegister() {
        if (etPhone.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0 &&
                etVerifyCode.getText().toString().length() > 0 && etInviteNum.getText().toString().length() > 0) {
            tvRegister.setTextColor(Color.WHITE);
            tvRegister.setBackgroundResource(R.drawable.shape_light_green_raduis_4);
            tvRegister.setClickable(true);
        } else {
            tvRegister.setTextColor(0x77ffffff);
            tvRegister.setBackgroundResource(R.drawable.shape_trans_light_green_raduis_4);
            tvRegister.setClickable(false);
        }
    }

    @OnClick({R.id.fl_close, R.id.tv_register, R.id.tv_get_verify_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                finish();
                break;
            case R.id.tv_register:
                String trim = etPassword.getText().toString().trim();
                String passwordMD5 = EncryptUtils.encryptMD5ToString(trim);
                if(mode==1) {
                    RegisterBean registerBean = new RegisterBean();
                    registerBean.setPhone(etPhone.getText().toString().trim().replace(" ", ""));
                    registerBean.setPassword(passwordMD5);
                    registerBean.setInvite_code(etInviteNum.getText().toString().trim());
                    registerBean.setVerification_code(etVerifyCode.getText().toString().trim());
                    String register = new Gson().toJson(registerBean);
                    OkGo.<BaseBean>post(HttpConstant.Register).upJson(register).execute(new JsonCallback<BaseBean>() {
                        @Override
                        public void onSuccess(Response<BaseBean> response) {
                            if (response.body().getCode() == 0) {
                                showToast("注册成功");
                                finish();
                            } else {
                                showToast(response.body().getMsg());
                            }
                        }

                        @Override
                        public void onError(Response<BaseBean> response) {
                            super.onError(response);

                        }
                    });
                }else if(mode==0){
                    RegisterBean registerBean = new RegisterBean();
                    registerBean.setPhone(etPhone.getText().toString().trim().replace(" ", ""));
                    registerBean.setPassword(passwordMD5);
                    registerBean.setVerification_code(etVerifyCode.getText().toString().trim());
                    String register = new Gson().toJson(registerBean);
                    OkGo.<BaseBean>post(HttpConstant.resetPassword).upJson(register).execute(new JsonCallback<BaseBean>() {
                        @Override
                        public void onSuccess(Response<BaseBean> response) {
                            if (response.body().getCode() == 0) {
                                showToast("修改密码成功");
                                SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
                                spUtils.putString(AppConstant.PASSWORD, passwordMD5);
                            } else {
                                showToast(response.body().getMsg());
                            }
                        }
                    });
                }
                break;
            case R.id.tv_get_verify_code:
                if (!RegexUtils.isMobileSimple(etPhone.getText().toString().trim().replace(" ", ""))) {
                    showToast("手机号输入错误");
                    return;
                }
                countDownTimer = new CountDownTimer(60 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int time = (int) (millisUntilFinished / 1000);
                        tv_get_verify_code.setText(time + "S");
                    }

                    @Override
                    public void onFinish() {
                        tv_get_verify_code.setText(R.string.getVerifyCode);
                        tv_get_verify_code.setClickable(true);
                    }
                };
                tv_get_verify_code.setClickable(false);
                countDownTimer.start();
                OkGo.<BaseBean>get(HttpConstant.reqVC).params("phone", etPhone.getText().toString().trim().replace(" ", "")).execute(new JsonCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        if (response.body().getCode() == 0) {

                        }
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }



}
