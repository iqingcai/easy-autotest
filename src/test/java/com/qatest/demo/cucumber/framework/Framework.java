package com.qatest.demo.cucumber.framework;

import com.qatest.demo.response.PersonResponse;
import com.qatest.demo.response.UserResponse;
import com.qatest.demo.utils.TestConstants;
import com.qatest.demo.utils.TestContext;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.util.Map;

@Log4j
@Data
public class Framework {

    private Map<String, Object> params;

    private PersonResponse personResponse;

    private UserResponse userResponse;

    private String test = "aaaaa";

    private String random = TestConstants.getRandomString(5);

    private int a;

    private int b;

}
