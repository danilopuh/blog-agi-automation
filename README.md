# Blog Agi Automation

Projeto de automação de testes end-to-end para validação da funcionalidade de pesquisa do [Blog do Agi](https://blogdoagi.com.br/). Desenvolvido com foco em estabilidade e confiabilidade, utilizando estratégias de busca direta via parâmetros de URL para minimizar falhas causadas por mudanças na interface.

## Funcionalidades Testadas

O projeto valida os seguintes cenários de negócio:

1. **Pesquisa válida com resultados**: Verifica se pesquisas com termos válidos retornam artigos relevantes
2. **Pesquisa sem resultados**: Confirma que o sistema exibe adequadamente quando não há conteúdo para o termo pesquisado
3. **Navegação para artigos**: Testa a capacidade de acessar artigos individuais a partir dos resultados
4. **Pesquisa case-insensitive**: Valida que termos em maiúsculas/minúsculas funcionam igualmente
5. **Tratamento de caracteres especiais**: Verifica como o sistema processa entradas com caracteres não alfanuméricos
6. **Pesquisa com múltiplos termos**: Testa busca com várias palavras-chave separadas por espaço

## Stack Tecnológica

- **Java 17**: Linguagem de programação
- **Maven**: Gerenciamento de dependências e build
- **Selenium WebDriver**: Automação de navegador (modo headless)
- **Cucumber**: Framework BDD para especificação de cenários
- **JUnit**: Framework de testes unitários
- **WebDriverManager**: Gerenciamento automático de drivers de navegador

## Pré-requisitos

- Java Development Kit (JDK) 17 ou superior
- Apache Maven 3.6 ou superior
- Chrome Browser (para WebDriver)

## Execução

### Execução local dos testes

```bash
mvn clean test
```

### Visualização de relatórios

Após a execução, o relatório HTML estará disponível em:
```
target/cucumber-reports.html
```

## Integração Contínua

O projeto inclui configuração de CI/CD via GitHub Actions que:

- Executa automaticamente a cada push na branch `main`
- Roda todos os cenários de teste
- Gera e arquiva relatórios como artifacts
- Configura ambiente Java e Maven automaticamente

Arquivo de configuração: `.github/workflows/ci.yml`

## Estrutura do Projeto

```
blog-agi-automation/
├── src/test/java/br/com/agi/
│   ├── core/
│   │   └── DriverFactory.java          # Configuração do WebDriver
│   ├── pages/
│   │   ├── BasePage.java               # Classe base para Page Objects
│   │   ├── HomePage.java               # Página inicial do blog
│   │   ├── SearchPage.java             # Página de resultados
│   │   └── ArticlePage.java            # Página de artigo individual
│   ├── steps/
│   │   └── BlogSteps.java              # Implementação dos steps Cucumber
│   └── runners/
│       └── RunCucumberTest.java        # Configuração do runner de testes
├── src/test/resources/features/
│   └── search_blog.feature             # Especificação dos cenários em Gherkin
├── .github/workflows/
│   └── ci.yml                          # Pipeline de CI/CD
├── pom.xml                             # Configuração Maven
└── README.md
```

## Arquitetura de Testes

### Page Object Model

O projeto utiliza o padrão Page Object Model para:
- Encapsular elementos e ações de cada página
- Facilitar manutenção quando há mudanças na UI
- Promover reutilização de código

### Estratégias de Robustez

- **Busca via parâmetros URL**: Utiliza `GET /?s=termo` para evitar interação com elementos de UI instáveis
- **Seletores flexíveis**: Implementa múltiplos seletores CSS como fallback
- **Normalização de texto**: Remove acentos e converte para lowercase para comparações confiáveis
- **Timeouts configuráveis**: Aguarda elementos aparecerem antes de falhar
- **Modo headless**: Execução sem interface gráfica para maior performance

### Tratamento de Cenários Edge Case

- Validação de estados "sem resultados"
- Normalização para comparações case-insensitive
- Remoção de acentos para matching robusto
- Tratamento de caracteres especiais em buscas

## Relatórios

O projeto gera relatórios em formato HTML com:
- Status de cada cenário (Pass/Fail)
- Tempo de execução por step
- Screenshots em caso de falhas
- Métricas de cobertura de testes

## Troubleshooting

### Problemas comuns

1. **ChromeDriver incompatível**: WebDriverManager resolve automaticamente
2. **Timeouts em CI**: Modo headless otimizado para pipelines
3. **Seletores quebrados**: Múltiplos seletores como fallback
4. **Instabilidade de rede**: Retry logic implementado

### Logs e Debug

Para execução com logs detalhados:
```bash
mvn test -Dwebdriver.chrome.logfile=chromedriver.log -Dwebdriver.chrome.verboseLogging=true
```

## Contribuição

Para contribuir com o projeto:

1. Fork o repositório
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## Licença

Este projeto é distribuído sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
