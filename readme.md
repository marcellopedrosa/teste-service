# 🛡️ Microserviço com Spring Security, OAuth2 e Arquitetura em Camadas

Este projeto é um microserviço desenvolvido com **Spring Boot**, organizado em uma arquitetura limpa de três camadas: `domain`, `application` e `infrastructure`. Ele implementa autenticação via **OAuth2**, controle de acesso com **Spring Security**, e persistência usando **Spring Data JPA**. O projeto utiliza **Maven** como sistema de build.

---

## 📁 Estrutura do Projeto

```
raiz-do-projeto/
├── domain/           # Regras de negócio, entidades, interfaces
├── application/      # Casos de uso e lógica de orquestração
└── infrastructure/   # Frameworks, APIs, repositórios, DTOs, mapeadores, adapters
```

### 🧩 Diagrama ASCII de Relação entre Módulos

```
                 +-----------------+
                 |     domain      |
                 |  (Entidades,    |
                 |  Interfaces)    |
                 +--------+--------+
                          |
                          v
                 +--------+--------+
                 |   application   |
                 | (Use Cases /    |
                 |  Services)      |
                 +--------+--------+
                          |
                          v
                 +--------+--------+
                 | infrastructure  |
                 | (Web, DB, DTOs, |
                 |  Security, etc) |
                 +----------------+
```

---

## 🔐 Segurança

- Implementação com **Spring Security**.
- Autenticação baseada em **OAuth2 (Authorization Server externo)**.
- Proteção de rotas por roles (authorities).
- Integração com `ResourceServer` para validação de JWT.

---

## 💠 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.2**
- **Spring Security**
- **Spring Data JPA**
- **OAuth2 Resource Server**
- **Maven (multi-módulo)**

---

## 📦 Módulos

### `domain`

> Contém as **entidades**, **value objects**, e **interfaces** (como repositórios).

- Totalmente isolado de frameworks.
- Define contratos e regras do negócio.

### `application`

> Orquestra casos de uso do negócio e coordena chamadas entre `domain` e `infrastructure`.

- Contém os **serviços de aplicação** (services).
- Usa interfaces do domínio e objetos de entrada/saída simples.

### `infrastructure`

> Integra com tecnologias externas e frameworks.

- Implementa os repositórios (`Spring Data JPA`).
- Define e expõe os **controllers REST**.
- Realiza o **mapeamento DTO <-> entidade** com **MapStruct** ou similar.
- Implementa **security config**, **adapters** e demais dependências técnicas.

---

## ▶️ Execução

1. Certifique-se de que o Authorization Server esteja ativo.
2. Configure as variáveis de ambiente:
   ```
   SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI=https://seu-auth-server.com
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/sua-base
   SPRING_DATASOURCE_USERNAME=seu_usuario
   SPRING_DATASOURCE_PASSWORD=sua_senha
   ```
3. Compile e suba com:
   ```bash
   mvn clean install
   cd infrastructure
   mvn spring-boot:run
   ```

---

## 💪 Testes

- Testes unitários estão localizados nos respectivos módulos.
- Para executar:
  ```bash
  mvn test
  ```

---

## 🚧 Melhorias Futuras

- Implementar caching com Spring Cache.
- Adicionar testes de integração com Testcontainers.
- Monitoramento com Micrometer + Prometheus.

