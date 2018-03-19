package com.yottabyte.cucumber;

import cucumber.api.SnippetType;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * cucumber驱动脚本
 * Created by A on 2017/4/1.
 */
@RunWith(CustomCucumberRunner.class)
@CucumberOptions(
        format = {"pretty", "json:target/cucumber.json"},
        features = {"src/test/resources"},
        monochrome = true,
        strict = true,
        dryRun= false,
        snippets = SnippetType.CAMELCASE,
        glue = {"com.yottabyte"},
        tags = {"@all"}
)
/**
 * @param projectName
 *可以根据实际项目名称更改该参数
 */
public class TestCucumberRunner{
    @AfterAll
    public static void generateReport(){
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");

        String buildNumber = "1";
        String projectName = "yottawebUITest";
        boolean runWithJenkins = true;
        boolean parallelTesting = false;

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optionally only if you need
        configuration.setParallelTesting(parallelTesting);
        configuration.setRunWithJenkins(runWithJenkins);
        configuration.setBuildNumber(buildNumber);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }
}

