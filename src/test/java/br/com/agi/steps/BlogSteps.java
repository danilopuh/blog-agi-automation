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

public class BlogSteps {

    HomePage home;
    SearchPage search;
    ArticlePage article;
    String lastTerm;

    @Before
    public void setUp(){
        DriverFactory.create();
        home = new HomePage();
        search = new SearchPage();
        article = new ArticlePage();
    }

    @After
    public void tearDown(){
        DriverFactory.quit();
    }

    @Dado("que estou na página inicial do blog do Agi")
    public void que_estou_na_pagina_inicial() {
        home.openHome();
    }

    @Quando("realizo uma pesquisa por {string}")
    public void realizo_uma_pesquisa_por(String termo) {
        lastTerm = termo;
        home.searchFor(termo);
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

    private String normalize(String s){
        String n = java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD);
        n = n.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return n.toLowerCase();
    }
}
