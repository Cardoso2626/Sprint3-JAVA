# 🏍️ Sprint 3 - java 

## 📌 Descrição do Projeto

Este projeto tem como objetivo o desenvolvimento de uma API RESTful em **Java com Spring Boot**, para gerenciar a entrada, saída e relacionamento entre **Moto**, **Chaveiro**, **Funcionário** e **Pátio**. A aplicação possui estrutura modularizada e segue boas práticas como:

- Uso de **DTOs** para controle de entrada/saída de dados
- Validações com **Bean Validation**
- **Paginação** e **ordenação** nos endpoints de listagem
- **Cache** com Spring Cache para otimização de desempenho
- Manipulação de exceções com um **Exception Handler global**

  ## FUNCIONALIDADES

- Banco de dados Oracle
- Spring web
- Spring boot
- Sptring data JPA
- Gradle
- Flyway (migrations)
- Spring Security (OAuth 2)
- Thymeleaf (MVC)


 Para as próximas entregas, planejamos conectar uma API de bluetooth, para conseguirmos linka-las com as motos, que é a ideia do nosso projeto. Basicamente, o funcioanrio conseguiria localizar sua moto através de conexões por bluetooth, que a partir dai, mostraria a localização da moto no galpão. Obviamente, isso ainda não foi implementado mas está sendo planejado pelos desenvolvedores do projeto.
-------
#Como rodar o projeto

(CRIE PRIMEIRAMENTE O OBJETO DA CLASSE MOTO, POIS NÃO É NECESSÁRIO UMA PASSAGEM DE ID PARA OS RELACIONAMENTOS QUE ESTA CLASSE OBTEM)
(LEMBRANDO QUE PARA CADA CLASSE, NÃO É NECESSÁRIO PASSAR SEU ID, APENAS NOS RELACIONAMENTOS. O ID DE CADA CLASSE É CRIADO AUTOMATICAMENTE)

- Para rodar o projeto, é necessário, após a clonagem ou upload do zip (necessário extrair pasta do repositório), abrir o projeto em uma IDE, como o InteliJi, abrir as camadas src - main - java - br.com.fiap.sprint1 (pacote), mas antes disso você deve fazer um autenticação no git
- Entre no git você precisa ir em "settings" no seu perfil, a partir daí, você cria um OAuth app, e após preencher as informações e criar o app, o git vai gerar um client id e você poderá gerar um client token. Após isso, salve essas informações e va no projeto que você clonou, e siga os seguintes passos:
  - Clique no icone "CurrentFile" que está na esquerda do icone de run (canto superior direito indo para o meio)
  - Clique em  "Edit configurations" e clique em "+"
  - Clique em "Application", coloque um nome que você quer, onde está -cp <no module> selecione "Sprint1.main", e onde está escrito "Main class", clique no icone de livro que fica um pouco mais a direita e selecione a primeira opção que aparecer
- Antes de rodar você precisa colocar algumas variáveis de ambientes, que são as variáveis de ambiente da conexão com o banco de dados, que são {db.user} e {db.passwd}, para modifica-las, clique em "modify options" e selecione a opção "Environment variables", após isso, essa opção aparecerá na sua tela, e você deve clicar no "livrinho" na direita para configura-la. Clique em + para adicionar uma variavel, coloque como "name" o db.user, e o valor sendo "rm557825", e a mesma coisa com db.passwd mas o valor sendo "fiap25", após isso, temos mais 2 variaveis para você preencher, GITHUB_CLIENT_ID e GITHUB_CLIENT_SECRET, e os valores vão ser os valores que o github te deu. Após isso, clique ok para tudo
- Pronto, agora você pode rodar o projeto no icone de play

- (Antes você deve verificar sua versão java para que o projeto rode da maneira correta, para isso va no build.gradle e verifique que o java está em 22, para mudar, se necessário, vá no sanduíche, canto superior esquerdo, "project structure", e mude o SDK para 22). 
- Para rodar o thymeleaf é necessário entrar na rota http://localhost:8080/pageFuncionario/cadastro, a partir daí ele abrirá um formulário de autenticação. POR FAVOR, faça a autenticação clicando no botão do github.
  
  - Agora, para rodar requisições REST, você deve utilizar algum tipo de programa para testar as requisições, em nosso projeto, usamos o POSTMAN. Ao entrar no postman, faça login e clique em fazer uma nova requisição ou "new request".
  - É necessário colocar o caminho do localhost da API, que seria http://localhost:8080/(entidadedarequisição)
 - Temos os tipos de requisição que você pode fazer: GET, PUT, DELETE e POST. De acordo com o caminho passado nas nossas classes controller (em @GetMapping, @PutMapping e tudo mais), você pode fazer a requisição que é indicada no @ logo acima do método.
 - POST: basta estar nesse caminho http://localhost:8080/(entidadedarequisição), clicar em "raw" nas opções que aparecem abaixo do link, e colocar os atributos que remetem a classeRequestDTO em json. Após isso, "send"
 - PUT: parecido com o post, mas você mantém todos os atributos e muda o valor do aributo que deseja, esse request vai se basear na classeRequest. A rota será a mesma do post
  - GET: tem algumas formas de fazer, você pode puxar por id, http://localhost:8080/(entidade da requisição)/(idDaEntidade), ou por placa (no caso da moto): (entidadedarequisição)/(PlacaDaEntidade)
  - DELETE: você pode deletar suas etidade pelo id, e ficaria assim  http://localhost:8080/(entidadedarequisição)/(idDaEntidade),
  - Além disso tudo você pode puxar por páginas (get), seguindo o caminho que também está no controller, temos 2 tipos, o pageable de Moto e de Chaveiro.
       - Ex de Chaveiro: http://localhost:8080/chaveiro/por-dispositivo?dispositivo=tag123&page=0&size=5&sort=dispositivo,asc
       - Ex de Moto: http://localhost:8080/moto/por-placa?placa=ABC1234&page=0&size=5&sort=modelo,asc

---

## 👨‍💻 Integrantes

- **Heitor Ortega** – RM557825  
- **Marcos Vinicius Lourenço** – RM556496  
- **Pedro Cardoso** – RM555160


