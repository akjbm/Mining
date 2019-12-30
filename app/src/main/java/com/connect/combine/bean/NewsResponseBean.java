package com.connect.combine.bean;

import java.io.Serializable;
import java.util.List;

public class NewsResponseBean {

    /**
     * code : 0
     * msg :
     * data : {"total":1,"raws":[{"id":1,"title":"这是一个标题","lang":"zh","desc":"这是一个描述","content":"这是一个内容","image":"banner1.png","url":"http://bannerpage/1.html","create_time":"2019-01-12 12:32:22","start_time":"2019-01-12 12:32:22","expired_time":"2020-01-12 12:32:22","weight":1,"status":0}]}
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
         * raws : [{"id":1,"title":"这是一个标题","lang":"zh","desc":"这是一个描述","content":"这是一个内容","image":"banner1.png","url":"http://bannerpage/1.html","create_time":"2019-01-12 12:32:22","start_time":"2019-01-12 12:32:22","expired_time":"2020-01-12 12:32:22","weight":1,"status":0}]
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

        public static class RawsBean implements Serializable {
            /**
             * id : 1
             * title : 这是一个标题
             * lang : zh
             * desc : 这是一个描述
             * content : 这是一个内容
             * image : banner1.png
             * url : http://bannerpage/1.html
             * create_time : 2019-01-12 12:32:22
             * start_time : 2019-01-12 12:32:22
             * expired_time : 2020-01-12 12:32:22
             * weight : 1
             * status : 0
             */

            private int id;
            private String title;
            private String lang;
            private String desc;
            private String content;
            private String image;
            private String url;
            private String create_time;
            private String start_time;
            private String expired_time;
            private int weight;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLang() {
                return lang;
            }

            public void setLang(String lang) {
                this.lang = lang;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getExpired_time() {
                return expired_time;
            }

            public void setExpired_time(String expired_time) {
                this.expired_time = expired_time;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
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
