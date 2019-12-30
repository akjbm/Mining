package com.connect.combine.bean;

import java.util.List;

public class ModifyNameRequestBean {

    /**
     * verification_code :
     * password :
     * name :
     * address : [{"name":"itiger","addr":"123123123","addr_type":1},{"name":"eth","addr":"0x1231","addr_type":1}]
     */

    private String verification_code;
    private String password;
    private String name;
    private List<AddressBean> address;

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AddressBean> getAddress() {
        return address;
    }

    public void setAddress(List<AddressBean> address) {
        this.address = address;
    }

    public static class AddressBean {
        /**
         * name : itiger
         * addr : 123123123
         * addr_type : 1
         */

        private String name;
        private String addr;
        private int addr_type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getAddr_type() {
            return addr_type;
        }

        public void setAddr_type(int addr_type) {
            this.addr_type = addr_type;
        }
    }
}
