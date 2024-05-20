# Desafio Automacao API

Este é um projeto de automação de testes de API.

## Requisitos

* Versão 17 do Java JDK;
* Apache Maven instalado e configurado na aplicação para o gerenciamento de dependencias e build;

## Bibliotecas Utilizadas
* Lombok: Utilizado para a geração automática de métodos getters e setters, além da construção de objetos de forma simplificada;
* Rest-Assured: Utilizado para efetuar chamadas aos serviços de API REST de forma fluente e simplificada;
* TestNG: Utilizado para a execução dos métodos de teste, oferecendo recursos adicionais como agrupamento de testes, paralelismo e configurações avançadas
* JUnit: Utilizado em conjunto com TestNG para a execução de testes, proporcionando uma estrutura robusta para a organização e execução dos testes.

## Passos necessários para rodar o projeto

* Clonar o repositório;
* No terminal, rode o comando: mvn clean install

## Para visualizar o relatório de testes:
O relatório se encontra na pasta: target > site

## Instrução para gerar um relatório:

mvn surefire-report:report

## Instrução para execução dos testes via terminal:
mvn test