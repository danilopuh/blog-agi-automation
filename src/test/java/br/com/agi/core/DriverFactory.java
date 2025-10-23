package br.com.agi.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void create() {
        if (driver.get() == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver.set(new ChromeDriver(options));
        }
    }

    public static WebDriver get() {
        return driver.get();
    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public static String takeScreenshot(String testName) {
        if (driver.get() == null) {
            return null;
        }

        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver.get();
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = String.format("screenshot_%s_%s.png", testName.replaceAll("[^a-zA-Z0-9]", "_"), timestamp);
            
            // Criar diretório screenshots se não existir
            String screenshotDir = "target/screenshots";
            Files.createDirectories(Paths.get(screenshotDir));
            
            String filePath = screenshotDir + "/" + fileName;
            Files.copy(sourceFile.toPath(), Paths.get(filePath));
            
            return filePath;
        } catch (IOException e) {
            System.err.println("Erro ao capturar screenshot: " + e.getMessage());
            return null;
        }
    }
}
