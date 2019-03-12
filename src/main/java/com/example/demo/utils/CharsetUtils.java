package com.example.demo.utils;

import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author admin
 * @date 2019-3-11 14:52
 */
public class CharsetUtils {
    private final static Logger logger = LoggerFactory.getLogger(CharsetUtils.class);

    public static String getEncodeing(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            UniversalDetector detector = new UniversalDetector(null);
            byte[] buf = new byte[4096];
            int nread;
            while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            String encoding = detector.getDetectedCharset();
            detector.reset();
            return encoding;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
