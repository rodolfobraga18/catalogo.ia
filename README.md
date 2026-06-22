# 🧠 Catálogo Inteligente API (Busca Semântica com IA)

Uma API RESTful construída em Java e Spring Boot que substitui a pesquisa tradicional baseada em palavras-chave por **Busca Semântica**. Em vez de buscar por strings exatas, o sistema utiliza Inteligência Artificial para entender a **intenção** e o **contexto** da pesquisa do usuário, retornando os produtos mais relevantes através de cálculos matemáticos de vetores (*Embeddings*).

## 💡 O Problema que Resolve
Em catálogos tradicionais, se um usuário pesquisa por "tela fluida para codar", um monitor cadastrado como "Monitor Gamer 144hz" não apareceria. Esta API resolve isso traduzindo o texto para vetores matemáticos, permitindo que o banco de dados encontre itens pelo seu **significado** e não apenas pela sua grafia.

---

## 🚀 Funcionalidades
* **Cadastro Inteligente:** Ao receber um novo produto, a API intercepta a requisição, envia a descrição para um modelo de I.A. local e gera automaticamente um vetor de 768 dimensões.
* **Regras de Negócio Automáticas:** Atualização autônoma de status de disponibilidade baseada na quantidade de estoque.
* **Busca Semântica de Alta Performance:** Endpoint de pesquisa que calcula a similaridade do cosseno entre o termo buscado e os produtos do banco de dados, retornando os resultados ordenados por relevância.
* **Proteção de Payload:** Retorno limpo para o front-end, ocultando as matrizes vetoriais complexas.

---

## 🛠️ Tecnologias Utilizadas (Stack)
* **Java 21**
* **Spring Boot 3.x**
* **Spring AI (v2.0.0):** Para orquestração das chamadas de inteligência artificial.
* **Ollama (Modelo: nomic-embed-text):** Para geração de embeddings de forma local, rápida e gratuita (sem depender de créditos da OpenAI).
* **PostgreSQL + pgvector:** Banco de dados relacional equipado com extensão de geometria espacial para armazenamento e cálculo de distâncias vetoriais.
* **Docker:** Para containerização e isolamento do ambiente de banco de dados.

---

## ⚙️ Como a Arquitetura Funciona
1. O Front-end ou o usuário envia um texto via HTTP (ex: Cadastro ou Busca).
2. O **Spring Boot** recebe o JSON e aciona o **Spring AI**.
3. O modelo local **Ollama** converte o texto em um array matemático (*Embedding*).
4. O Spring utiliza um *bypass* arquitetural (`@ColumnTransformer`) para converter o array numa String que o banco compreenda.
5. O **PostgreSQL**, utilizando a extensão `pgvector`, realiza o armazenamento ou a busca por proximidade matemática e devolve o objeto Java.

---

## 📦 Como Executar o Projeto Localmente

### Pré-requisitos
* Java 21 ou superior.
* Maven instalado (ou use o `./mvnw` do projeto).
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) rodando.
* [Ollama](https://ollama.com/) instalado no seu SO.

### Passo a Passo

**1. Suba o Banco de Dados (Docker)**
Abra o seu terminal e execute o comando abaixo para baixar e rodar a imagem customizada do PostgreSQL com suporte a vetores:
```bash
docker run --name pg-vetor -e POSTGRES_USER=rodolfo -e POSTGRES_PASSWORD=123456789 -e POSTGRES_DB=catalogo_db -p 5432:5432 -d ankane/pgvector
