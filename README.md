# Extrator de Nota de Corretagem

## Descrição

Projeto desenvolvido em SpringBoot e Java para extração de dados de nota de corretagem de investimentos em ações na Bovespa no padrão Sinacor.
Testado com notas do banco BTG Pactual e corretora Empiricus/Vitreo. Para outras corretoras seja necessário fazer adaptações.


O objetivo deste projeto é mostrar a viabilidade de extração dos dados do PDF da nota de corretagem. O código é livre e pode ser embutido em aplicações que necessitem de tais dados.

## Tecnologias Usadas

- **SpringBoot**: Ferramenta que facilita e agiliza o desenvolvimento de aplicativos da web e de microsserviços com o Spring Framework.
- **Java 17**: Linguagem de programação usada para implementar o backend.
- **NodeJs/JavaScript**: Linguagem de programação usada para implementar o frontend.
- **HTML e CSS**: Implementação das telas do frontend.
- **Gradle**: Sistema de automatização de builds.

## Estrutura do Projeto

O projeto está organizado nas seguintes módulos:

- Front-end
- Back-end

## Como Executar

### Pré-requisitos

- Java 17 instalado.

### Passo a Passo

1. **Baixar o Projeto**

   Clone o repositório ou baixe os arquivos do projeto.

2. **Compilar e Executar o Back-end**

   No terminal, navegue até o diretório do projeto e execute os seguintes comandos:

      
    
   Isso iniciará o servidor na porta 12345.

3. **Compilar e Executar o Front-end**

   Abra um novo terminal, navegue até o diretório do projeto e execute os seguintes comandos:

       $ cd .\frontend\
       $ node .\Index.js

   Isso conectará o Front-end ao Back-end.

4. **Acessar a aplicação pelo navegador**

     Abra o seu navegador e digite:

       http://localhost:5000

     Isso abrirá a aplicação para que você possa utilizar.
