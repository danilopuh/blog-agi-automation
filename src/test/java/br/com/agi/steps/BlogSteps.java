package br.com.agi.steps;

import br.com.agi.core.DriverFactory;
import br.com.agi.pages.ArticlePage;
import br.com.agi.pages.HomePage;
import br.com.agi.pages.SearchPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import java.time.Instant;

public class BlogSteps {

    HomePage home;
    SearchPage search;
    ArticlePage article;
    String lastTerm;
    private Instant searchStartTime;
    private Duration searchDuration;
    private Exception caughtException;

    @Before
    public void setUp(){
        DriverFactory.create();
        home = new HomePage();
        search = new SearchPage();
        article = new ArticlePage();
        caughtException = null;
    }

    @After
    public void tearDown(){
        DriverFactory.quit();
    }

    @Dado("que estou na página inicial do blog do Agi")
    public void que_estou_na_pagina_inicial() {
        home.openHome();
    }

    @Dado("que navego para uma URL inválida do blog")
    public void que_navego_para_uma_url_invalida_do_blog() {
        try {
            WebDriver driver = DriverFactory.get();
            driver.get("https://blog.agi.com.br/pagina-inexistente-404");
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @Quando("realizo uma pesquisa por {string}")
    public void realizo_uma_pesquisa_por(String termo) {
        lastTerm = termo;
        home.searchFor(termo);
    }

    @Quando("realizo uma pesquisa por {string} e meço o tempo de resposta")
    public void realizo_uma_pesquisa_por_e_meco_o_tempo_de_resposta(String termo) {
        lastTerm = termo;
        searchStartTime = Instant.now();
        home.searchFor(termo);
        searchDuration = Duration.between(searchStartTime, Instant.now());
    }

    @Entao("são exibidos artigos relacionados à palavra pesquisada")
    public void sao_exibidos_artigos_relacionados() {
        Assert.assertTrue("Esperava resultados na pesquisa", search.hasResults());
    }

    @Entao("não deve haver resultados")
    public void nao_deve_haver_resultados() {
        // Pode mostrar mensagem de "Nada encontrado" ou simplesmente zero cards
        boolean noMsg = search.hasNoResultsMessage();
        boolean noCards = !search.hasResults();
        Assert.assertTrue("Deveria não haver resultados", noMsg || noCards);
    }

    @Entao("o resultado deve aparecer em menos de 5 segundos")
    public void o_resultado_deve_aparecer_em_menos_de_5_segundos() {
        Assert.assertNotNull("Tempo de resposta não foi medido", searchDuration);
        Assert.assertTrue("Tempo de resposta foi de " + searchDuration.getSeconds() + " segundos, mas deveria ser menor que 5",
            searchDuration.getSeconds() < 5);
    }

    @Entao("deve ser exibida uma mensagem de erro apropriada")
    public void deve_ser_exibida_uma_mensagem_de_erro_apropriada() {
        WebDriver driver = DriverFactory.get();
        boolean hasError = caughtException != null || 
                          driver.getTitle().contains("404") || 
                          driver.getTitle().contains("Não encontrado") ||
                          driver.getPageSource().contains("404") ||
                          driver.getPageSource().contains("Página não encontrada");
        
        Assert.assertTrue("Nenhum erro foi detectado para URL inválida", hasError);
    }

    @Quando("abro o primeiro resultado da lista")
    public void abro_o_primeiro_resultado_da_lista() {
        search.openFirstResult();
    }

    @Entao("o artigo é aberto corretamente")
    public void o_artigo_e_aberto_corretamente() {
        String title = article.getTitle();
        Assert.assertNotNull("Título do artigo não deveria ser nulo", title);
        Assert.assertTrue("Título do artigo deveria ter conteúdo", title.trim().length() > 0);
    }

    @Entao("os títulos retornados contêm o termo pesquisado, ignorando maiúsculas e acentos")
    public void os_titulos_contem_o_termo_ignorar_case_acentos() {
        String normalizedTerm = normalize(lastTerm);
        boolean anyMatch = search.getTitles().stream()
                .map(this::normalize)
                .anyMatch(t -> t.contains(normalizedTerm));
        Assert.assertTrue("Ao menos um título deveria conter o termo pesquisado", anyMatch);
    }

    @Entao("pelo menos um título contém algum dos termos pesquisados")
    public void pelo_menos_um_titulo_contem_algum_dos_termos_pesquisados() {
        String[] termos = lastTerm.split(" ");
        boolean anyMatch = false;
        
        for (String termo : termos) {
            String normalizedTerm = normalize(termo.trim());
            if (!normalizedTerm.isEmpty()) {
                boolean termMatch = search.getTitles().stream()
                        .map(this::normalize)
                        .anyMatch(t -> t.contains(normalizedTerm));
                if (termMatch) {
                    anyMatch = true;
                    break;
                }
            }
        }
        
        Assert.assertTrue("Ao menos um título deveria conter pelo menos um dos termos pesquisados", anyMatch);
    }

    private String normalize(String s){
        String n = java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD);
        n = n.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return n.toLowerCase();
    }
}
