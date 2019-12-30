package com.connect.combine.bean;

import java.util.List;

public class MeachineResponseBean {

    /**
     * code : 0
     * msg :
     * data : {"total":2,"machine":[{"images":"https://123.img,https://234.img","model":"蚂蚁矿机S17+ 240-Aa","capacity":"53 TH/s","power":"39.5 J/TH","position":"联合矿业西伯利亚1区矿场"},{"images":"https://1234.img,https://2345.img","model":"蚂蚁矿机S20+ 350-Xc","capacity":"120 TH/s","power":"60 J/TH","position":"联合矿业西伯利亚3区矿场"}]}
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
         * total : 2
         * machine : [{"images":"https://123.img,https://234.img","model":"蚂蚁矿机S17+ 240-Aa","capacity":"53 TH/s","power":"39.5 J/TH","position":"联合矿业西伯利亚1区矿场"},{"images":"https://1234.img,https://2345.img","model":"蚂蚁矿机S20+ 350-Xc","capacity":"120 TH/s","power":"60 J/TH","position":"联合矿业西伯利亚3区矿场"}]
         */

        private int total;
        private List<MachineBean> machine;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<MachineBean> getMachine() {
            return machine;
        }

        public void setMachine(List<MachineBean> machine) {
            this.machine = machine;
        }

        public static class MachineBean {
            /**
             * images : https://123.img,https://234.img
             * model : 蚂蚁矿机S17+ 240-Aa
             * capacity : 53 TH/s
             * power : 39.5 J/TH
             * position : 联合矿业西伯利亚1区矿场
             */

            private String images;
            private String model;
            private String capacity;
            private String power;
            private String position;

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getCapacity() {
                return capacity;
            }

            public void setCapacity(String capacity) {
                this.capacity = capacity;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }
        }
    }
}
