package com.connect.combine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.connect.combine.R;
import com.connect.combine.bean.BaseBean;
import com.connect.combine.bean.ModifyNameRequestBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.HttpConstant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReplaceNameActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.tv_modify)
    TextView tvModify;
    @BindView(R.id.et_name)
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace_name);
        String name = getIntent().getStringExtra("name");
        etName.setText(name);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fl_close, R.id.tv_modify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                finish();
                break;
            case R.id.tv_modify:
                ModifyNameRequestBean modifyNameRequestBean = new ModifyNameRequestBean();
                modifyNameRequestBean.setName(etName.getText().toString().trim());
                String request = new Gson().toJson(modifyNameRequestBean);
                OkGo.<BaseBean>post(HttpConstant.modifyName).upJson(request).execute(new JsonCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response) {
                        if(response.body().getCode()==0)
                        {
                            showToast("昵称修改成功");
                        }
                    }
                });
                break;
        }
    }
}
