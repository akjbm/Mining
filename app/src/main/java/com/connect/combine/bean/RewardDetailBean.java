package com.connect.combine.bean;

import java.util.List;

public class RewardDetailBean {

    /**
     * code : 0
     * msg :
     * data : {"reward":[{"create_time":"2019-12-01 16:20:53","amount":"100","type":3,"type_name":"矿机挖矿"},{"create_time":"2019-12-01 10:20:53","amount":"1000","type":1,"type_name":"邀请矿友"}]}
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
        private List<RewardBean> reward;

        public List<RewardBean> getReward() {
            return reward;
        }

        public void setReward(List<RewardBean> reward) {
            this.reward = reward;
        }

        public static class RewardBean {
            /**
             * create_time : 2019-12-01 16:20:53
             * amount : 100
             * type : 3
             * type_name : 矿机挖矿
             */

            private String create_time;
            private String amount;
            private int type;
            private String type_name;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }
        }
    }
}
