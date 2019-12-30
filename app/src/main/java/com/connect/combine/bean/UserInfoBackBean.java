package com.connect.combine.bean;

public class UserInfoBackBean {


    /**
     * code : 0
     * msg :
     * data : {"user":{"phone":"139*****999","name":"999","invite_code":"abc999"},"wallet":{"my_power":"100","my_output":"100","today_output":"100","children_cnt":"100","descendant_cnt":"100","my_reward":"100","managed_invest":"100"},"investAddr":{"type_name":"ERC20","addr":"123123"},"withdrawAddr":{"type_name":"iTiger","addr":"0x1234"}}
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
         * user : {"phone":"139*****999","name":"999","invite_code":"abc999"}
         * wallet : {"my_power":"100","my_output":"100","today_output":"100","children_cnt":"100","descendant_cnt":"100","my_reward":"100","managed_invest":"100"}
         * investAddr : {"type_name":"ERC20","addr":"123123"}
         * withdrawAddr : {"type_name":"iTiger","addr":"0x1234"}
         */

        private UserBean user;
        private WalletBean wallet;
        private InvestAddrBean investAddr;
        private WithdrawAddrBean withdrawAddr;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public WalletBean getWallet() {
            return wallet;
        }

        public void setWallet(WalletBean wallet) {
            this.wallet = wallet;
        }

        public InvestAddrBean getInvestAddr() {
            return investAddr;
        }

        public void setInvestAddr(InvestAddrBean investAddr) {
            this.investAddr = investAddr;
        }

        public WithdrawAddrBean getWithdrawAddr() {
            return withdrawAddr;
        }

        public void setWithdrawAddr(WithdrawAddrBean withdrawAddr) {
            this.withdrawAddr = withdrawAddr;
        }

        public static class UserBean {
            /**
             * phone : 139*****999
             * name : 999
             * invite_code : abc999
             */

            private String phone;
            private String name;
            private String invite_code;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInvite_code() {
                return invite_code;
            }

            public void setInvite_code(String invite_code) {
                this.invite_code = invite_code;
            }
        }

        public static class WalletBean {
            /**
             * my_power : 100
             * my_output : 100
             * today_output : 100
             * children_cnt : 100
             * descendant_cnt : 100
             * my_reward : 100
             * managed_invest : 100
             */

            private String my_power;
            private String my_output;
            private String today_output;
            private String children_cnt;
            private String descendant_cnt;
            private String my_reward;
            private String managed_invest;
            private String withdrawed_amount;

            public String getWithdrawed_amount() {
                return withdrawed_amount;
            }

            public void setWithdrawed_amount(String withdrawed_amount) {
                this.withdrawed_amount = withdrawed_amount;
            }

            public String getWithdrawable_amount() {
                return withdrawable_amount;
            }

            public void setWithdrawable_amount(String withdrawable_amount) {
                this.withdrawable_amount = withdrawable_amount;
            }

            private String withdrawable_amount;

            public String getMy_power() {
                return my_power;
            }

            public void setMy_power(String my_power) {
                this.my_power = my_power;
            }

            public String getMy_output() {
                return my_output;
            }

            public void setMy_output(String my_output) {
                this.my_output = my_output;
            }

            public String getToday_output() {
                return today_output;
            }

            public void setToday_output(String today_output) {
                this.today_output = today_output;
            }

            public String getChildren_cnt() {
                return children_cnt;
            }

            public void setChildren_cnt(String children_cnt) {
                this.children_cnt = children_cnt;
            }

            public String getDescendant_cnt() {
                return descendant_cnt;
            }

            public void setDescendant_cnt(String descendant_cnt) {
                this.descendant_cnt = descendant_cnt;
            }

            public String getMy_reward() {
                return my_reward;
            }

            public void setMy_reward(String my_reward) {
                this.my_reward = my_reward;
            }

            public String getManaged_invest() {
                return managed_invest;
            }

            public void setManaged_invest(String managed_invest) {
                this.managed_invest = managed_invest;
            }
        }

        public static class InvestAddrBean {
            /**
             * type_name : ERC20
             * addr : 123123
             */

            private String type_name;
            private String addr;

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }
        }

        public static class WithdrawAddrBean {
            /**
             * type_name : iTiger
             * addr : 0x1234
             */

            private String type_name;
            private String addr;

            public String getType_name() {
                return type_name;
            }

            public void setType_name(String type_name) {
                this.type_name = type_name;
            }

            public String getAddr() {
                return addr;
            }

            public void setAddr(String addr) {
                this.addr = addr;
            }
        }
    }
}
