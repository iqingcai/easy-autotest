package com.qatest.demo.utils;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

/**
 * 全局配置变量
 */

@Log4j
@Getter
public class TestContext {

    private PropertiesUtil propertiesUtil = null;
    private static TestContext testContext = null;
    public static Config config = null;

    public void init() {

        if(config == null){
            String path = EnvConfigUtil.configEnv;
            log.info("配置文件路径： " + path);
            propertiesUtil = new PropertiesUtil(path);
            config = new Config();
            propertiesUtil.propertiesToObject(config, path);
        }

    }


    /**
     * 获取实例
     * @return
     * @throws Exception
     */
    public static TestContext getTestContext() {
        if (testContext == null) {
            try {
                testContext = new TestContext();
                testContext.init();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                log.error("没有找到全局变量文件 \n" + e.getMessage());
            }
        }
        return testContext;
    }


}
