package com.connect.combine.bean;

import java.util.List;

public class TiXianRecordBean {


    /**
     * code : 0
     * msg :
     * data : {"withdraw":[{"create_time":"2019-12-01 16:20:53","amount":"100","amount_token_name":"USDT","token_name":"ABC","token_amount":"12000","token_price":"0.12","to_addr":"123123123","status":0},{"create_time":"2019-12-01 08:20:53","amount":"100","amount_token_name":"USDT","token_name":"ABC","token_amount":"1000","token_price":"0.1","to_addr":"123123124","status":0}]}
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
        private List<WithdrawBean> withdraw;

        public List<WithdrawBean> getWithdraw() {
            return withdraw;
        }

        public void setWithdraw(List<WithdrawBean> withdraw) {
            this.withdraw = withdraw;
        }

        public static class WithdrawBean {
            /**
             * create_time : 2019-12-01 16:20:53
             * amount : 100
             * amount_token_name : USDT
             * token_name : ABC
             * token_amount : 12000
             * token_price : 0.12
             * to_addr : 123123123
             * status : 0
             */

            private String create_time;
            private String amount;
            private String amount_token_name;
            private String token_name;
            private String token_amount;
            private String token_price;
            private String to_addr;
            private int status;

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

            public String getAmount_token_name() {
                return amount_token_name;
            }

            public void setAmount_token_name(String amount_token_name) {
                this.amount_token_name = amount_token_name;
            }

            public String getToken_name() {
                return token_name;
            }

            public void setToken_name(String token_name) {
                this.token_name = token_name;
            }

            public String getToken_amount() {
                return token_amount;
            }

            public void setToken_amount(String token_amount) {
                this.token_amount = token_amount;
            }

            public String getToken_price() {
                return token_price;
            }

            public void setToken_price(String token_price) {
                this.token_price = token_price;
            }

            public String getTo_addr() {
                return to_addr;
            }

            public void setTo_addr(String to_addr) {
                this.to_addr = to_addr;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
