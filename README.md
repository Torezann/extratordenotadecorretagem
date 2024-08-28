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

- [https://docs.gradle.org/current/userguide/installation.html Java 17]
- Node 20
- Gradle 8

### Passo a Passo

1. **Baixar o Projeto**

   Clone o repositório ou baixe os arquivos do projeto.

2. **Configure o diretório de armazenamento de notas Json**

   Altere o valor da propriedade "diretorio-de-notas" no arquivo "appliaction.properties" como você quiser, desde que seja um diretório válido e com direito de escrita para o usuário do sistema operacional que irá executar o back-end.

         \backend\src\main\resources\appliaction.properties

3. **Compilar e Executar o Back-end**

   No terminal, entre no diretório do projeto e execute os seguintes comandos:

         $ cd .\backend\
         $ gradle clean build
         $ java -jar .\build\libs\extratordenotadecorretagem.jar
    
   Isso iniciará o servidor back-end na porta 8080.

4. **Compilar e Executar o Front-end**

   Abra um novo terminal, entre no diretório do projeto e execute os seguintes comandos:

         $ cd .\frontend\
         $ node .\Index.js

   Isso conectará o Front-end ao Back-end.

5. **Acessar a aplicação pelo navegador**

     Abra o seu navegador e digite:

         http://localhost:5000

     Isso abrirá a aplicação para que você possa utilizar.
