package com.qatest.demo.cucumber;

import com.qatest.demo.testng.TestNGLogListener;
import com.qatest.demo.testng.TestNGResultListener;
import com.qatest.demo.utils.FileUtil;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;
import org.testng.annotations.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@API(status = Status.STABLE)
@Listeners({TestNGLogListener.class, TestNGResultListener.class})
public abstract class AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;

    public AbstractTestNGCucumberTests() {
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = {"cucumber"}, description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    /**
     * Override the scenarios method to enable parallel execution
     * 支持scenarios级别并发
     * @return
     */
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return this.testNGCucumberRunner == null ? new Object[0][0] : this.testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }

        //生成报告
        File reportOutputDirectory = new File("target/cucumber-reports");

        List<String> jsonFiles = new ArrayList<>();

        //解析的json注意路径配置与runner对应一致
        jsonFiles.add(FileUtil.getProjectPath() + "/target/cucumber-reports/json/report.json");

        String projectName = "easy-autotest";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();

    }
}

