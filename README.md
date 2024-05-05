POC - Mensageria com RabbitMQ

## Descrição
Esse projeto tem como objetivo realizar uma prova de conceito(POC), publicando eventos e consumindo as mensagens do
RabbitMq de maneira bem simplificada.

Prepara automaticamente o ambiente do rabbitmq (Infra as code), ou seja, as queues, exchanges e bindings necessárias
para executar a aplicação.

E o banco de dados é em memória para entrega mais rápida e focar no objetivo da POC (RabbitMq).

### Nota
O banco de dados é em memória, então, ao reiniciar a aplicação os dados são perdidos. Faça o purge no RabbitMq para
que o listener não fique consumindo mensagens antigas e lançando exceções desnecessárias.

## Tecnologias
Java 17+

Maven (para gerenciamento de dependências)

Spring Boot (para desenvolvimento da API REST)

Docker & Docker Compose (para subir o ambiente do RabbitMQ)

## Executando o projeto
Baixar e acessar o projeto pelo terminal
Com o docker em execução, executar o docker compose
- `docker-compose up -d ou docker compose up -d`

Executar no terminal o comando para build do projeto com o maven
- `mvn clean package -Dmaven.test.skip`

Executar a aplicação via java -jar
- `java -jar target/*.jar`

## Endpoints
Salvar imagem:
- POST - http://localhost:8080/images/upload?provider=gcp
  - `provider (opcional)` - Provedor de armazenamento (gcp ou aws)
  - `formData`:
    - `file` - Arquivo de imagem

Listar imagens:
- GET - http://localhost:8080/images

Recuperar uma image:
- GET - http://localhost:8080/images/{id}
  - `id` - Id da imagem
