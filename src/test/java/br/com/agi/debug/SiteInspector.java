package br.com.agi.debug;

import br.com.agi.core.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SiteInspector {
    public static void main(String[] args) {
        DriverFactory.create();
        WebDriver driver = DriverFactory.get();
        
        try {
            System.out.println("=== INSPECIONANDO BLOG DO AGI ===");
            
            // Navegar para página de busca
            driver.get("https://blogdoagi.com.br/?s=cartao");
            Thread.sleep(3000); // Aguardar carregamento
            
            System.out.println("Página carregada: " + driver.getTitle());
            System.out.println("URL atual: " + driver.getCurrentUrl());
            
            // Verificar se há elementos de artigo com diferentes seletores
            String[] selectors = {
                "article", 
                ".post", 
                ".blog-post",
                ".entry",
                ".card",
                ".result",
                ".search-result",
                "[class*='post']",
                "[class*='article']",
                "[class*='entry']",
                "div[class*='card']",
                "h1, h2, h3, h4"
            };
            
            for (String selector : selectors) {
                List<WebElement> elements = driver.findElements(By.cssSelector(selector));
                System.out.println(String.format("Seletor '%s': %d elementos encontrados", selector, elements.size()));
                
                if (elements.size() > 0 && elements.size() < 20) {
                    for (int i = 0; i < Math.min(3, elements.size()); i++) {
                        WebElement el = elements.get(i);
                        String text = el.getText().trim();
                        if (text.length() > 0) {
                            System.out.println("  -> " + text.substring(0, Math.min(100, text.length())));
                        }
                    }
                }
            }
            
            // Verificar título da página
            System.out.println("\n=== HTML TITLES ===");
            List<WebElement> titles = driver.findElements(By.cssSelector("h1, h2, h3"));
            for (WebElement title : titles) {
                System.out.println("Title: " + title.getText());
            }
            
            // Ver código fonte básico
            System.out.println("\n=== PAGE SOURCE SAMPLE ===");
            String pageSource = driver.getPageSource();
            if (pageSource.contains("cartão") || pageSource.contains("cartao")) {
                System.out.println("✓ Página contém termo de busca");
            } else {
                System.out.println("✗ Página NÃO contém termo de busca");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DriverFactory.quit();
        }
    }
}