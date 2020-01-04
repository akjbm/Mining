package com.connect.combine.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.connect.base.BaseFragment;
import com.connect.base.utils.BarUtils;
import com.connect.base.utils.SPUtils;
import com.connect.base.utils.SizeUtils;
import com.connect.combine.R;
import com.connect.combine.activity.BuyMeachineActivity;
import com.connect.combine.activity.LoginActivity;
import com.connect.combine.activity.MainActivity;
import com.connect.combine.activity.MineActivity;
import com.connect.combine.activity.MyMeachineActivity;
import com.connect.combine.activity.TitleRecycleViewActivity;
import com.connect.combine.bean.BannerResponseBean;
import com.connect.combine.bean.BaseBean;
import com.connect.combine.bean.DayInfoResponseBean;
import com.connect.combine.bean.NewsResponseBean;
import com.connect.combine.bean.RegisterBean;
import com.connect.combine.bean.UserInfoBackBean;
import com.connect.combine.callback.JsonCallback;
import com.connect.combine.constant.AppConstant;
import com.connect.combine.constant.HttpConstant;
import com.connect.combine.utils.BitmapUtils;
import com.connect.combine.utils.GlideImageLoader;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.status_bar)
    FrameLayout statusBar;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.mv_news)
    MarqueeView mvNews;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.ll_gonggao)
    LinearLayout ll_gonggao;
    @BindView(R.id.tv_meachine_details)
    TextView tvMeachineDetails;
    @BindView(R.id.tv_buy_meachine)
    TextView tvBuyMeachine;

    List<BannerResponseBean.DataBean.RawsBean> raws;
    List<NewsResponseBean.DataBean.RawsBean> totalNews;
    @BindView(R.id.ll_switch)
    LinearLayout llSwitch;
    @BindView(R.id.iv_language)
    ImageView iv_language;
    @BindView(R.id.tv_suanli)
    TextView tv_suanli;
    @BindView(R.id.tv_total_output)
    TextView tv_total_output;
    @BindView(R.id.tv_today_output)
    TextView tvTodayOutput;
    @BindView(R.id.tv_today_sell)
    TextView tvTodaySell;
    @BindView(R.id.tv_today_value)
    TextView tvTodayValue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        int statusBarHeight = BarUtils.getStatusBarHeight(getContext());
        view.findViewById(R.id.status_bar).setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight));
        SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
        String string = spUtils.getString("Language", "en");
        if (string.equals("en")) {
            iv_language.setImageResource(R.mipmap.en);
        } else {
            iv_language.setImageResource(R.mipmap.zh);
        }

        return view;

    }

    private void jumpToLogin() {
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("xx", "onResume");
        SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
        String phone = spUtils.getString(AppConstant.PHONE);
        String password = spUtils.getString(AppConstant.PASSWORD);
        BitmapUtils.check();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
            jumpToLogin();
        } else {
            RegisterBean registerBean = new RegisterBean();
            registerBean.setPhone(phone);
            registerBean.setPassword(password);
            String registerString = new Gson().toJson(registerBean);
            OkGo.<BaseBean>post(HttpConstant.Login).upJson(registerString).execute(new JsonCallback<BaseBean>() {
                @Override
                public void onSuccess(Response<BaseBean> response) {
                    int code = response.body().getCode();
                    Log.e("code", code + "");
                    if (code == 0) {
                        spUtils.putBoolean(AppConstant.IS_LOGIN, true);
                        spUtils.putString(AppConstant.PHONE, phone);
                        spUtils.putString(AppConstant.PASSWORD, password);
                        OkGo.<UserInfoBackBean>get(HttpConstant.UserInfo).execute(new JsonCallback<UserInfoBackBean>() {
                            @Override
                            public void onSuccess(Response<UserInfoBackBean> response) {
                                UserInfoBackBean body = response.body();
                                if (body.getCode() == 0) {
                                    String phone = body.getData().getUser().getPhone();
                                    String name = body.getData().getUser().getName();
                                    AppConstant.InviteCode = body.getData().getUser().getInvite_code();
                                    UserInfoBackBean.DataBean.WalletBean wallet = body.getData().getWallet();
                                    EventBus.getDefault().post(wallet);
                                    String value = TextUtils.isEmpty(wallet.getMy_power()) ? "0" : wallet.getMy_power();
                                    tv_suanli.setText(value + getString(R.string.suanli));
                                    try {
                                        tv_total_output.setText(Integer.parseInt(wallet.getMy_output()) + getString(R.string.suanli));
                                    } catch (NumberFormatException e) {
                                        tv_total_output.setText("0" + getString(R.string.suanli));
                                    }
                                    try {
                                        tvTodayOutput.setText(Integer.parseInt(wallet.getToday_output()) + getString(R.string.suanli));
                                    } catch (NumberFormatException e) {
                                        tvTodayOutput.setText("0" + getString(R.string.suanli));
                                    }

                                }
                            }
                        });


                    } else {
                        jumpToLogin();
                    }
                }
            });

        }
        OkGo.<BannerResponseBean>get(HttpConstant.BannerInfo).execute(new JsonCallback<BannerResponseBean>() {
            @Override
            public void onSuccess(Response<BannerResponseBean> response) {
                List<String> images = new ArrayList<>();
                List<String> click = new ArrayList<>();
                BannerResponseBean body = response.body();
                raws = body.getData().getRaws();
                for (BannerResponseBean.DataBean.RawsBean bean : raws) {
                    images.add(bean.getImage());
                    click.add(bean.getUrl());
                }
                banner.setImages(images).setImageLoader(new GlideImageLoader(HomeFragment.this.getActivity()));
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                });
                banner.start();
            }
        });
        OkGo.<NewsResponseBean>get(HttpConstant.NewsInfo).execute(new JsonCallback<NewsResponseBean>() {
            @Override
            public void onSuccess(Response<NewsResponseBean> response) {
                List<String> news = new ArrayList<>();
                NewsResponseBean body = response.body();
                totalNews = body.getData().getRaws();
                for (NewsResponseBean.DataBean.RawsBean bean : totalNews) {
                    String title = bean.getTitle();
                    news.add(title);
                }
                mvNews.startWithList(news);
                mvNews.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position, TextView textView) {
                        Intent intent = new Intent(AppConstant.GLOBAL_CONTEXT, TitleRecycleViewActivity.class);
                        intent.putExtra("mode", 0);
                        String json = new Gson().toJson(totalNews);
                        intent.putExtra("data", json);
                        startActivity(intent);
                    }
                });
            }
        });


        OkGo.<DayInfoResponseBean>get(HttpConstant.DayInfo).execute(new JsonCallback<DayInfoResponseBean>() {
            @Override
            public void onSuccess(Response<DayInfoResponseBean> response) {
                DayInfoResponseBean body = response.body();
                if (body != null && body.getCode() == 0) {
                    DayInfoResponseBean.DataBean data = body.getData();
                    if (data != null) {
                        String dayInvest = data.getDayInvest();
                        if (TextUtils.isEmpty(dayInvest)) {
                            dayInvest = "0";
                        }
                        tvTodaySell.setText(dayInvest + " " + getString(R.string.suanli));
                        String dayReward = data.getDayReward();
                        if (TextUtils.isEmpty(dayReward)) {
                            dayReward = "0";
                        }
                        tvTodayValue.setText(dayReward + " " + getString(R.string.suanli));
                    }
                }
            }
        });
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            Log.e("xx", "Visible");


        } else {
            Log.e("xx", "Hide");
        }
    }


    @OnClick({R.id.iv_me, R.id.ll_gonggao, R.id.tv_meachine_details, R.id.tv_buy_meachine, R.id.ll_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_me:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, MineActivity.class));
