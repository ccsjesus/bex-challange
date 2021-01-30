# Bex Challange

Aplicação desenvolvida para calcular a melhor rota de viagem dada uma origem e destino.

## Getting Started

Algumas instruções a seguir serão passadas para iniciar a aplicação.

### Requisitos

1. JDK 8+
2. Apache Maven 3.2.x

Para saber se a máquina possui  o Java instalado basta executar o comando abaixo:

```shell
java -version
```

### Execução do programa

1. ` cd app`
2. ` java -jar bex-challange.jar input-routes.csv`

Nota: o arquivo **input-routes.csv** deve estar na mesma pasta, caso contrário deverá ser informado a localização, exemplo:

```shell
java -jar bex-challange.jar E:\bex-challange\input-routes.csv
```

## Estrutura do Projeto

 **Foi utilizado a estrutura Java padrão para codificação e Spring Boot para a exposição das APIs**

* O código Java referente ao componentes gerenciados pelo Spring estão localizado em  [`component`](src/main/java/br/com/bexchallange/component) diretório para qualquer component.
* Java code for Helper Classes are located under [`helper`](src/main/java/com/intuit/developer/sampleapp/crud/helper) directory for each entitiy
* Java code for QBO DataService object are located under [`qbo`](src/main/java/com/intuit/developer/sampleapp/crud/qbo) directory 
* Config files are located in the [`resources`](src/main/resources) directory

[ss1]: https://help.developer.intuit.com/s/samplefeedback?cid=9010&repoName=SampleApp-CRUD-Java
