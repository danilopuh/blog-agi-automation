package br.com.agi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {

    private By articleTitleLinks = By.cssSelector("h1 a, h2 a, h3 a, .entry-title a, .post-title a, .ast-archive-title");
    private By noResultSelectors = By.xpath("//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZÁÂÃÀÇÉÊÍÓÔÕÚÜ', 'abcdefghijklmnopqrstuvwxyzáâãàçéêíóôõúü'), 'nada encontrado') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'nenhum resultado') or contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'no results') or contains(text(), 'Lamentamos')]");
    private By paginationNext = By.cssSelector("a.next, a.nextpostslink, .pagination a.next");

    public boolean hasResults() {
        // Primeiro verificar se há mensagem explícita de "sem resultados"
        if (hasNoResultsMessage()) {
            return false;
        }
        
        // Verificar se há elementos que claramente indicam resultados de pesquisa
        List<WebElement> titles = driver.findElements(articleTitleLinks);
        
        // Se encontrou títulos, verificar se são relevantes (não são mensagens de erro)
        if (!titles.isEmpty()) {
            for (WebElement title : titles) {
                String text = title.getText().toLowerCase();
                if (!text.isEmpty() && 
                    !text.contains("nada foi encontrado") && 
                    !text.contains("lamentamos") && 
                    !text.contains("no results") &&
                    !text.contains("página não encontrada") &&
                    !text.contains("404")) {
                    return true;
                }
            }
        }
        
        // Verificar se há artigos ou posts
        List<WebElement> articles = driver.findElements(By.cssSelector("article, .post, .entry, .blog-post"));
        if (!articles.isEmpty()) {
            for (WebElement article : articles) {
                String text = article.getText().toLowerCase();
                if (text.length() > 50 && // Artigo com conteúdo substancial
                    !text.contains("nada foi encontrado") && 
                    !text.contains("lamentamos") && 
                    !text.contains("no results")) {
                    return true;
                }
            }
        }
        
        return false;
    }

    public List<String> getTitles() {
        // Tentar encontrar links de títulos primeiro
        List<WebElement> links = driver.findElements(articleTitleLinks);
        if (!links.isEmpty()) {
            return links.stream()
                    .map(WebElement::getText)
                    .filter(text -> !text.trim().isEmpty())
                    .collect(Collectors.toList());
        }
        
        // Se não encontrar links, buscar por títulos em elementos de conteúdo
        List<WebElement> titles = driver.findElements(By.cssSelector("h1, h2, h3, .page-title, .archive-title"));
        return titles.stream()
                .map(WebElement::getText)
                .filter(text -> !text.trim().isEmpty() && 
                                !text.toLowerCase().contains("nada foi encontrado"))
                .collect(Collectors.toList());
    }

    public void openFirstResult() {
        List<WebElement> links = driver.findElements(articleTitleLinks);
        if (!links.isEmpty()) {
            WebElement firstLink = links.get(0);
            firstLink.click();
        } else {
            throw new RuntimeException("Nenhum link de artigo encontrado para clicar");
        }
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
