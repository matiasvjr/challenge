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

## Exemplos de chamada de API:
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

# Decisões técnicas de design

A aplicação foi desenvolvida de maneira que seu código possa ser facilmente compreendido e alterado afim de suportar novas funcionalidades.

## Lógica do validador de senha:
A interface **Validator** possui um método **isValid(*password*)** que retorna ***true/false*** indicando se uma senha é válida.\
Cada implementação dessa interface representa um tipo de regra que deve ser respeitada para que a senha seja válida.
- **MinimumSizeValidator**: É o validador que verifica a quantidade mínima de caracteres em uma senha.
- **NonRepeatingValidator**: É o validador que verifica se não existem caracteres repetidos.
- **AtLeastOneValidator**: É um validador um pouco mais abrangente que verifica se a senha possui pelo menos um dos caracteres definidos em um determinado alfabeto.
Nesse caso foram usadas diferentes instâncias do mesmo validador apenas modificando o alfabeto.
- **CompositeValidator**: É um tipo de validador especial que agrega outros validadores mas funciona como se fosse apenas um.

Inicialmente considerei a possibilidade da utilização de expressões regulares para a validação da senha.
Porém a idéia foi descartada pois geralmente regex são difíceis de entender e isso acabaria prejudicando a facilidade de manutenção no futuro.

Utilizando esse modelo, a validação de senha fica simples de entender e fácil de se extender.
Surgindo uma nova regra de validação, bastaria apenas a criação de uma nova implementação da interface.

As regras de validação são colocadas juntas na classe **ApplicationConfiguration** através do **CompositeValidator**.
E em seguida o validador é passado para quem quer que precise utilizá-lo.

## Camada de serviço (PasswordService):
Esse serviço tem apenas o objetivo de verificar se a senha é válida fazendo uso do validador, sem necessidade de saber como ele funciona e quais são exatamente as suas regras.

Em uma possível evolução do sistema, por exemplo, essa classe poderia ser facilmente extensível para funcionar como um proxy e coletar métricas sobre as senhas testadas.
Sem qualquer alteração nos validadores.

## API web (PasswordController):
O endpoint ***/password/validation*** recebe como parâmetro a senha que deve ser validada.

Utilizei o método POST pois essa api está mais para um RPC do que para um serviço REST. \
No caso de uma chamada válida, o retorno será sempre um status 200 (independente se a resposta for *true* ou *false*).
Pois mesmo retornando *false*, o objetivo foi cumprido com sucesso.

Para o caso de uma chamada onde o parâmetro esteja ausente ou com nome errado, o status será 400 pois o erro foi do lado do cliente.
