
# Task Management API

## Descrição

Este projeto é uma API RESTful para gerenciamento de tarefas. Utiliza Spring Boot como framework principal, Spring Data JPA para persistência de dados e Spring Security para autenticação e autorização. O projeto também faz uso do H2 Database para testes em um perfil específico e suporte a diferentes roles de usuário.

## Funcionalidades

- CRUD completo para entidades **Task**, **User** e **Category**.
- Validações de negócio para datas e status de tarefas.
- Autenticação e autorização com Spring Security.
- Perfil de testes que popula o banco de dados com usuários e tarefas predefinidas.

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database (para testes)
- BCrypt para hashing de senhas

## Configuração do Projeto

### Dependências

Adicione as seguintes dependências ao seu `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### Configuração de Segurança

- A aplicação utiliza `BCryptPasswordEncoder` para codificar senhas.
- Os usuários são carregados do banco de dados usando um serviço customizado que implementa `UserDetailsService`.
- As roles de usuário estão definidas como `ROLE_USER` e `ROLE_ADMIN`.

### Testes

Para testar o sistema, certifique-se de que o perfil `test` está ativado. Este perfil preenche o banco de dados com usuários e tarefas predefinidos.

**Usuários de Teste:**

- **Maria Brown**
  - Email: `maria@gmail.com`
  - Senha: `123456`
  - Role: `ROLE_USER`

- **Alex Green**
  - Email: `alex@gmail.com`
  - Senha: `123456`
  - Role: `ROLE_ADMIN`

- **Carl Purple**
  - Email: `carl@gmail.com`
  - Senha: `123456`
  - Role: `ROLE_USER`

### Endpoints

- **/api/tasks**: CRUD para tarefas.
- **/api/categories**: CRUD para categorias.
- **/api/users**: CRUD para usuários.

### Regras de Negócio

1. **Validação de Datas**:
   - `conclusionDate` deve ser posterior a `createDate`.
2. **Status da Tarefa**:
   - Uma tarefa só pode ser marcada como `DONE` se tiver uma `conclusionDate` válida.
   - Uma vez que uma `conclusionDate` válida é definida, o `TaskStatus` muda automaticamente para `DONE`.

### Execução

Para executar o projeto:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

Isso iniciará a aplicação com o perfil de teste ativo, populando o banco de dados H2 com os dados de exemplo.

## Melhorias Futuras

- Integração com OAuth2/JWT para autenticação mais robusta.
- Implementação de testes unitários e de integração.
- Interface de usuário para interação com a API.

## Contribuição

Contribuições são bem-vindas! Por favor, faça um fork do repositório e envie um pull request com suas alterações.

## Licença

Este projeto é licenciado sob a Licença MIT. Para mais detalhes, consulte o arquivo LICENSE.
