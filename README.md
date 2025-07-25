# customer-microservice
Post Tech Fiap - Arquitetura e Desenvolvimento em JAVA - Tech Challenger Fase 4

## FIAP - Tech Challenge - Fase 4

### Sistema de gestão para seus estabelecimentos

Nessa quarta fase de entrega o objetivo é desenvolver um backend de Sistema de Gerenciamento de Pedidos Integrado
com Spring e Microsserviços dividido em 6 microserviços.

### Serviço de criação de pedido  
Serviço responsável por realizar o cadastro do cliente

### Como rodar o projeto
Para rodar o projeto completo é necessário baixar os 6 microsserviços e rodar a partir do arquivo docker-compose que se encontra neste repositório de [docker-compose.yml](https://github.com/MaiconFiuza/customer-microservice/blob/main/docker-compose.yml)

#### 1. Fazer o build dos containeres da aplicação:
Executar o seguinte comando:
    
    docker-compose up --build

#### 2. Executar a aplicação através dos containeres criados:
Executar o seguinte comando para inicializar os containeres da aplicação

    docker-compose up


Serviço de customer
Disponível na porta http://localhost:8080/

Link para a documentação das API's do projeto (OpenAPI):
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)



### Cobertura de testes do projeto 
Para rodar a cobertura de testes do projeto é possível pelo comando mvn test, o report com a porcentagem de testes coberto estará no arquivo index dentro de `target\site\jacoco`
<img width="1184" height="445" alt="image" src="https://github.com/user-attachments/assets/090c8947-e899-41b1-8c45-e3d7a880774f" />
