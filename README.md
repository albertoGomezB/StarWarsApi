# StarWarsApi 👽

This project get data from the Star Wars public api using Spring Boot as a framework, and Feign as a client to consume the api.
The Code is refactored to use the Solid principles and clean code.

It has a controller that receives a name of a character for parameter and returns the name of the character, the name of the planet where the character was born and then, obtain all the information modified by the character, including the fastest vehicle or starship the character drives.

## Technologies ☕
- Java 17
- Spring Boot 3.2.4
- -  Actuator, Cache, Web, Devtools, Test
- Feign
- Lombok
- OpenApi
- Junit 5, Mockito
- Docker

## Installation and execution

1. Clone the repository

```bash
git clone https://github.com/albertoGomezB/StarWarsApi.git
```

2. Build the project

```bash
mvn clean install
```

3. Create the docker image

```bash
docker build -t 'userDockerHub' starwarsapi .
```

4. Run the docker image

```bash
docker run -p 8080:8080 'userDockerHub'/starwarsapi
```

5. Tests
```bash
mvn test
```

## Ports ⚓

- 8080: Application port

## Endpoints 📡

- GET /swapi-proxy/person-info/{name} 

    - Example: http://localhost:8080/swapi-proxy/person-info?name=Luke Skywalker

## Api Documentation 📖

- http://localhost:8080/star-wars/doc


## Author 🧙‍♂️

~ albertoGomezB 
   









