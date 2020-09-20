package com.qatest.demo.cucumber.steps;

import com.qatest.demo.cucumber.framework.Framework;
import com.qatest.demo.response.PersonResponse;
import com.qatest.demo.response.UserResponse;
import com.qatest.demo.retrofit.service.RetrofitManager;
import com.qatest.demo.utils.ConvertResponse;
import com.qatest.demo.utils.GetHeaders;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import retrofit2.Response;

import java.io.IOException;

@Log4j
public class UserTestStepdefs {

    Framework framework;

    /**
     * Sharing state between steps in Cucumber-JVM using PicoContainer
     * @param framework
     */
    public UserTestStepdefs( Framework framework ) {
        this.framework = framework;
    }

    @When("创建一个用户")
    public void createUser() throws IOException {

        Response response = RetrofitManager.getCommonApi().createUser(GetHeaders.getHeaders()).execute();
        UserResponse userResponse = ConvertResponse.convertResponse(response, UserResponse.class);

        framework.setUserResponse(userResponse);
        log.info(framework.getUserResponse().getUserId());
        Assert.assertEquals(framework.getUserResponse().getUserId(), 1);
    }


    @Then("获取用户信息")
    public void getInfo() throws IOException {

        Assert.assertEquals(framework.getUserResponse().getUserId(), 1); //判断不同steps之间变量是否共享

        Response response = RetrofitManager.getCommonApi().getInfo(GetHeaders.getHeaders(),
                framework.getUserResponse().getUserId()).execute();
        PersonResponse personResponse = ConvertResponse.convertResponse(response, PersonResponse.class);

        framework.setPersonResponse(personResponse);
        log.info(personResponse.getPerson().getHometown().getCity());
        Assert.assertEquals(personResponse.getHttpCode(), 200);
        Assert.assertEquals(personResponse.getPerson().getAge(), 18, "模拟断言失败场景"); //断言失败，目的是查看失败的报告展示
    }
}
