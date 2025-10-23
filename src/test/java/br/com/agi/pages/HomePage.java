package br.com.agi.pages;

public class HomePage extends BasePage {

    public void openHome() {
        open(BASE_URL);
    }

    public void searchFor(String term) {
        // Usa a rota de busca do WordPress para evitar dependência de UI (menos flakiness)
        open(BASE_URL + "/?s=" + encode(term));
    }
}
