package com.qatest.demo.testng.testcase;

import com.qatest.demo.response.PersonResponse;
import com.qatest.demo.response.UserResponse;
import com.qatest.demo.retrofit.service.RetrofitManager;
import com.qatest.demo.testng.TestNGLogListener;
import com.qatest.demo.testng.TestNGResultListener;
import com.qatest.demo.utils.ConvertResponse;
import com.qatest.demo.utils.GetHeaders;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;

/**
 * TestNG example
 */
@Log4j
@Listeners({ TestNGLogListener.class, TestNGResultListener.class })
public class TestNGTestcase {

    UserResponse userResponse;

    @Test
    public void createUser() throws IOException {

        Response response = RetrofitManager.getCommonApi().createUser(GetHeaders.getHeaders()).execute();
        userResponse = ConvertResponse.convertResponse(response, UserResponse.class);
        log.info(userResponse.getUserId());
        Assert.assertEquals(userResponse.getUserId(), 1);
    }

    @Test(dependsOnMethods = "createUser")
    public void getInfo() throws IOException {

        Response response = RetrofitManager.getCommonApi().getInfo(GetHeaders.getHeaders(), userResponse.getUserId()).execute();

        PersonResponse personResponse = ConvertResponse.convertResponse(response, PersonResponse.class);

        log.info(personResponse.getPerson().getHometown().getCity());
        Assert.assertEquals(personResponse.getHttpCode(), 200);
        Assert.assertEquals(personResponse.getPerson().getName(), "pig");

    }

}
