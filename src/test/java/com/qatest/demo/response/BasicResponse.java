package com.qatest.demo.response;


import lombok.Data;

/*
 * 接口响应参数中的通用参数，根据项目适当调整
 */
@Data
public class BasicResponse {
	
	private int httpCode;
	private String Code;
	private String Message;
	private String RequestId;

}
