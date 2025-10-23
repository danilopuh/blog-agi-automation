# Blog Agi Automation (Selenium + Cucumber + Java)

Automação Web do fluxo de **pesquisa de artigos** no [Blog do Agi](https://blogdoagi.com.br/).  
Projeto focado em estabilidade: usa a **rota de busca** (`?s=termo`) para evitar flakiness de UI.

## ✅ Cenários cobertos
1. **Pesquisa válida** retorna resultados e contém o termo no título (case/acentos ignorados)
2. **Pesquisa sem resultados** mostra estado vazio
3. **Abrir artigo** a partir dos resultados

## 🧰 Stack
- Java 17, Maven
- Selenium WebDriver (headless)
- Cucumber + JUnit
- WebDriverManager (instalação automática de driver)
- Relatório HTML (`target/cucumber-reports.html`)

## ▶️ Execução local
Prerequisitos: Java 17 + Maven

```bash
mvn clean test
# Abra o relatório:
# target/cucumber-reports.html
```

## 🚀 CI (GitHub Actions)
Pipeline roda automaticamente a cada push na branch `main` e publica o relatório como artifact.

Arquivo: `.github/workflows/ci.yml`

## 📂 Estrutura
```
src/test/java/br/com/agi/
  core/DriverFactory.java
  pages/...
  steps/BlogSteps.java
  runners/RunCucumberTest.java
src/test/resources/features/search_blog.feature
.github/workflows/ci.yml
pom.xml
```

## 🔧 Notas de robustez
- Busca feita via `GET /?s=termo` (WordPress) para reduzir quebra por mudanças visuais
- Seletores genéricos para títulos e cards de artigo
- Normalização para comparação **case-insensitive** e **sem acentos**

---

> Qualquer ajuste adicional (novos cenários, suporte a outros navegadores, paralelismo, Docker) é só pedir.
