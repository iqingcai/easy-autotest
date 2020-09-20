package com.qatest.demo.testng;

import lombok.extern.log4j.Log4j;
import org.slf4j.MDC;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.util.UUID;

@Log4j
public class TestNGLogListener implements IInvokedMethodListener {

	@Override
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		log.info("---------------------- " + arg0.getTestMethod().getMethodName() + " End ----------------------");
		//这边没有clear掉MDC的原因是因为TestNGResultListener在该方法后执行
	}

	@Override
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
    	MDC.put("logTraceUUID", UUID.randomUUID().toString());
    	log.info("---------------------- " + arg0.getTestMethod().getMethodName() + " Start ---------------------- ");
	}
}