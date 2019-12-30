package com.connect.combine.activity;

import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.connect.base.BaseActivity;
import com.connect.base.utils.BarUtils;
import com.connect.combine.R;

public class BasicActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
    }


    @Override
    protected void onResume() {
        int statusBarHeight = BarUtils.getStatusBarHeight(this);
        findViewById(R.id.status_bar).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,statusBarHeight));
        super.onResume();

    }

    public void showToast(CharSequence text) {
        Display display = getWindowManager().getDefaultDisplay();
        // 获取屏幕高度
        int height = display.getHeight();
        Toast toast = Toast.makeText(this,text, Toast.LENGTH_LONG);
        // 这里给了一个1/4屏幕高度的y轴偏移量
        toast.setGravity(Gravity.TOP, 0, height*3 / 4);
        toast.show();
    }
}
