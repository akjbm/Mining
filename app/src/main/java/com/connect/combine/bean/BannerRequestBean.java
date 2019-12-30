package com.connect.combine.bean;

public class BannerRequestBean {

    /**
     * lang : zh
     * status : 0
     * id : 1
     * page : 1
     * size : 10
     * ascOrder : false
     * orderBy : create_time
     */

    private String lang;
    private int status;
    private int id;
    private int page;
    private int size;
    private boolean ascOrder;
    private String orderBy;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isAscOrder() {
        return ascOrder;
    }

    public void setAscOrder(boolean ascOrder) {
        this.ascOrder = ascOrder;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
