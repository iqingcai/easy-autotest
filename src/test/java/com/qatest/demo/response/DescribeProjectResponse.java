package com.qatest.demo.response;

import com.qatest.demo.model.Project;
import lombok.Data;

@Data
public class DescribeProjectResponse extends BasicResponse {

    private Project project;

}
