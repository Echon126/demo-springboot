package com.example.demo.entity;

/**
 * @author admin
 * @date 2018-9-20 10:04
 */
public class Content {
    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Content(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
