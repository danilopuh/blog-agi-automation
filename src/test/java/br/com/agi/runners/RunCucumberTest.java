package br.com.agi.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "summary",
                "html:target/cucumber-reports.html"
        },
        features = "src/test/resources/features",
        glue = "br.com.agi.steps",
        tags = "@e2e",
        monochrome = true
)
public class RunCucumberTest { }