//                DialogPlus dialogPlus = DialogPlus.newDialog(getContext()).setContentHolder(new ViewHolder(R.layout.hongbao_layout)).setContentBackgroundResource(R.color.transp).setGravity(Gravity.CENTER).create();
//                dialogPlus.show();
                break;
            case R.id.ll_gonggao:
                Intent intent = new Intent(AppConstant.GLOBAL_CONTEXT, TitleRecycleViewActivity.class);
                intent.putExtra("mode", 0);
                String json = new Gson().toJson(totalNews);
                intent.putExtra("data", json);
                startActivity(intent);
                break;
            case R.id.tv_meachine_details:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, MyMeachineActivity.class));
                break;
            case R.id.tv_buy_meachine:
                startActivity(new Intent(AppConstant.GLOBAL_CONTEXT, BuyMeachineActivity.class));
                break;
            case R.id.ll_switch:
                PopupWindow popupWindow = new PopupWindow(getContext());
                View inflate = View.inflate(getContext(), R.layout.popu_language_layout, null);
                popupWindow.setContentView(inflate);
                inflate.findViewById(R.id.ll_en).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
                        String sta = spUtils.getString("Language", "en");
                        if ("zh".equals(sta)) {
                            Locale myLocale = new Locale("en");
                            Resources res = getResources();
                            DisplayMetrics dm = res.getDisplayMetrics();
                            Configuration conf = res.getConfiguration();
                            conf.locale = myLocale;
                            res.updateConfiguration(conf, dm);
                            spUtils.putString("Language", "en");
                            //getActivity().recreate();
                            Intent intent = new Intent(AppConstant.GLOBAL_CONTEXT, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            getActivity().startActivity(intent);
                            AppConstant.CURRENT_LANGUAGE = 1;

                        }
                    }
                });
                inflate.findViewById(R.id.ll_china).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
                        String sta = spUtils.getString("Language", "en");
                        if ("en".equals(sta)) {
                            Locale myLocale = new Locale("zh");
                            Resources res = getResources();
                            DisplayMetrics dm = res.getDisplayMetrics();
                            Configuration conf = res.getConfiguration();
                            conf.locale = myLocale;
                            res.updateConfiguration(conf, dm);
                            spUtils.putString("Language", "zh");
                            //getActivity().recreate();
                            Intent intent = new Intent(AppConstant.GLOBAL_CONTEXT, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            getActivity().startActivity(intent);
                            AppConstant.CURRENT_LANGUAGE = 0;
                        }
                    }
                });

                popupWindow.setWidth(SizeUtils.dp2px(getContext(), 82));
                popupWindow.setHeight(SizeUtils.dp2px(getContext(), 70));
                // 设置背景
                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_white_radius_4));
                // 外部点击事件
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(llSwitch, -10, -10);
                break;

        }


    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("onPause", "onPause");
        OkGo.getInstance().cancelAll();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("onStop", "onStop");
        banner.stopAutoPlay();
        // OkGo.getInstance().cancelAll();;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("onDestroy", "onDestroy");
        // OkGo.getInstance().cancelAll();
    }

    public void changeAppLanguage() {
        //这是SharedPreferences工具类，用于保存设置，代码很简单，自己实现吧
        SPUtils spUtils = new SPUtils(AppConstant.GLOBAL_CONTEXT, AppConstant.SP);
        String sta = spUtils.getString("Language", "zh");
        Locale myLocale = new Locale(sta);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

}
