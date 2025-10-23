# language: pt
@e2e
Funcionalidade: Pesquisa de artigos no Blog do Agi
  Como visitante do blog
  Quero pesquisar conteúdos por palavra-chave
  Para encontrar artigos relevantes rapidamente

  Cenário: Pesquisa válida retorna resultados
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "cartão"
    Então são exibidos artigos relacionados à palavra pesquisada
    E os títulos retornados contêm o termo pesquisado, ignorando maiúsculas e acentos

  Cenário: Pesquisa sem resultados mostra estado vazio
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "asdfghzxcvqwe"
    Então não deve haver resultados

  Cenário: Abrir um artigo a partir dos resultados
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "finanças"
    E abro o primeiro resultado da lista
    Então o artigo é aberto corretamente
