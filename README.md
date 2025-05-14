# 🏍️ Sprint 1 - java 

## 📌 Descrição do Projeto

Este projeto tem como objetivo o desenvolvimento de uma API RESTful em **Java com Spring Boot**, para gerenciar a entrada, saída e relacionamento entre **Moto**, **Chaveiro**, **Funcionário** e **Pátio**. A aplicação possui estrutura modularizada e segue boas práticas como:

- Uso de **DTOs** para controle de entrada/saída de dados
- Validações com **Bean Validation**
- **Paginação** e **ordenação** nos endpoints de listagem
- **Cache** com Spring Cache para otimização de desempenho
- Manipulação de exceções com um **Exception Handler global**

#Como rodar o projeto

  Para rodar o projeto, é necessário, após a clonagem ou upload do zip (necessário extrair pasta do repositório), abrir as camadas src - main - java - br.com.fiap.sprint1 (pacote), após isso é necessário ir na classe Sprint1JavaApplication
  e clicar no símbolo de run (play) no canto superior da página. 
  Após isso, você deve utilizar algum tipo de programa para testar as requisições, em nosso projeto, usamos o POSTMAN. Ao entrar no postman, faça login e clique em fazer uma nova requisição ou "new request".
  É necessário colocar o caminho do localhost da API, que seria http://localhost:8080/(entidade da requisição)
  Temos os tipos de requisição que você pode fazer: GET, PUT, DELETE e POST. De acordo com o caminho passado nas nossas classes controller (em @GetMapping, @PutMapping e tudo mais), você pode fazer a requisição que é indicada no @ logo acima do método.
  POST: basta estar nesse caminho http://localhost:8080/(entidade da requisição), clicar em "raw" nas opções que aparecem abaixo do link, e colocar os atributos que remetem a classeRequestDTO em json. Após isso, "send"
  PUT: parecido com o post, mas você mantém todos os atributos e muda o valor do aributo que deseja, esse request vai se basear na classeRequest
  GET: tem algumas formas de fazer, você pode puxar por id, http://localhost:8080/(entidade da requisição)/(idDaEntidade), ou por placa (no caso da moto): (entidade da requisição)/(PlacaDaEntidade)
  DELETE: você pode deletar suas etidade pelo id, e ficaria assim  http://localhost:8080/(entidade da requisição)/(idDaEntidade),
---

## 👨‍💻 Integrantes

- **Heitor Ortega** – RM557825  
- **Marcos Vinicius Lourenço** – RM556496  
- **Pedro Cardoso** – RM555160

---

## FUNCIONALIDADES

- Banco de dados H2
- Spring web
- Spring boot
- Sptring data JPA
- Gradle
