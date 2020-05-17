# iti - challenge

Disponibilizar um api web para validar se uma senha é válida.

---

## Tecnologias utilizadas

- Java 8
- Docker 17.05 ou superior

---

## Como executar os testes localmente (requer Java instalado):

- Testes Unitários:
```sh
./mvnw test
```

- Testes Integrados:
```sh
./mvnw verify -Pintegration-tests
```


## É possível executar os testes sem a necessidade do Java instalado na máquina, dependendo apenas do Docker:

- Criar um volume para armazenar as dependências, evitando o download a cada novo teste:
```sh
docker volume create --name maven-repo
```

- Testes Unitários:
```sh
docker run -it --rm --name maven-project -v maven-repo:/root/.m2 -v "$(pwd)":/mymaven -w /mymaven openjdk:8-alpine ./mvnw test clean
```

- Testes Integrados:
```sh
docker run -it --rm --name maven-project -v maven-repo:/root/.m2 -v "$(pwd)":/mymaven -w /mymaven openjdk:8-alpine ./mvnw verify -Pintegration-tests clean
```


## Como criar a imagem Docker:
```sh
docker build -t challenge .
```

## Como executar:
```sh
docker run --rm -p 8080:8080 challenge
```

#Exemplos de chamada de API:
```sh
curl -i -X POST "http://localhost:8080/password/validate?password=" # false
curl -i -X POST "http://localhost:8080/password/validate?password=aa" # false
curl -i -X POST "http://localhost:8080/password/validate?password=ab" # false
curl -i -X POST "http://localhost:8080/password/validate?password=AAAbbbCc" # false
curl -i -X POST "http://localhost:8080/password/validate?password=AbTp9\!foo" # false
curl -i -X POST "http://localhost:8080/password/validate?password=AbTp9\!fok" # true
curl -i -X POST "http://localhost:8080/password/validate" # status 400
```

---

# Decisões de design

TODO