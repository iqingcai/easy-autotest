package com.qatest.demo.utils;

import lombok.extern.log4j.Log4j;

import java.io.*;
import java.util.Properties;

/*
 * 读取环境配置
 */

@Log4j
public class ConfigUtil {

    public static final String envConfig = FileUtil.getFilePath("configEnv.properties");
    public static final String configEnv = FileUtil.getFilePath(getProperties(envConfig, "env"));

    public static String getProperties(String key) {
        return getProperties(configEnv, key);
    }

    public static String getProperties(String propertyFile, String key) {
        String pattern = null;
        InputStream is = null;
        try {
            Properties prop = new Properties();
            is = new FileInputStream(propertyFile);
            prop.load(is);

            pattern = prop.getProperty(key).trim();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            log.error(String.format("get key %s from file %s error, please check! ", key, propertyFile));
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return pattern;
    }



}

