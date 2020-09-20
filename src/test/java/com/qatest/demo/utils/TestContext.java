package com.qatest.demo.utils;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

/**
 * 全局配置变量
 */

@Log4j
@Getter
public class TestContext {

    private static TestContext testContext = null;

    private ConfigUtil configUtil = null;

    private String url;

    public void init() {
        if(configUtil == null)
            configUtil = new ConfigUtil();
        url = ConfigUtil.getProperties("project.url");
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
