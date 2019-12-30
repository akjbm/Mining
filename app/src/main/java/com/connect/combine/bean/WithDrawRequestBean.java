package com.connect.combine.bean;

public class WithDrawRequestBean {

    /**
     * password :
     * verification_code :
     * amount :
     * token_price :
     * token_amount :
     * to_addr :
     */

    private String password;
    private String verification_code;
    private String amount;
    private String token_price;
    private String token_amount;
    private String to_addr;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getToken_price() {
        return token_price;
    }

    public void setToken_price(String token_price) {
        this.token_price = token_price;
    }

    public String getToken_amount() {
        return token_amount;
    }

    public void setToken_amount(String token_amount) {
        this.token_amount = token_amount;
    }

    public String getTo_addr() {
        return to_addr;
    }

    public void setTo_addr(String to_addr) {
        this.to_addr = to_addr;
    }
}
