package com.connect.combine.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.connect.combine.R;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BasicActivity {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.fl_close)
    FrameLayout flClose;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.fl_web)
    FrameLayout flWeb;
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent((FrameLayout) flWeb, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go("http://www.jd.com");
    }

    @OnClick({R.id.fl_close, R.id.fl_web})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_close:
                finish();
                break;
            case R.id.fl_web:
                break;
        }
    }
}
