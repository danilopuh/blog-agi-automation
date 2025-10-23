package br.com.agi.pages;

import org.openqa.selenium.By;

public class ArticlePage extends BasePage {
    private By articleTitle = By.cssSelector("h1, .entry-title");

    public String getTitle() {
        return waitVisible(articleTitle).getText();
    }
}
