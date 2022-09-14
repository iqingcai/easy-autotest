package com.qatest.demo.cucumber.steps;

import com.qatest.demo.utils.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.extern.log4j.Log4j;

@Log4j
public class Hooks {

    /**
     * Hooks
     * @Before 每个Scenario之前都会运行
     */
    @Before()
    public void before(){

        log.info("before....每个Scenario之前都会运行");
        TestContext.getTestContext();

    }

    @After()
    public void after(){

        log.info("after....每个Scenario结束之后都会运行");

    }
}
