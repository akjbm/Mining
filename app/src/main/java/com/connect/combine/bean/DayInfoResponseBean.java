package com.connect.combine.bean;

public class DayInfoResponseBean {


    /**
     * code : 0
     * msg :
     * data : {"dayInvest":"","dayReward":""}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dayInvest :
         * dayReward :
         */

        private String dayInvest;
        private String dayReward;

        public String getDayInvest() {
            return dayInvest;
        }

        public void setDayInvest(String dayInvest) {
            this.dayInvest = dayInvest;
        }

        public String getDayReward() {
            return dayReward;
        }

        public void setDayReward(String dayReward) {
            this.dayReward = dayReward;
        }
    }
}
