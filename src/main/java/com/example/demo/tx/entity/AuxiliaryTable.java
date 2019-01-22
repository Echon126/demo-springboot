package com.example.demo.tx.entity;

import java.io.Serializable;

/**
 * @author admin
 * @date 2019-1-22 16:15
 */
public class AuxiliaryTable implements Serializable {

    private static final long serialVersionUID = -8522624955600593154L;
    private int id;
    private String city;
    private int code;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
