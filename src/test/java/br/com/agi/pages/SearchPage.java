package br.com.agi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {

    private By articleCards = By.cssSelector("article, .post, .blog-post");
    private By articleTitleLinks = By.cssSelector("h2 a, h3 a, .entry-title a, .post-title a");
    private By noResultSelectors = By.xpath("//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÂÃÀÇÉÊÍÓÔÕÚÜ', 'abcdefghijklmnopqrstuvwxyzáâãàçéêíóôõúü'), 'nada encontrado') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'nenhum resultado') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'no results')]");
    private By paginationNext = By.cssSelector("a.next, a.nextpostslink, .pagination a.next");

    public boolean hasResults() {
        List<WebElement> cards = waitAllVisible(articleCards);
        return cards != null && !cards.isEmpty();
    }

    public List<String> getTitles() {
        List<WebElement> links = driver.findElements(articleTitleLinks);
        return links.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void openFirstResult() {
        WebElement first = waitVisible(articleTitleLinks);
        first.click();
    }

    public boolean hasNoResultsMessage() {
        return !driver.findElements(noResultSelectors).isEmpty();
    }

    public boolean goToNextPageIfExists() {
        List<WebElement> nexts = driver.findElements(paginationNext);
        if (!nexts.isEmpty()) {
            nexts.get(0).click();
            return true;
        }
        return false;
    }
}
