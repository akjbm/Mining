package com.connect.combine.bean;

import java.util.List;

public class RankListResponseBean {

    /**
     * code : 0
     * msg :
     * data : [{"rank":1,"name":"昵称","minerID":"2312","minerAddress":"2312","capacity":1000,"missCap":2000}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rank : 1
         * name : 昵称
         * minerID : 2312
         * minerAddress : 2312
         * capacity : 1000
         * missCap : 2000
         */

        private int rank;
        private String name;
        private String minerID;
        private String minerAddress;
        private int capacity;
        private int missCap;

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMinerID() {
            return minerID;
        }

        public void setMinerID(String minerID) {
            this.minerID = minerID;
        }

        public String getMinerAddress() {
            return minerAddress;
        }

        public void setMinerAddress(String minerAddress) {
            this.minerAddress = minerAddress;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getMissCap() {
            return missCap;
        }

        public void setMissCap(int missCap) {
            this.missCap = missCap;
        }
    }
}
