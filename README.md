# Carteira de Investimentos

## Intuito do Projeto

Este projeto tem como objetivo criar uma API REST para gerenciar uma carteira de ações.

## Tecnologias Utilizadas

- **Linguagem**: Kotlin
- **Framework**: Spring (Spring Boot)
- **Banco de Dados**: PostgreSQL

## Documentação

Para acessar a documentação, entre na pasta do projeto docs ou entre <a href="./docs/">aqui!</a></br></br>
Para pegar só o esquemas de rotas do postman <a href="./docs/CarteiraDeInvestimento.postman_collection.json">click aqui!</a></br></br>
Ou só o diagrama relacional, <a href="./docs/database.pdf">click aqui!</a></br></br>
## Como Usar

**Para clonar o projeto, use um dos comandos a seguir:**
<br>
<br>
HTTP
```bash 
$ git clone https://github.com/MarcosPauloV/CarteiraDeInvestimento.git
```
SSH
```bash 
$ git clone git@github.com:MarcosPauloV/CarteiraDeInvestimento.git
```
GitHubCLI
```bash 
$ gh repo clone MarcosPauloV/CarteiraDeInvestimento
```
**Apos o clone instale as dependencias do gradle pela IDE do intellij**

**Em seguida, rode primeiro o banco de dados usando o comando a seguir**
```bash 
$ docker-compose up -d
```
**Para rodar a IDE, você podera startar pela propria IDE ou utilizando o comando a seguir:**
```bash 
$ ./gradlew bootrun
```