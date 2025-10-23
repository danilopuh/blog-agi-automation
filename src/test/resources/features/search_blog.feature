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

  @edge-case
  Cenário: Pesquisa com string vazia
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por ""
    Então são exibidos artigos relacionados à palavra pesquisada

  @edge-case 
  Cenário: Pesquisa com apenas espaços em branco
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "   "
    Então são exibidos artigos relacionados à palavra pesquisada

  @edge-case
  Cenário: Pesquisa com números
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "2024"
    Então são exibidos artigos relacionados à palavra pesquisada

  @edge-case
  Cenário: Pesquisa com termo muito longo
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"
    Então não deve haver resultados

  @edge-case
  Cenário: Pesquisa com caracteres que não geram resultados
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "xyz123!@#$%^&*()_+-={}[]|\:;'<>?,./~`"
    Então não deve haver resultados

  @performance
  Cenário: Validar tempo de resposta da pesquisa
    Dado que estou na página inicial do blog do Agi
    Quando realizo uma pesquisa por "empréstimo" e meço o tempo de resposta
    Então o resultado deve aparecer em menos de 5 segundos

  @exception
  Cenário: Comportamento quando site está indisponível
    Dado que navego para uma URL inválida do blog
    Então deve ser exibida uma mensagem de erro apropriada
