# Bex Challange

Aplicação desenvolvida para calcular a melhor rota de viagem dada uma origem e destino.

## Getting Started

Algumas instruções a seguir serão passadas para iniciar a aplicação.

### Requisitos

1. JDK 8+
2. Apache Maven 3.6.x

Para saber se a máquina possui  o Java instalado basta executar o comando abaixo:

```shell
java -version
```

Para saber se a máquina possui o Maven instalado basta executar o comando abaixo:

```shell
mvn -v
```

### Execução do programa

1. ` cd bex-challange-master`
2. ` mvn clean install`
2. ` java -jar target\bex-challange-0.0.1.jar input-routes.csv`

Nota: o arquivo **input-routes.csv** deve estar na mesma pasta, caso contrário deverá ser informado a localização, exemplo:

```shell
java -jar target\bex-challange-0.0.1.jar E:\bex-challange\input-routes.csv
```

## Estrutura do Projeto

 **Foi utilizado a estrutura Java padrão para codificação e Spring Boot para a exposição das APIs**

* Classes Java referentes aos componentes gerenciados pelo Spring estão localizado em [`component`](src/main/java/br/com/bexchallange/component) diretório para qualquer component.
* Classes Java referentes às configurações do projeto [`component`](src/main/java/br/com/bexchallange/configuration) diretório para qualquer configuration.
* Classes Java referentes aos controladores, responsáveis por receber as requisições e expor as APIs localizados em [`controller`](src/main/java/br/com/bexchallange/controller) esse diretório é para todos os controloadores criados.
* Classes Java responsáveis por realizar a blindagem de atributos não necessários para a camada services, esta, por sua vez está localizado em  [`dto`](src/main/java/br/com/bexchallange/dto). 
* Classes Java para referir-se às entidades, apesar de não utilizar o JPA, considerei Aresta, Vertice e Routes como entidades para o projeto localizados em  [`entity`](src/main/java/br/com/bexchallange/entity).
* Classes Java para responsáveis por conter as regras de negócios, sendo acessados pelas demais classes, localizado em  [`service`](src/main/java/br/com/bexchallange/service).
* Classes Java para responsáveis por representar funcionalidades com características utilitárias [`util`](src/main/java/br/com/bexchallange/util).
* Arquivos de configuração estão localizados em [`resources`](src/main/resources).

Nota: Endereço do projeto no Github: https://github.com/ccsjesus/bex-challange

## Utilização das APIs

 Uma vez que a aplicação esteja em execução basta acessar o seguinte endereço no navegador: **http://localhost:8080/swagger-ui.html#/**, neste endereço encontra-se as APIs desenvolvidas.
 


