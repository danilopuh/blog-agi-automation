# Blog AGI - Test Automation Framework# Blog Agi Automation



## DescriçãoProjeto de automação de testes end-to-end para validação da funcionalidade de pesquisa do [Blog do Agi](https://blogdoagi.com.br/). Desenvolvido com foco em estabilidade e confiabilidade, utilizando estratégias de busca direta via parâmetros de URL para minimizar falhas causadas por mudanças na interface.



Framework de automação de testes end-to-end para validação da funcionalidade de pesquisa do Blog AGI. Implementa práticas de engenharia de qualidade e padrões de design consolidados para automação web.## Funcionalidades Testadas



## Stack TecnológicaO projeto valida os seguintes cenários de negócio:



- **Java 17** - Linguagem de programação1. **Pesquisa válida com resultados**: Verifica se pesquisas com termos válidos retornam artigos relevantes

- **Maven 3.9+** - Gerenciamento de dependências e build2. **Pesquisa sem resultados**: Confirma que o sistema exibe adequadamente quando não há conteúdo para o termo pesquisado

- **Selenium WebDriver 4.24** - Automação de navegador web3. **Navegação para artigos**: Testa a capacidade de acessar artigos individuais a partir dos resultados

- **Cucumber 7.18** - Framework BDD (Behavior Driven Development)4. **Pesquisa case-insensitive**: Valida que termos em maiúsculas/minúsculas funcionam igualmente

- **JUnit 4.13** - Framework de testes5. **Tratamento de caracteres especiais**: Verifica como o sistema processa entradas com caracteres não alfanuméricos

- **WebDriverManager 5.9** - Gerenciamento automático de drivers6. **Pesquisa com múltiplos termos**: Testa busca com várias palavras-chave separadas por espaço

- **JaCoCo 0.8** - Análise de cobertura de código

## Stack Tecnológica

## Arquitetura

- **Java 17**: Linguagem de programação

### Page Object Model- **Maven**: Gerenciamento de dependências e build

```- **Selenium WebDriver**: Automação de navegador (modo headless)

src/test/java/br/com/agi/pages/- **Cucumber**: Framework BDD para especificação de cenários

├── BasePage.java          # Classe base com funcionalidades comuns- **JUnit**: Framework de testes unitários

├── HomePage.java          # Página inicial e pesquisa- **WebDriverManager**: Gerenciamento automático de drivers de navegador

├── SearchPage.java        # Resultados de pesquisa

└── ArticlePage.java       # Páginas de artigos## Pré-requisitos

```

- Java Development Kit (JDK) 17 ou superior

### Test Implementation- Apache Maven 3.6 ou superior

```- Chrome Browser (para WebDriver)

src/test/java/br/com/agi/

├── steps/BlogSteps.java           # Step definitions Cucumber## Execução

├── hooks/ScreenshotHooks.java     # Captura automática de screenshots

├── runners/RunCucumberTest.java   # Configuração de execução### Execução local dos testes

└── core/DriverFactory.java       # Gestão do WebDriver

``````bash

mvn clean test

### Feature Specifications```

```

src/test/resources/features/### Visualização de relatórios

└── search_blog.feature    # Cenários BDD em Gherkin

```Após a execução, o relatório HTML estará disponível em:

```

## Cobertura de Testestarget/cucumber-reports.html

```

### Cenários Implementados (13 total)

## Integração Contínua

| Tipo | Tag | Descrição | Status |

|------|-----|-----------|--------|O projeto inclui configuração de CI/CD via GitHub Actions que:

| Funcional | @e2e | Pesquisa válida retorna resultados | ✅ |

| Funcional | @e2e | Pesquisa sem resultados | ✅ |- Executa automaticamente a cada push na branch `main`

| Funcional | @e2e | Navegação para artigo | ✅ |- Roda todos os cenários de teste

| Funcional | @e2e | Pesquisa case-insensitive | ✅ |- Gera e arquiva relatórios como artifacts

| Funcional | @e2e | Pesquisa com caracteres especiais | ✅ |- Configura ambiente Java e Maven automaticamente

| Funcional | @e2e | Pesquisa com múltiplos termos | ✅ |

| Edge Case | @edge-case | String vazia | ✅ |Arquivo de configuração: `.github/workflows/ci.yml`

| Edge Case | @edge-case | Espaços em branco | ✅ |

| Edge Case | @edge-case | Pesquisa com números | ✅ |## Estrutura do Projeto

| Edge Case | @edge-case | Termo muito longo | ✅ |

| Edge Case | @edge-case | Caracteres sem resultados | ✅ |```

| Performance | @performance | Tempo de resposta < 5s | ✅ |blog-agi-automation/

| Exceção | @exception | Site indisponível/404 | ✅ |├── src/test/java/br/com/agi/

│   ├── core/

## Funcionalidades Técnicas│   │   └── DriverFactory.java          # Configuração do WebDriver

│   ├── pages/

### Captura de Screenshots│   │   ├── BasePage.java               # Classe base para Page Objects

- Automática em falhas de teste│   │   ├── HomePage.java               # Página inicial do blog

- Nomenclatura com timestamp│   │   ├── SearchPage.java             # Página de resultados

- Integração com relatórios Cucumber│   │   └── ArticlePage.java            # Página de artigo individual

- Diretório: `target/screenshots/`│   ├── steps/

│   │   └── BlogSteps.java              # Implementação dos steps Cucumber

### Normalização de Texto│   └── runners/

- Remoção de acentos para comparações│       └── RunCucumberTest.java        # Configuração do runner de testes

- Case-insensitive matching├── src/test/resources/features/

- Suporte a caracteres UTF-8│   └── search_blog.feature             # Especificação dos cenários em Gherkin

├── .github/workflows/

### Múltiplos Relatórios│   └── ci.yml                          # Pipeline de CI/CD

- **HTML**: `target/cucumber-html-reports/`├── pom.xml                             # Configuração Maven

- **JSON**: `target/cucumber-reports/json/`└── README.md

- **JUnit XML**: `target/cucumber-reports/junit/````

