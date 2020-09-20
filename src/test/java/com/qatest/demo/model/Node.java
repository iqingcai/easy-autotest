package com.qatest.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Node {

    private String projectId;
    private String masterNodeIp;
    private int masterNodePort;
    private String slaveNodeIp;
    private int slaveNodePort;

}
