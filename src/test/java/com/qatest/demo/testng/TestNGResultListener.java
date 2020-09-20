package com.qatest.demo.testng;

import lombok.extern.log4j.Log4j;
import org.slf4j.MDC;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Log4j
public class TestNGResultListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
    	log.info("---------------------- " + tr.getName() + " Fail ----------------------");
 
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();		
    	tr.getThrowable().printStackTrace(new PrintStream(baos));
    	String exception = baos.toString();	
    	log.error(exception);

    	//如果你想堆栈的每一行都打印MDC信息，则启用以下代码
    	
//    	StackTraceElement[] element = tr.getThrowable().getStackTrace();
//    	for(int i =0; i<element.length; i++)
//    		log.info(element[i]);
    	
    	
    	MDC.remove("logTraceUUID");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
    	log.info("---------------------- " + tr.getName() + " Skipped ----------------------");
		MDC.remove("logTraceUUID");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
    	log.info("---------------------- " + tr.getName() + " Success ----------------------");
		MDC.remove("logTraceUUID");
    }
}