- **Timeline**: `target/cucumber-reports/timeline/`

- **JaCoCo**: Cobertura de código## Arquitetura de Testes



## Execução### Page Object Model



### Pré-requisitosO projeto utiliza o padrão Page Object Model para:

- Java 17+- Encapsular elementos e ações de cada página

- Maven 3.9+- Facilitar manutenção quando há mudanças na UI

- Chrome Browser- Promover reutilização de código



### Comandos### Estratégias de Robustez



```bash- **Busca via parâmetros URL**: Utiliza `GET /?s=termo` para evitar interação com elementos de UI instáveis

# Executar todos os testes- **Seletores flexíveis**: Implementa múltiplos seletores CSS como fallback

mvn clean test- **Normalização de texto**: Remove acentos e converte para lowercase para comparações confiáveis

- **Timeouts configuráveis**: Aguarda elementos aparecerem antes de falhar

# Executar com relatórios completos- **Modo headless**: Execução sem interface gráfica para maior performance

mvn clean verify

### Tratamento de Cenários Edge Case

# Executar por tags específicas

mvn test -Dcucumber.filter.tags="@e2e"- Validação de estados "sem resultados"

mvn test -Dcucumber.filter.tags="@edge-case"- Normalização para comparações case-insensitive

mvn test -Dcucumber.filter.tags="@performance"- Remoção de acentos para matching robusto

```- Tratamento de caracteres especiais em buscas



### Visualização de Relatórios## Relatórios



```O projeto gera relatórios em formato HTML com:

target/cucumber-html-reports/cucumber-html-reports/overview-features.html- Status de cada cenário (Pass/Fail)

target/cucumber-reports/timeline/index.html- Tempo de execução por step

```- Screenshots em caso de falhas

- Métricas de cobertura de testes

## Estrutura de Diretórios

## Troubleshooting

```

blog-agi-automation/### Problemas comuns

├── src/test/

│   ├── java/br/com/agi/1. **ChromeDriver incompatível**: WebDriverManager resolve automaticamente

│   │   ├── core/DriverFactory.java2. **Timeouts em CI**: Modo headless otimizado para pipelines

│   │   ├── pages/[Page Objects]3. **Seletores quebrados**: Múltiplos seletores como fallback

│   │   ├── steps/BlogSteps.java4. **Instabilidade de rede**: Retry logic implementado

│   │   ├── hooks/ScreenshotHooks.java

│   │   └── runners/RunCucumberTest.java### Logs e Debug

│   └── resources/features/search_blog.feature

├── target/Para execução com logs detalhados:

│   ├── cucumber-reports/```bash

│   ├── cucumber-html-reports/mvn test -Dwebdriver.chrome.logfile=chromedriver.log -Dwebdriver.chrome.verboseLogging=true

│   ├── screenshots/```

│   └── surefire-reports/

└── pom.xml## Contribuição

```

Para contribuir com o projeto:

## CI/CD Integration

1. Fork o repositório

### GitHub Actions2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)

- Execução automática em push/PR3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)

- Múltiplos formatos de relatório4. Push para a branch (`git push origin feature/nova-funcionalidade`)

- Artefatos de evidência5. Abra um Pull Request

- Notificações de status

## Licença

### Pipeline Configuration

```yamlEste projeto é distribuído sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

# .github/workflows/ci.yml
- Java 17 setup
- Maven dependency resolution
- Test execution
- Report generation
- Artifact upload
```

## Padrões de Qualidade

### Implementações
- Testes independentes e idempotentes
- Waits explícitos com timeouts configuráveis
- Estratégias de seleção robustas
- Tratamento de exceções estruturado
- Cleanup automático de recursos
- Execução headless para CI/CD

### Métricas
- **Success Rate**: 100% (13/13 cenários)
- **Execution Time**: ~45-60 segundos
- **Code Coverage**: Análise via JaCoCo
- **Screenshot Evidence**: Falhas automaticamente documentadas

## Configuração do Ambiente

### WebDriver Configuration
```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--headless=new");
options.addArguments("--disable-gpu");
options.addArguments("--window-size=1920,1080");
options.addArguments("--no-sandbox");
options.addArguments("--disable-dev-shm-usage");
```

### Maven Dependencies
- Selenium WebDriver 4.24.0
- Cucumber Java 7.18.1
- JUnit 4.13.2
- WebDriverManager 5.9.2
- Maven Cucumber Reporting 5.7.7
- JaCoCo Maven Plugin 0.8.11

## Padrões de Desenvolvimento

### Code Standards
- Page Object Model implementation
- BDD scenarios em português
- Conventional commit messages
- Test coverage maintenance
- Cross-browser compatibility

### Project Structure
- Separação clara de responsabilidades
- Reutilização de componentes
- Configuração centralizada
- Logs estruturados
- Error handling consistente