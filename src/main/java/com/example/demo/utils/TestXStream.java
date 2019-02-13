package com.example.demo.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
        // 转换装配
        XStream xStream = new XStream(new DomDriver());
        System.out.println(xStream.fromXML(PERSON_XML).toString());
        System.out.println(xStream.fromXML(PERSON_XML).toString());
        System.out.println(xStream.fromXML(PERSON_XML).toString());
    }
}
