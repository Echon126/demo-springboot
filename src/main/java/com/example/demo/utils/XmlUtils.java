package com.example.demo.utils;


import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;

import java.util.*;

/**
 * @author admin
 * @date 2019-2-28 14:23
 */
public class XmlUtils {

    /**
     * map转化成xml数据
     *
     * @param map
     * @param rootElement
     * @return
     */
    public static String mapXmlBody(Map<String, Object> map, String rootElement) {
        Document doc = DocumentHelper.createDocument();
        Element body = DocumentHelper.createElement(rootElement);
        doc.add(body);
        buildMapXmlBody(body, map);
        return doc.asXML();

    }

    private static void buildMapXmlBody(Element body, Map<String, Object> vo) {
        if (vo == null) return;
        Iterator<String> it = vo.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            if (StringUtils.isNotEmpty(key)) {
                Object obj = vo.get(key);
                Element element = DocumentHelper.createElement(key);
                if (obj != null) {
                    if (obj instanceof java.lang.String) {
                        element.setText(obj.toString());
                    } else {
                        if (obj instanceof java.lang.Character || obj instanceof java.lang.Boolean
                                || obj instanceof java.lang.Number || obj instanceof java.math.BigInteger
                                || obj instanceof java.math.BigDecimal) {
                            Attribute attr = DocumentHelper.createAttribute(element, "type",
                                    obj.getClass().getCanonicalName());
                            element.add(attr);
                            element.setText(String.valueOf(obj));
                        } else if (obj instanceof java.util.Map) {
                            Attribute attr = DocumentHelper.createAttribute(element, "type",
                                    java.util.Map.class.getCanonicalName());
                            element.add(attr);
                            buildMapXmlBody(element, (Map<String, Object>) obj);
                        } else {

                        }
                    }
                    body.add(element);
                }
            }
        }
    }

    /**
     * xml转化为map
     *
     * @param xml
     * @param rootElement
     * @return
     * @throws DocumentException
     */
    private static Map xmlToMap(String xml, String rootElement) throws DocumentException {
        Document doc = DocumentHelper.parseText(xml);
        Element body = doc.getRootElement();
        Map map = buildXmlBodyMap(body);
        return map;
    }

    private static Map buildXmlBodyMap(Element body) {
        Map map = new HashMap();
        if (body == null) return map;

        List<Element> elements = body.elements();
        for (Element element : elements) {
            String key = element.getName();
            if (StringUtils.isNotEmpty(key)) {
                String type = element.attributeValue("type", "java.lang.String");
                String text = element.getText().trim();
                Object value = null;
                if (java.lang.String.class.getCanonicalName().equals(type)) {
                    value = text;
                } else if (java.lang.Character.class.getCanonicalName().equals(type)) {
                    value = new java.lang.Character(text.charAt(0));
                } else if (java.lang.Boolean.class.getCanonicalName().equals(type)) {
                    value = new java.lang.Boolean(text);
                } else if (java.lang.Short.class.getCanonicalName().equals(type)) {
                    value = new java.lang.Short(text);
                } else if (java.lang.Integer.class.getCanonicalName().equals(type)) {
                    value = java.lang.Integer.parseInt(text);
                } else if (java.lang.Long.class.getCanonicalName().equals(type)) {
                    value = java.lang.Long.parseLong(text);
                } else if (java.lang.Float.class.getCanonicalName().equals(type)) {
                    value = java.lang.Float.parseFloat(text);
                } else if (java.lang.Double.class.getCanonicalName().equals(type)) {
                    value = java.lang.Double.parseDouble(text);
                } else if (java.math.BigInteger.class.getCanonicalName().equals(type)) {
                    value = new java.math.BigInteger(text);
                } else if (java.math.BigDecimal.class.getCanonicalName().equals(type)) {
                    value = new java.math.BigDecimal(text);
                } else if (java.util.Map.class.getCanonicalName().equals(type)) {
                    System.out.println(java.util.Map.class.getCanonicalName());
                    value = buildXmlBodyMap(element);
                } else {
                }
                map.put(key, value);
            }
        }
        return map;
    }


    public static void main(String[] args) throws DocumentException {
       /* Map<String, Object> map = new LinkedHashMap<>();
        Map<String, Object> inermap = new HashMap<String, Object>();
        inermap.put("name", "zhansna");
        inermap.put("sex", "man");
        inermap.put("age", 100);
        inermap.put("phone", "13829292929292");

        map.put("name", "zhansna");
        map.put("sex", "man");
        map.put("age", 100);
        map.put("persons", inermap);
        map.put("phone", "13829292929292");

        String str = mapXmlBody(map, "Root");
        System.out.println(str);*/

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Root><name>zhansna</name><sex>man</sex><age type=\"java.lang.Integer\">100</age><persons type=\"java.util.Map\"><phone>13829292929292</phone><sex>man</sex><name>zhansna</name><age type=\"java.lang.Integer\">100</age></persons><phone>13829292929292</phone></Root>";
        Map mapXMl = xmlToMap(xml, "Root");
    }

}
















