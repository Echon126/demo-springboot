package com.example.demo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author admin
 * @date 2019-3-6 16:59
 */
public class TaxReturn {
    private String ssn;
    private int year;
    private String lastnName;

    public TaxReturn() {
    }

    public TaxReturn(String ssn, int year, String lastnName) {
        this.ssn = ssn;
        this.year = year;
        this.lastnName = lastnName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLastnName() {
        return lastnName;
    }

    public void setLastnName(String lastnName) {
        this.lastnName = lastnName;
    }

   /* public String toString() {
        return new ToStringBuilder(this)
                .append("ssn", ssn)
                .append("year", year)
                .append("lastName", lastnName)
                .toString();

    }*/

    @Override
    public String toString() {
        return "TaxReturn{" +
                "ssn='" + ssn + '\'' +
                ", year=" + year +
                ", lastnName='" + lastnName + '\'' +
                '}';
    }


}
