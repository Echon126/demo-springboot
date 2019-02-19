package com.example.demo.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author admin
 * @date 2019-2-18 14:10
 */
public class FileDown {

    public static void downloadWrite(HttpServletRequest request, HttpServletResponse response, String filename) {

        filename.replace("file:///", "");
        File file = new File(filename);
        response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + filename.substring(filename.lastIndexOf("/") + 1));
        response.setContentLength((int) file.length());
        response.setCharacterEncoding("utf-8");
        try (InputStream is = new BufferedInputStream(new FileInputStream(file));
             OutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            out.write(buffer);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}








