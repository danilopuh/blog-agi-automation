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

  Cenário: Pesquisa com termo em maiúsculas retorna resultados
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "EMPRÉSTIMO"
    Então são exibidos artigos relacionados à palavra pesquisada
    E os títulos retornados contêm o termo pesquisado, ignorando maiúsculas e acentos

  Cenário: Pesquisa com caracteres especiais
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "crédito@#$%"
    Então são exibidos artigos relacionados à palavra pesquisada

  Cenário: Pesquisa com múltiplos termos
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "cartão crédito"
    Então são exibidos artigos relacionados à palavra pesquisada
    E pelo menos um título contém algum dos termos pesquisados
