# 🌐 ForumHub

O **ForumHub** é um projeto desenvolvido como parte do Desafio Oracle Next Education em parceria com a Alura. Trata-se de uma aplicação Back End que tem como objetivo fornecer uma API REST para gerenciar tópicos de um fórum, utilizando o framework Spring Boot. A API permitirá aos usuários realizar operações essenciais de CRUD (Criar, Consultar, Atualizar e Deletar) sobre os tópicos.

## 🎯 Funcionalidades

- **Cadastro de tópico**: Criar tópico com título, mensagem, autor e curso.
- **Listagem de tópicos**: Listar todos os tópicos cadastrados.
- **Detalhar um tópico**: Recuperar informações detalhadas de um tópico a partir do seu ID.
- **Atualizar tópico**: Alterar os dados de um tópico (Obs: somente o autor do tópico pode realizar essa operação).
- **Excluir tópico**: Excluir um tópico a partir do seu ID (Obs: somente o autor do tópico pode realizar essa operação).
- **Autenticação JWT**: Segurança com autenticação baseada em tokens.

## ✨ Tecnologias Utilizadas

Este projeto foi desenvolvido utilizando as seguintes tecnologias e ferramentas:

- **Java JDK**  
  Utilizado para compilar e executar o código Java.  
  🔗 [Baixe a versão 17 do Java LTS](https://www.oracle.com/java/technologies/javase-downloads.html)

- **Maven** (versão 4 ou superior)  
  Gerenciador de dependências e automação de builds.

- **Spring Boot** (versão 3.4.1)  
  Framework para desenvolvimento rápido e eficiente de aplicações Java.  
  🔗 [Explore o Spring Initializr](https://start.spring.io/)

- **MySQL** (versão 8.0.40)  
  Banco de dados relacional utilizado para armazenar e gerenciar os dados do projeto.  
  🔗 [Baixe o MySQL](https://www.mysql.com/downloads/)

- **IDE IntelliJ IDEA** (opcional)  
  Ferramenta recomendada para desenvolvimento, com suporte robusto para Java e Spring Boot.  
  🔗 [Baixe o IntelliJ IDEA](https://www.jetbrains.com/idea/download/)

### Observação
Certifique-se de ter todas as ferramentas e tecnologias acima instaladas e configuradas corretamente antes de executar o projeto. Caso utilize outra IDE ou ferramentas diferentes, ajuste o ambiente de acordo com sua preferência.

## 🛠️ Executando o Projeto no IntelliJ IDEA

### 1. **Clonar o Repositório**
- Faça o clone do repositório para a sua máquina local.

### 2. **Abrir o Projeto no IntelliJ IDEA**
- Abra o IntelliJ IDEA.
- Navegue até **File > Open** e selecione a pasta do projeto clonado.

### 3. **Configurar o MySQL**
1. **Crie um banco de dados no MySQL com o nome desejado**
2. **Certifique-se de que o MySQL está exucutando corretamente ou ajuste conforme necessário**
3. **Configuração de Variáveis de Ambiente:** Este projeto utiliza variáveis de ambiente para configurar a conexão com o banco de dados. Certifique-se de configurá-las!

- `DB_HOST`: Host onde o banco de dados está rodando. Exemplo: `localhost` ou `db.myserver.com`.
- `DB_NAME`: Nome do banco de dados. Exemplo: `meubanco`.
- `DB_USER`: Usuário com permissões de acesso ao banco de dados.
- `DB_PASSWORD`: Senha do usuário configurado no banco de dados.
- `JWT_SECRET`: Senha do JWT.

### 4. **Configurar o Arquivo `application.properties`**
No IntelliJ, configure o arquivo `application.properties` para utilizar as variáveis de ambiente:

```properties
spring.application.name=api
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.token.secret=${JWT_SECRET}
```

### 5. **Rodar o Projeto**
- No IntelliJ, localize o arquivo principal: `ApiApplication.java`.
- Clique com o botão direito no arquivo e selecione **Run** para iniciar a aplicação.

### 6. **Testar as Funcionalidades**
- Após iniciar a aplicação, use o terminal ou qualquer interface disponível para interagir com o menu e testar as funcionalidades.
- Certifique-se de que o banco de dados está configurado corretamente para persistência dos dados.

