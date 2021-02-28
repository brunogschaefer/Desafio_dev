# Desafio GoDev_

Este repositório é parte do desafio GoDev_ da proway.

Trata-se de uma Application Programming Interface (API) REST de gerenciamento de evento, utilizando Java e Spring Boot e está disposta conforme o padrão MVC. 
O código foi escrito em inglês, por uma questão de padrão, e a API não dispõe de interface grafica, de modo que pode ser necessário o uso de aplicações terceiras.

## Tecnologias utilizadas

1. Java
2. Spring Boot (Data JPA, Security, Validation, Web, Test/Security Test)
3. Maven
4. H2 database
5. PostgreSQL

## Ambientes

A API dispõe de três ambientes:
1. Test (utilizado para o desenvolvimento local com o banco de dados H2)
2. Dev (também para uso local, mas utilizando o postgreSQL)
3. Prod (Utilizado para o Deploy da API)

Para mudar o ambiente da API, é necessário modificar o arquivo godev/src/main/resources/application.properties.
O ambiente é modificado através do `spring.profiles.active=*`, sendo que onde consta *, deve constar o ambiente desejado (test, dev, prod).

## Estrutura

A API é dividida em duas camadas: Controller e Model.
A camada controller é responsável por receber e retornar os comandos HTTP. Esses comandos são repassados à camada Model através de Data-Transfer Objects (DTO).
Esses objetos DTO são enviados à subcamada de serviços, que será responsável por realizar a lógica de persistência da API, e trabalhará com as entidades, com as classes de utilidade e com os repositórios.
Cumpre ressaltar que a lógica de negócio está separada da camada de serviços, bem como das entidades, e estão localizadas na pasta "utils".

A API possui três entidades:
1. Participant (que são os participantes do evento)
2. EventRoom (que são as salas de evento)
3. CoffeeSpace (que são os espaços de café)

## Instruções de uso

A API foi criada no Ubuntu, portanto as informações aqui podem não funcionar em outros Sistemas Operacionais.
Abrindo o terminal do Linux, é necessário clonar este repositório para o computador, o que pode ser feito através do comando:

`git clone https://github.com/brunogschaefer/Desafio_dev.git`

Após clonar o repositório, é necessário instalar o maven:

`sudo apt install maven`

Para executar o projeto com o maven, é necessário estar com o terminal na pasta root do projeto `../godev`. Em seguida, utilize o seguinte comando:

`mvn spring-boot:run`

Esse comando irá executar o projeto através do Tomcat, na porta 8080. Lembrando que o projeto está em ambiente de "test" como padrão, de modo que o projeto será executado utilizando o banco de dados H2.
O banco de dados H2 pode ser acessado através do link `http://localhost:8080/h2-console` no navegador, sendo que o login padrão é `sa`, e o campo de senha deve ser deixado em branco.
Para testar as os endpoints, é recomendável o uso do aplicativo Postman, que pode ser adquirido gratuitamente no site `https://www.postman.com/`.
Para rodar a collection específica para esse projeto, utilize o seguinte link:

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/72d009320795c4f13c09)

## Endpoints

A API dispõe de 10 funções básicas, de modo que cumprem os requisitos propostos no desafio. As funções e suas respectivas lógicas estão dispostas a seguir:

### EventRoom
 
Para criar uma nova sala de evento, é necessário utilizar o método HTTP POST, enviando uma mensagem JSON ao controller, contendo os campos "name"(nome da sala) e "capacity" (capacidade/lotação).
Devem ser enviados pela URI "~/eventroom"

Para retornar uma sala específica, é necessário um método HTTP GET pelo seguinte path "~/eventroom/{nome}"

E para retornar todas as salas, é necessário um método HTTP GET pelo seguinte path "~/eventroom"

### CoffeeSpace

Para criar um novo espaço de café, é necessário utilizar o método HTTP POST, enviando uma mensagem JSON ao controller, contendo o campo "name"(nome do espaço).
Devem ser enviados pela URI "~/eventroom". **A criação de espaços de café está limitado a dois espaços.**

Para retornar um espaço especfico, é necessário um método HTTP GET pelo seguinte path "~/coffeespace/{nome}"

E para retornar todos os espaços, é necessário um método HTTP GET pelo seguinte path "~/coffeespace"

### Participant

Para adicionar um novo participante, é necessário utilizar o método HTTP POST, enviando uma mensagem JSON ao controller, contendo o campo "firstName"(nome) e "lastName" (sobrenome).
Devem ser enviados pela URI "~/participants". 
Cada participante será adicionado à sala subsequente ao participante anterior, de modo que a diferença entre participantes entre uma sala e outra será de, no máximo, 1 participante.
**No entanto, para adicionar um participante, é necessário que existam, pelo menos, 1 sala de evento e 1 espaço de café**

Para retornar um participante especfico, é necessário um método HTTP GET pelo seguinte path "~/participants/{nome}+{sobrenome}"

E para retornar todos os participantes, é necessário um método HTTP GET pelo seguinte path "~/participants"

### SetStage

Por fim, para mudar a etapa do evento, foi criado um método PUT denominado SetStage. A API dispõe de suas constantes denominadas STAGE_A (etapa A) e STAGE_B (etapa B).
O método em questão recebe um boolean que, quando "true", executará as seguintes etapas:
1. modificará a etapa de todos os participantes, de STAGE_A para STAGE_B
2. selecionará todos os participantes com o ID ímpar
3. mudará os participantes selecionados para a sala subsequente

Cumpre destacar, no entanto, que esse método quebrará a regra de diferença de 1 participante entre as salas, visto que, conforme o desafio, é necessário selecionar a metade dos participantes para mudar de sala, de modo que os demais participantes continuarão na mesma.

Para executar o método em questão, basta utilizar um método HTTP PUT, através do path ~/participants/setstage/{boolean}

## Segurança

Para garantir a segurança nas requisições HTTP e no acesso web, há um arquivo chamado Security.Config. Ele utiliza as assinaturas @Configuration e @EnableWebSecurity do Spring Security, e extende a classe WebSecurityConfigurerAdapter. 

## Seeds

Por fim, há um arquivo data.sql que serve como seed para o banco de dados H2. Toda vez que o ambiente de testes é executado, esse arquivo criará as tabelas necessrias e preencherá os dados no banco.
Já o ambiente dev utiliza o schema-generator. Ou seja, toda vez que o ambiente dev  executado,  criado um arquivo create.sql, contendo o query necessrio para a criação das tabelas no postgreSQL.
