package com.connect.combine.activity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.fragment.app.Fragment;

import com.connect.base.BaseActivity;
import com.connect.base.ui.CustomViewPager;
import com.connect.base.utils.SPUtils;
import com.connect.combine.R;
import com.connect.combine.adapter.HomeViewPagerAdapter;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.fragment.FriendFragment;
import com.connect.combine.fragment.HomeFragment;
import com.connect.combine.fragment.WalleFragment;
import com.connect.combine.ui.BottomBarLayout;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends BaseActivity {

    private CustomViewPager cvp_home;
    private HomeFragment homeFragment;
    private WalleFragment walleFragment;
    private FriendFragment friendFragment;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private BottomBarLayout bbl_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
        String sta = spUtils.getString("Language", "en");
        Locale myLocale = new Locale(sta);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        setContentView(R.layout.activity_main);
        cvp_home = findViewById(R.id.cvp_home);
        bbl_home = findViewById(R.id.bbl_home);
        homeFragment = new HomeFragment();
        walleFragment = new WalleFragment();
        friendFragment = new FriendFragment();
        fragments.add(homeFragment);
        fragments.add(friendFragment);
        fragments.add(walleFragment);
        cvp_home.setAdapter(new HomeViewPagerAdapter(getSupportFragmentManager(),fragments));
        cvp_home.setOffscreenPageLimit(2);
        bbl_home.setViewPager(cvp_home);
      //  startActivity(new Intent(this,LoginActivity.class));
    }
}
