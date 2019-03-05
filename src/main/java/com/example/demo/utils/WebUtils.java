package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author admin
 * @date 2019-3-1 14:16
 */
public class WebUtils {

    public static String getContent(String url, String oriEncoding, String targetEncoding) throws IOException {
        URL getUrl;
        String line;
        StringBuilder sb = null;
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            getUrl = new URL(url);
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestProperty("Accept", "text/xml");
            connection.setRequestProperty("User_Agent", "Mozilla/4.0 (compatible;MSIE 5.0;Windows NT;DigExt)");

            connection.connect();

            if (targetEncoding != null) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), targetEncoding));

            } else {
                reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
            }
            sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
            if (connection != null)
                connection.disconnect();
        }

        assert sb != null;
        return sb.toString();
    }


}










