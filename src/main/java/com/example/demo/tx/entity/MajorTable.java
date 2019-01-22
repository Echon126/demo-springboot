package com.example.demo.tx.entity;

import java.io.Serializable;

/**
 * @author admin
 * @date 2019-1-22 16:16
 */
public class MajorTable implements Serializable {

    private static final long serialVersionUID = -791959428825612899L;
    private int id;
    private String name;
    private String sex;
    private int city;
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
