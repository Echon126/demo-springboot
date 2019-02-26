package com.example.demo.container.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author admin
 * @date 2019-2-26 10:08
 */
public class RequestWrap extends HttpServletRequestWrapper {
    public RequestWrap(HttpServletRequest request) {
        super(request);
    }

    public String[] getParameterValues(String name) {
        String[] parames = super.getParameterValues(name);
        if (name.equals("ip") && isBlankArray(parames)) {
            return new String[]{super.getRemoteAddr()};
        }
        return parames;
    }

    public static boolean isBlankArray(Object[] obj) {
        if (obj == null || obj.length < 1) return true;
        return false;
    }
}
