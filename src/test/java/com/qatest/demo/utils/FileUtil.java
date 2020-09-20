package com.qatest.demo.utils;


import lombok.extern.log4j.Log4j;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

@Log4j
public class FileUtil {

    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    public String getRealPath() {

        String realPath = null;
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();

        try {
            realPath = URLDecoder.decode(url.getPath(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (realPath.endsWith(".jar")) {
            realPath = realPath.substring(0, realPath.lastIndexOf("/") + 1);
        }else {
            realPath = Thread.currentThread().getContextClassLoader().getResource("").getFile();
        }

        return realPath;
    }

    public static String getFilePath(String fileName) {

        FileUtil fileUtil = new FileUtil();
        File file = new File(fileUtil.getRealPath() + "/" + fileName);

        if (!file.exists()) {
            log.error("文件不存在，请确认: " + file.toString());
        }
        return file.toString();
    }
}
