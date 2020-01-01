package com.connect.combine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.connect.base.utils.SPUtils;
import com.connect.combine.R;
import com.connect.combine.bean.BaseBean;
import com.connect.combine.bean.CurrentPriceResponseBean;
import com.connect.combine.bean.WithDrawRequestBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.constant.HttpConstant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetMoneyActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.exit)
    FrameLayout exit;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.record)
    TextView record;
    @BindView(R.id.tv_tixian)
    TextView tvTixian;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_name_ensure)
    EditText etNameEnsure;
    @BindView(R.id.et_value)
    EditText etValue;
    @BindView(R.id.et_value_ensure)
    EditText etValueEnsure;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_current_price)
    TextView currentPrice;
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.tv_get_verify_code)
    TextView tvGetVerifyCode;
    private String price = "0";
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_money);
        ButterKnife.bind(this);
        OkGo.<CurrentPriceResponseBean>get(HttpConstant.CurrentPrice).execute(new JsonCallback<CurrentPriceResponseBean>() {
            @Override
            public void onSuccess(Response<CurrentPriceResponseBean> response) {
                price = response.body().getData();
                currentPrice.setText(getString(R.string.dangqian_bijia, price));
            }
        });
        if (etName.getText().toString().length() > 0
                && etPassword.getText().toString().length() > 0
                && etValue.getText().toString().length() > 0
                && etNameEnsure.getText().toString().length() > 0
                && etValueEnsure.getText().toString().length() > 0
                && etVerifyCode.getText().toString().length() > 0
        ) {
            tvTixian.setTextColor(Color.WHITE);
            tvTixian.setBackgroundResource(R.drawable.shape_light_green_raduis_4);
            tvTixian.setClickable(true);
        } else {
            tvTixian.setTextColor(0x77ffffff);
            tvTixian.setBackgroundResource(R.drawable.shape_trans_light_green_raduis_4);
            tvTixian.setClickable(false);
        }

        etVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etVerifyCode.getText().toString().trim().length() != 0) {
                    tvTixian.setTextColor(Color.WHITE);
                    tvTixian.setBackgroundResource(R.drawable.shape_light_green_raduis_4);
                    tvTixian.setClickable(true);
                } else {
                    tvTixian.setTextColor(0x77ffffff);
                    tvTixian.setBackgroundResource(R.drawable.shape_trans_light_green_raduis_4);
                    tvTixian.setClickable(false);

                }
            }
        });

    }

    @OnClick({R.id.exit, R.id.title, R.id.record, R.id.tv_tixian, R.id.tv_get_verify_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.title:
                break;
            case R.id.record:
                Intent intent = new Intent(AppConstant.GLOBAL_CONTEXT, TitleRecycleViewActivity.class);
                intent.putExtra("mode", 2);
                startActivity(intent);
                break;
            case R.id.tv_tixian:
                if (!(etName.getText().toString().length() > 0
                        || etPassword.getText().toString().length() > 0
                        || etValue.getText().toString().length() > 0
                        || etNameEnsure.getText().toString().length() > 0
                        || etValueEnsure.getText().toString().length() > 0
                        || etVerifyCode.getText().toString().length() > 0)
                ) {
                    showToast("输入为空");
                    return;
                }
                if (!etName.getText().toString().trim().equals(etNameEnsure.getText().toString().trim())) {
                    showToast("两次输入的提现账户不一致");
                    return;
                }
                if (!etValue.getText().toString().trim().equals(etValueEnsure.getText().toString().trim())) {
                    showToast("两次输入的提现金额不一致");
                    return;
                }
                if (Double.parseDouble(price) <= 0) {
                    OkGo.<CurrentPriceResponseBean>get(HttpConstant.CurrentPrice).execute(new JsonCallback<CurrentPriceResponseBean>() {
                        @Override
                        public void onSuccess(Response<CurrentPriceResponseBean> response) {
                            price = response.body().getData();
                            currentPrice.setText(getString(R.string.dangqian_bijia, price));

                        }
                    });
                    return;
                }
                WithDrawRequestBean withDrawRequestBean = new WithDrawRequestBean();
                withDrawRequestBean.setTo_addr(etName.getText().toString().trim());
                withDrawRequestBean.setVerification_code(etVerifyCode.getText().toString().trim());
                withDrawRequestBean.setAmount(etValue.getText().toString().trim());
                withDrawRequestBean.setToken_price(price);
                String s = new Gson().toJson(withDrawRequestBean);
                OkGo.<BaseBean>post(HttpConstant.WithDraw).upJson(s).execute(new JsonCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        BaseBean body = response.body();
                        if (body.getCode() == 0) {
                            showToast(getString(R.string.tixian_chenggong));
                        } else {
                            showToast(response.body().getMsg());
                        }
                    }
                });
                break;
            case R.id.tv_get_verify_code:
                countDownTimer = new CountDownTimer(60 * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int time = (int) (millisUntilFinished / 1000);
                        tvGetVerifyCode.setText(time + "S");
                    }

                    @Override
                    public void onFinish() {
                        tvGetVerifyCode.setText(R.string.getVerifyCode);
                        tvGetVerifyCode.setClickable(true);
                    }
                };
                tvGetVerifyCode.setClickable(false);
                countDownTimer.start();
                SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
                OkGo.<BaseBean>get(HttpConstant.reqVC).params("phone", spUtils.getString(AppConstant.PHONE)).execute(new JsonCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        if (response.body().getCode() == 0) {

                        }
                    }
                });

                break;
        }
    }

}
