<div align="center">

# Magic Api - ZAPPTS

<img src="./magic.jpg" width="150px">

Esse repositório se trata do teste técnico de backend para empresa [Zappts](https://www.zappts.com.br/).
O teste foi desenvolvido em Java com SpringBoot e MySQL para persistencia de dados..

</div>

## Descrição
O teste consiste em construir uma API onde a pessoa usuaria, pode adicionar um ou mais decks e dentro desses decks ela pode ter as cartas do jogo Magic the gathering que estão cadastradas.

## Instruções para consumir a API:

- Clone o repositório em sua máquina e instale as dependencias com o comando `mvn install`.
- Utilize o comando `mvn spring-boot:run` para iniciar aplicação.
- Aplicação utilizará a porta `8080` do localhost.
- É necessário o serviço do MySQL funcionando em sua máquina para rodar localmente.
- No arquivo `application.properties` altere o `username` e o `password` para o qual você utiliza em seu MySQL localmente.
- Também é possivel acessar a API pelo link: https://zappts-magic-api.herokuapp.com/ 
- API possui as seguintes rotas:
    - POST, GET: `/cards`
    - GET, DELETE: `/cards/{cardId}`
    
    - POST, GET: `/user`
    - GET, DELETE `/user/{useId}`
    - POST: `/user/{userId}/decks`
    - PUT, DELETE `/user/{userId}/decks{deckName}`
    - POST, PUT, DELETE `/{userId}/decks/{deckName}/cards/{cardId}`

    - GET: `/decks`
    - GET: `/decks/{deckId}` 

## Rotas:

### CARDS:

#### POST `/cards`:
- API permite que seja criado um novo Card através do método POST no endpoint `/cards` passando no body um json no formato:
```json
    {
      "name": "String.",
      "edition": "String.",
      "language": "String, e tem que ser portuguese, english ou japanese",
      "foil": "boolean",
      "price": "double e tem que ser maior que 1"
    }
```
*Observação: para essa rota especifica, existe um seeder para popular o banco, que cadastra 16 cartas quando a aplicação é inicializada pela primeira vez.

#### GET `/cards` e `/cards/{cardId}`:
- API permite que seja possivel listar todas os Cards registrados através dp método GET no endpoint `/cards`, e listar um Card especifico com o endpoint `/cards/{cardId}`, passando um cardId de um card já cadastrado.

#### DELETE `/cards/{cardId}`:
- A API permite deletar um card especifico com o endpoint `/cards/{cardId}`, passando um cardId de um card já cadastrado.


### USERS:

#### POST `/user`:
- API permite que seja criado um novo user através do método POST no endpoint `/user` passando no body um json no formato:
```json
    {
      "name": "String.",
      "userName": "String e não pode ser repetido com algum outro username ja cadastrado."
    }
```

#### GET `/user` e `/user/{userId}`:
- API permite que seja possivel listar todas os users registrados através dp método GET no endpoint `/user`, e listar um user especifico com o endpoint `/user/{userId}`, passando um userId de um user já cadastrado.

#### DELETE `/user/{userId}`:
- A API permite deletar um user especifico com o endpoint `/user/{userId}`, passando um userId de um user já cadastrado.

#### POST `/user/{userId}/decks`:
- API permite que seja criado um novo deck, que será relacionado com o usuario que está adicionado, através do método POST no endpoint `/user/{userId}/decks` passando no body um json no formato:
```json
    {
      "deckName": "String, e nao pode ser repetido com outro deck que o usuario tenha relacionado."
    }
```
#### PUT `/user/{userId}/decks/{deckName}`:
- API permite que seja editado um deck, que esteja relacionado com o usuario que está adicionado, através do método PUT no endpoint `/user/{userId}/decks/{deckName}` passando o deckName na Url, e no body um json no formato:
```json
    {
      "deckName": "String, e nao pode ser repetido com outro deck que o usuario tenha relacionado."
    }
```

#### POST `/user/{userId}/decks/{deckName}`:
- A API permite deletar um deck especifico do usuario relacionado com userId com o endpoint `/user/{userId}/decks/{deckName}`, passando o deckName já cadastrado para o usuario.


- POST, PUT, DELETE `/{userId}/decks/{deckName}/cards/{cardId}`

#### POST `/{userId}/decks/{deckName}/cards/{cardId}`:
- API permite que seja adicionado ao um deck de um determinado usuario, um card que já exista na base de dados, que será relacionado com o usuario que está adicionado, através do método POST no endpoint `/{userId}/decks/{deckName}/cards/{cardId}`, passando na url um userId já cadastrado, um deckname relacionado com o userId e um cardId já cadastrado, não há necessidade de passar nada no body.

#### PUT `/{userId}/decks/{deckName}/cards/{cardId}`:
- API permite que seja editado o campo `price` de um card já relacionado com o deck do usuário especifico, através do método PUT no endpoint `/{userId}/decks/{deckName}/cards/{cardId}`, passando na url um userId já cadastrado, um deckname relacionado com o userId e um cardId já cadastrado e relacionado com o deck do usuário, e passando no body um json no formato:
```json
    {
      "price": "double, com valor minimo de 1.00"
    }
```

#### DELETE `/{userId}/decks/{deckName}/cards/{cardId}`:
- A API permite deletar um card relacionado ao um deck especifico do usuario relacionado com userId com o endpoint `/{userId}/decks/{deckName}/cards/{cardId}`, passando na url userId, o deckName e o cardId.

### DECKS:

#### GET `/decks` e `/decks/{decksId}`:
- API permite que seja possivel listar todas os decks registrados através dp método GET no endpoint `/decks`, e listar um deck especifico com o endpoint `/decks/{decksId}`, passando um deckId de um deck já cadastrado.

### Próximos passos na API:
- Aprimoramento nos teste,
- Mais testes;
- Criar uma rota, onde quando selecionado um deck especifico, é possivel ordenar por preço ou ordem alfabetica de nome;
- Uma rota para login;
- Implementação do JWT;
- "Dockerizar" a aplicação;
- Traduzir o nome das cartas de ingles para portugues automaticamente.

### Considerações finais:
<div align="center">

    Ainda preciso me aprimorar muito em Java, principalmente na parte de teste, é uma linguagem relativamente nova para mim.
    Agradeço muito a oportunidade de estar participando no processo seletivo da Zappts.

    Dúvidas ou sugestões me contate por:
    - Linkedin: https://www.linkedin.com/in/guicouto90/
    - Email: gui.couto90@yahoo.com.br
    
</div>