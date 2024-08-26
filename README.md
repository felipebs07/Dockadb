
# DockaDB API - Localização

## Descrição

O **DockaDB API** é uma API desenvolvida com **Spring Boot**, **MongoDB** e **Docker** que oferece funcionalidades de pesquisa por localizações. Com essa API, é possível consultar dados sobre continentes, países, regiões do Brasil, estados e municípios brasileiros. O projeto é automatizado com **GitHub Actions** para CI/CD.

## Tecnologias Utilizadas

-   **Spring Boot**: Framework Java para a criação de aplicações web.
-   **MongoDB - Atlas**: Banco de dados NoSQL utilizado para armazenar as informações geográficas.
-   **Docker**: Ferramenta de containerização usada para padronizar e isolar o ambiente de desenvolvimento e produção.
-   **GitHub Actions**: Automação de pipeline para CI/CD, garantindo qualidade e consistência do código em cada commit e PR.


## Funcionalidades

-   Consultar **continentes** (UN 49 formatação level 1 e level 2).
-   Consultar **regiões do Brasil**.
-   Consultar **estados** do Brasil.
-   Consultar **cidades** do Brasil.

## Como Executar o Projeto

### Pré-requisitos

-   **Docker** e **Docker Compose** instalados.
-   **Java 17** (Recomendado) ou superior instalado.
-   **Maven** instalado.
-   **Mongo DB - Atlas** ter conexão com os bancos para gerar as collections

### Passo a Passo

1.  Clone o repositório:
    `git clone https://github.com/felipebs07/Dockadb.git` 
    
2.  Navegue até o diretório do dockadb-api e gere um arquivo .env, dessa forma, cada ponto de interrogação precisa ser substituído com os dados do Database e conexões criadas por vocês no mongodb atlas:

		    MONGO_DB_DATABASE=?
    		MONGO_DB_USERNAME=?
    		MONGO_DB_PASSWORD=?
    		MONGO_DB_PARAMS=?    Exemplo: retryWrites=true&w=majority&appName=APP
    		SPRING_PROFILES_ACTIVE=prod

3.  Configurar o compilador para rodar o profile dev
 
	    Comando para acessar:   -Dspring.profiles.active=dev
	    
4. **Avisos sobre docker**
	O Docker está configurado para ser buildado  e verificado pelo git action, para funcionar localmente é necessário alguns ajustes:
		1. Crie um arquivo .env na pasta principal onde esta localizado o docker-compose, e adicione após a linha environment nos services o comando:  `env_file:   .env`
		2. Adicione no dockerfile que esta no diretório do dockadb-api, coloque logo abaixo do **COPY target** o comando: `COPY .env /app/.env`
		
4. Porta de **desenvolvimento** é localhost:8000
5. Porta de **produção** é localhost:8008
	
	Se todos esses passos foram seguidos corretamente e os dois arquivos .env foi configurado da forma correta com os dados do mongodb, o docker irá subir corretamente e pode utilizar o postman para testar.

## Endpoints

### Continentes

-   **GET** `/continente/buscarGeofrafiaRegiao`: Retorna todos os continentes.

### Países

-   **GET** `/pais`: Retorna todos os países.

### Estados do Brasil

-   **GET** `/api/estado`: Retorna todos os estados do Brasil.

### Regiões do Brasil

-   **GET** `/estado/buscarRegioes`: Retorna todas as regiões do brasil.


### Municípios do Brasil

-   **GET** `/municipio`: Retorna todas as cidades de um estado específico.

Na pasta principal do projeto tem um modelo do postman com **todas as apis parametrizadas,** recomendo utilizar para evitar algum tipo de erro por causa parâmetro obrigatório em algumas apis, o nome do arquivo é **Dockadb.postman_collection.json**.

## CI/CD

O projeto utiliza **GitHub Actions** para automatizar o processo de integração contínua e entrega contínua (CI/CD). O pipeline realiza os seguintes passos:

1.  Build do projeto quando realizado commit na main ou na hora de solicitar um merge para verificar se está tudo certo.
2. Validações sobre commits na main são recusadas quando ocorrem alterações envolvendo os arquivos do Docker ou a pasta . github automaticamente.

## Contribuições

Contribuições são bem-vindas! Siga as etapas abaixo para colaborar:

1.  Faça um fork do repositório.
2.  Crie um branch para sua feature (`git checkout -b feature/nova-feature`).
3.  Commit suas alterações (`git commit -m 'Adiciona nova feature'`).
4.  Envie para o branch (`git push origin feature/nova-feature`).
5.  Abra um Pull Request.

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detalhes.