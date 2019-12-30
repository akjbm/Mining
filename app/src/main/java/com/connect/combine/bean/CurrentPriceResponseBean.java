package com.connect.combine.bean;

public class CurrentPriceResponseBean {

    /**
     * price : 0.02184
     * time : 1577698207904
     * qty : 288.61
     * isBuyerMaker : false
     */

    private String price;
    private long time;
    private String qty;
    private boolean isBuyerMaker;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public boolean isIsBuyerMaker() {
        return isBuyerMaker;
    }

    public void setIsBuyerMaker(boolean isBuyerMaker) {
        this.isBuyerMaker = isBuyerMaker;
    }
}
