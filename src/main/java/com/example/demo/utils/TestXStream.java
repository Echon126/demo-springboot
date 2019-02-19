package com.example.demo.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author admin
 * @date 2019-2-13 15:10
 */
public class TestXStream {
    private static final String PERSON_XML = "<PERSON>\n" +
            "  <Name>熔岩</Name>\n" +
            "  <age>27</age>\n" +
            "  <PROFILE>\n" +
            "    <Job>软件project师</Job>\n" +
            "    <tel>13512129933</tel>\n" +
            "    <remark>备注说明</remark>\n" +
            "  </PROFILE>\n" +
            "  <ADDLIST>\n" +
            "    <ADDRESS Zipcode=\"450001\">\n" +
            "      <Add>郑州市经三路</Add>\n" +
            "    </ADDRESS>\n" +
            "    <ADDRESS Zipcode=\"710002\">\n" +
            "      <Add>西安市雁塔路</Add>\n" +
            "    </ADDRESS>\n" +
            "  </ADDLIST>\n" +
            "</PERSON>";
    ;

    private static final String PROFILE_XML = "<PROFILE>\n" +
            "    <Job>软件project师</Job>\n" +
            "    <tel>13512129933</tel>\n" +
            "    <remark>备注说明</remark>\n" +
            " </PROFILE>";

    private static final String ADDRESS_XML = "<ADDRESS Zipcode=\"710002\">\n" +
            "      <Add>西安市雁塔路</Add>\n" +
            " </ADDRESS>";
    ;


    public static void main(String[] args) {
        GetYesterDay();
    }

    public static  void GetYesterDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        System.out.println(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,1);
        String date1= sdf.format(calendar.getTime());
        System.out.println(date1);


    }
}
