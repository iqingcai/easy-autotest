package com.qatest.demo.cucumber.steps;

import com.qatest.demo.cucumber.framework.Framework;
import com.qatest.demo.utils.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;

@Log4j
public class MyStepdefs {

    Framework framework;

    public MyStepdefs( Framework framework ) {
        this.framework = framework;
    }

    @When("参数 {int} 和 {int}")
    public void 参数A和B(int a , int b) throws InterruptedException {

        log.info(framework.getA());
        framework.setA(a);
        log.info(framework.getA());
        log.info(framework.getB());
        Thread.sleep(1000);
        log.info(TestContext.getTestContext().getUrl());
    }

    @Then("给一个参数 {int} 和 {int}")
    public void 给一个参数A和B(int a, int b) {

        log.info(framework.getA());
        log.info(framework.getB());
    }

    @When("使用错误用户名 {} 和密码 {} 来登录")
    public void login(String userName, String password) {

        log.info("用户名： " + userName + ", 密码： " + password);
    }


    @Then("不正确的用户名或密码登录失败")
    public void loginError() {

        log.info("登录失败");

    }


}
