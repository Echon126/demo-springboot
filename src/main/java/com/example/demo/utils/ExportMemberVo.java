package com.example.demo.utils;

/**
 * @author admin
 * @date 2019-2-12 15:05
 */
public class ExportMemberVo {
    private String name;
    private Integer  gender;
    private String phone;
    private String idCard;
    private String bankNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender == 0 ? "男" : "女";
    }

    public void setGender(Integer  gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }
}
