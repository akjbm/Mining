package com.connect.combine.constant;

public class HttpConstant {
    public static String Login=AppConstant.HttpHost+"/api/v0/login";
    public static String Register=AppConstant.HttpHost+"/api/v0/register";
    public static String Logout=AppConstant.HttpHost+"/api/v0/logout";
    public static String resetPassword=AppConstant.HttpHost+"/api/v0/resetPasswd";
    public static String modifyName=AppConstant.HttpHost+"/api/v0/user/modify";
    public static String reqVC=AppConstant.HttpHost+"/api/v0/reqVC";
    public static String UserInfo=AppConstant.HttpHost+"/api/v0/user/info";
    public static String Reward = AppConstant.HttpHost + "/api/v0/user/reward";
    public static String CurrentPrice = AppConstant.HttpHost + "/api/v0/home/price";
    public static String DayInfo = AppConstant.HttpHost + "/api/v0/home/dayInfo";
    public static String MinePool = AppConstant.HttpHost + "/api/v0/home/miner/pool";
    public static String MineRank = AppConstant.HttpHost + "/api/v0/home/node/rank";
    public static String Ti_xian_Record=AppConstant.HttpHost+"/api/v0/user/withdraw";
    public static String Invest=AppConstant.HttpHost+"/api/v0/user/invest";
    public static String Meachine=AppConstant.HttpHost+"/api/v0/user/machine";
    public static String KuangyouInfo=AppConstant.HttpHost+"/api/v0/user/descendant";
    public static String BannerInfo=AppConstant.HttpHost+"/api/v0/manage/banner/query";
    public static String MeachineQuery = AppConstant.HttpHost + "/api/v0/manage/product/query";
    public static String NewsInfo=AppConstant.HttpHost+"/api/v0/manage/news/query";
    //public static String CurrentPrice="https://api.itiger.io/openapi/quote/v1/trades?symbol=VOLUMEUSDT&limit=1";
    public static String WithDraw=AppConstant.HttpHost+"/api/v0/trade/withdraw_itiger";
}
