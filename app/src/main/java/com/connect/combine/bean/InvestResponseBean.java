package com.connect.combine.bean;

import java.util.List;

public class InvestResponseBean {

    /**
     * code : 0
     * msg :
     * data : {"invest":[{"create_time":"2019-12-01 16:20:53","amount":"100","profit":"200","token_name":"erc20USDT","trx_hash":"0x123456","status":0},{"create_time":"2019-12-01 10:20:53","amount":"1000","profit":"2800","token_name":"erc20USDT","trx_hash":"0x123456","status":0}]}
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
        private List<InvestBean> invest;

        public List<InvestBean> getInvest() {
            return invest;
        }

        public void setInvest(List<InvestBean> invest) {
            this.invest = invest;
        }

        public static class InvestBean {
            /**
             * create_time : 2019-12-01 16:20:53
             * amount : 100
             * profit : 200
             * token_name : erc20USDT
             * trx_hash : 0x123456
             * status : 0
             */

            private String create_time;
            private String amount;
            private String profit;
            private String token_name;
            private String trx_hash;
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

            public String getProfit() {
                return profit;
            }

            public void setProfit(String profit) {
                this.profit = profit;
            }

            public String getToken_name() {
                return token_name;
            }

            public void setToken_name(String token_name) {
                this.token_name = token_name;
            }

            public String getTrx_hash() {
                return trx_hash;
            }

            public void setTrx_hash(String trx_hash) {
                this.trx_hash = trx_hash;
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
