package com.connect.combine.bean;

import java.util.List;

public class FriendBean {

    /**
     * code : 0
     * msg :
     * data : {"descendant":[{"phone":"139*****999","name":"999","level":1,"total_invest":"100","create_time":"2019-12-01 16:20:53"},{"phone":"139*****996","name":"996","level":2,"total_invest":"100","create_time":"2019-12-02 26:20:53"}]}
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
        private List<DescendantBean> descendant;

        public List<DescendantBean> getDescendant() {
            return descendant;
        }

        public void setDescendant(List<DescendantBean> descendant) {
            this.descendant = descendant;
        }

        public static class DescendantBean  {
            /**
             * phone : 139*****999
             * name : 999
             * level : 1
             * total_invest : 100
             * create_time : 2019-12-01 16:20:53
             */

            private String phone;
            private String name;
            private int level;
            private String total_invest;
            private String create_time;

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

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getTotal_invest() {
                return total_invest;
            }

            public void setTotal_invest(String total_invest) {
                this.total_invest = total_invest;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
