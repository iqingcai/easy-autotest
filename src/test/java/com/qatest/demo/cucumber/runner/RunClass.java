package com.qatest.demo.cucumber.runner;

import com.qatest.demo.cucumber.AbstractTestNGCucumberTests;
import com.qatest.demo.utils.TestContext;
import io.cucumber.testng.CucumberOptions;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author hzzhouxiaoqing
 */

@Log4j
@Test
@CucumberOptions(
		features={ //指定需要运行的features文件
				"src/test/resources/features/example.feature",
				"src/test/resources/features/login.feature"
		},
		glue={"com.qatest.demo.cucumber.steps"},
		plugin = {
				"pretty:target/cucumber-reports/STDOUT",
				"html:target/cucumber-reports/index.html",
				"json:target/cucumber-reports/json/report.json"
				},

//		tags = "@debug or @demo",
		monochrome = true
		)

public class RunClass extends AbstractTestNGCucumberTests {

	@BeforeClass(alwaysRun = true)
	@Override
	public void setUpClass() {
		super.setUpClass();
		TestContext.getTestContext();
	}

	/**
	 * 重写支持单线程运行
	 * @return
	 */
	@Override
	@DataProvider
	public Object[][] scenarios() {
		return super.scenarios();
	}

}