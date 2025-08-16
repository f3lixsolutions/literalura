# LiterAlura 📚

Projeto de estudos do desafio Alura + Oracle ONE: um catálogo de livros que busca dados na API Gutendex, salva no PostgreSQL e conversa com a gente pelo console.
Sou iniciante, então a ideia aqui é ser direta e simples. :)

----------------------------------------
## O que o projeto faz
- Buscar livro por título na API e salvar no banco;
- Listar todos os livros cadastrados;
- Listar autores cadastrados;
- Listar autores vivos em um ano informado;
- Mostrar a quantidade de livros por idioma (pt | en | es | fr).

Regra do desafio: considerar só o primeiro autor e só o primeiro idioma que a API retorna.

----------------------------------------
## Tecnologias usadas
- Java 21 (pode ser 17+)
- Spring Boot 3 (Data JPA)
- PostgreSQL 16+
- HttpClient (Java 11+) para chamadas HTTP
- Jackson para JSON

----------------------------------------
## Pré-requisitos
- Java JDK 17 ou superior instalado;
- Maven instalado;
- PostgreSQL rodando localmente;
- (Opcional) IntelliJ IDEA.

----------------------------------------
## Configuração do banco
Crie um banco chamado literalura (ou troque o nome na configuração):

CREATE DATABASE literalura;

Edite o arquivo src/main/resources/application.properties e ajuste usuário/senha:

spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Dica: ddl-auto=update ajuda no desenvolvimento. Em produção seria outra estratégia.

----------------------------------------
## Como rodar
Pelo Maven:
mvn spring-boot:run

Ou pelo IntelliJ: rode a classe com.felixsolutions.literalura.LiterAluraApplication.

----------------------------------------
## Como usar (menu)
Ao iniciar o app, aparece um menu simples:

=== LiterAlura ===
1) Buscar livro pelo título (API -> salvar)
2) Listar livros registrados
3) Listar autores registrados
4) Listar autores vivos em determinado ano
5) Quantidade de livros por idioma [pt|en|es|fr]
0) Sair
   Escolha:

Exemplos rápidos:

1) Buscar por título
   Escolha: 1
   Título: Dom Casmurro
   Salvo: "Dom Casmurro" [pt] - Machado de Assis (downloads: 1615)

2) Listar livros
   "Dom Casmurro" [pt] - Machado de Assis (downloads: 1615)

4) Autores vivos em 1800
   Escolha: 4
   Ano: 1800
   Jane Austen (nasc: 1775, fal: 1817)

5) Quantidade por idioma
   Escolha: 5
   Idioma [pt|en|es|fr]: pt
   Quantidade de livros no idioma 'pt': 1

----------------------------------------
## Estrutura do projeto (resumo)
src/main/java/com/felixsolutions/literalura
├─ cli/
│  └─ Menu.java
├─ dto/
│  ├─ GutendexAuthorDTO.java
│  ├─ GutendexBookDTO.java
│  └─ GutendexResponseDTO.java
├─ model/
│  ├─ Autor.java
│  └─ Livro.java
├─ repository/
│  ├─ AutorRepository.java
│  └─ LivroRepository.java
├─ service/
│  ├─ CatalogoService.java
│  └─ GutendexClient.java
└─ LiterAluraApplication.java

----------------------------------------
## Observações do desafio
- Uso de Jackson com @JsonIgnoreProperties e @JsonAlias nos DTOs;
- Chamadas HTTP com HttpClient nativo do Java;
- JPA + PostgreSQL para persistência;
- Para evitar erro de LazyInitialization, a listagem de livros roda em transação read-only.

----------------------------------------
## Erros comuns (e soluções rápidas)
- Falha ao conectar no banco: confira URL, usuário, senha e se o PostgreSQL está rodando;
- Lista de livros quebra com LazyInitialization: confirmar que o método de listagem está com @Transactional(readOnly = true);
- Aviso de LF/CRLF no Git (Windows): é normal; se quiser padronizar, crie .gitattributes.

----------------------------------------
## Commits úteis (histórico organizado)
- feat: buscar livro por título e salvar
- feat: listar livros e autores
- feat: autores vivos em determinado ano
- feat: contagem de livros por idioma
- refactor: JSON com Jackson (@JsonIgnoreProperties/@JsonAlias)
- fix: evitar LazyInitialization na listagem
- docs: adiciona README

----------------------------------------
## Créditos
Desafio LiterAlura — Alura + Oracle ONE.
API: Gutendex.
Projeto feito para estudo (passo a passo, dev iniciante e aprendendo). :)
