# Password Validator API
API para validação de senhas baseado nas seguintes regras:

```
- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Ao menos 1 caractere especial: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto
```

# Ferramentas necessárias para execução

* Git 2.25.1 (apenas se for clonar o projeto)
* JDK 15+ (testado com OpenJDK)
* Maven 3.6+
* Docker 20.10.6, build 370c289 (ou maior)
* Docker-Compose version 1.27.4, build 40524192 (ou maior)

# Construindo e rodando localmente

```bash
- Baixar o repositório
    git clone https://github.com/malikoski/api-validate-password
- Na pasta onde foi clonado o projeto executar um dos passos abaixo:
```

### Sem docker
````
mvn clean install
java -jar target/validate-password-1.0.jar
````

### Via docker
````
docker-compose -up d
````

# Documentação e testes(rodando local)

## Endpoint: `/api/password`

```
Api Doc: http://localhost:8080/swagger-ui/index.html
   - Obs: É possível testar a API clicando no botão 'Try it out'. 

# curl --location --request POST 'http://localhost:8080/api/password' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'password=aaaaaaaa'

```

# Sobre a API...

* Utilização do framework `Spring Boot` por ser uma ferramenta robusta e bastante utilizada no
  mundo java;
* Utilizando regex, pode-se deixar a cargo do próprio framework para validar já na entrada do endpoint,
com isso não há necessidade criar outra camada(classes de serviço por exemplo) para tratamento de regras. 
  Deve-se apenas configurar a mensagem de retorno quando a validação falhar(ou seja: `false`)
* Foi utilizado para documentar a API o plugin SpringFox. Assim para evitar de 
"poluir" a classe (`PasswordValidatorImplController.java`) com configurações de documentação  optou-se por criar uma    interface (`PasswordValidatorController.java`) onde foram configurados os detalhes da documentação para o plugin 
  e a classe concreta `PasswordValidatorImplController.java` implementando a interface.
* Procurado utilizar solução mais prática com menos codificação, assim gerando mais 
legibilidade e entendimento do código com `Pattern de Regex`(detalhe do pattern abaixo);   
  
````regexp
^(?=.*[a-z])(?=.*[A-Z])(?=.*[\d])(?=.*[!@#$%^&*()-+])(?:([A-Za-z\d!@#$%^&*()-+\S])(?!.*\1)){9,}$

- Detalhando:
   ^ -> início da string
   (?=.*[a-z]) -> procurar caracteres minúsculos
   (?=.*[A-Z]) -> procurar carateres maiúsculos
   (?=.*[\d])  -> procurar caracteres númericos
   (?=.*[!@#$%^&*()-+]) -> procurar caracteres(símbolos) especias
   (?:([A-Za-z\d!@#$%^&*()-+\S]) -> restringir o conjunto de caracteres válidos(invalidando espaços)
   (?!.*\1)) -> invalidará caracteres repetidos
   {9,} -> quantidade mínima de caracteres
   $ -> final da string
   ````

* Como não foi determinado regras específicas para Input / Output para API como por 
exemplo - `Content-type`, ou nome do field que receberá o valor para validação, 
ou mesmo se a senha deveria ser criptografada, foi utilizada a forma mais simples possível:
  
    * `Content-Type: x-www-form-urluncoded`
    * param: `password` - value: `xxxxxxxxxxxx`
    
* Como o retorno é apenas desejado um valor booleano, optou-se também pela forma mais simples:
    * `Content-Type: text\plain`
    * `true` ou `false`
