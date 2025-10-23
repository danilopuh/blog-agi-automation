package br.com.agi.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"br.com.agi.steps", "br.com.agi.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/html",
                "json:target/cucumber-reports/json/Cucumber.json",
                "junit:target/cucumber-reports/junit/Cucumber.xml",
                "timeline:target/cucumber-reports/timeline",
                "rerun:target/cucumber-reports/rerun.txt"
        },
        tags = "not @ignore"
)
public class RunCucumberTest {
}
