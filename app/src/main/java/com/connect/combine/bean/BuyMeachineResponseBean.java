package com.connect.combine.bean;

import java.util.List;

public class BuyMeachineResponseBean {

    /**
     * code : 0
     * msg :
     * data : {"total":1,"raws":[{"id":35,"lang":"en","name":"test11","model":"testModel1","capacity":"2343.434","power":"2234.232","position":"南极洲1","desc":"ceshi chanpin4","image":"suiyi2","status":1,"create_time":"2019-12-15T23:27:10+08:00"}]}
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
         * total : 1
         * raws : [{"id":35,"lang":"en","name":"test11","model":"testModel1","capacity":"2343.434","power":"2234.232","position":"南极洲1","desc":"ceshi chanpin4","image":"suiyi2","status":1,"create_time":"2019-12-15T23:27:10+08:00"}]
         */

        private int total;
        private List<RawsBean> raws;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RawsBean> getRaws() {
            return raws;
        }

        public void setRaws(List<RawsBean> raws) {
            this.raws = raws;
        }

        public static class RawsBean {
            /**
             * id : 35
             * lang : en
             * name : test11
             * model : testModel1
             * capacity : 2343.434
             * power : 2234.232
             * position : 南极洲1
             * desc : ceshi chanpin4
             * image : suiyi2
             * status : 1
             * create_time : 2019-12-15T23:27:10+08:00
             */

            private int id;
            private String lang;
            private String name;
            private String model;
            private String capacity;
            private String power;
            private String position;
            private String desc;
            private String image;
            private int status;
            private String create_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getLang() {
                return lang;
            }

            public void setLang(String lang) {
                this.lang = lang;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
