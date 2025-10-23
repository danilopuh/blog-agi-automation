package br.com.agi.hooks;

import br.com.agi.core.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class ScreenshotHooks {

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = DriverFactory.takeScreenshot(scenario.getName());
            if (screenshotPath != null) {
                System.out.println("Screenshot capturado para cenário falho: " + screenshotPath);
                
                // Adicionar screenshot ao relatório Cucumber
                try {
                    byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) DriverFactory.get())
                            .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Screenshot");
                } catch (Exception e) {
                    System.err.println("Erro ao anexar screenshot ao relatório: " + e.getMessage());
                }
            }
        }
    }
}