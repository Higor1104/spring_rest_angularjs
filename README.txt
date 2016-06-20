### Front End ##

-Angular 1.5
-Bootstrap
-Api correios
-Node.js
-Nginx

-Estrutura do codigo
Specific Style

# Como executar

- Para realizar a integracao com o back end foi necessario a integracao com  Node.js e Nginx para o redirecionamento das rotas 

- Entrar na pasta do projeto e executar npm start (Necessario ter instalado na maquina Node.js)
- O arquivo de configuracao do nginx esta na pasta setup com o nome "xmedika.conf", colocar o mesmo na pasta config do nginx e executar o comando
nginx -c conf\xmedika.conf

- Acessar o link no browser
  http://localhost/#/

- Desing Patterns usado
   - Factory, directives, scope, controller, rote...

- Uso de
  - diretivas, mask, filter, orderby, ng-required e varios outros..

- Funcoes da aplicacao
  - Cadastrar, Remover, Editar, listar tudo, listar por id, pesquisa com filtro, mascaras em alguns campos,
validacao de formulario, auto preencimento de endereco com o valor do cep, alertas....

- Manual de uso
  1 - Menu Cadastro Pessoa: Cadatrar uma nova pessoa
  2 - Menu Manutenca Pessoa: Lista pessoa por id, Lista toda as pessoas, Pesquisa Pessoa, Na tabela: Remove pessoa e edita os dados da pessoa.

  3- Menu Cadastro Endereco: Seleciona a pessoa e cadastra um Novo endereco, "auto preenchimento com cep"
  4 - Menu Manutenca Endereco: Seleciona a pessoa  e lista todos os enderecos, Na tabela: Remove endereco e edita os do endereco.

## Back End ##

-Spring 4
-Swagger
-Endpoints 
-Actuator
-JUnit
-Mockito
-jpa
-dao
-dto
-hibernate
-Spring data
-Jpa Repository

-Banco de dados
Postgree, o aruqivo sql para a criacao da base de dados esta na pasta setup com o nome banco_de_dados.sql

- Como executar
 mvn spring-boot:run ou pelo plugin do eclipse

- Design Patterns
-MVC
-Actuator
-Dependency injection

-Documentacao da api
 A documentacao foi desenvolvida utilizando swagger - http://localhost:8080/swagger-ui.html

-Entpoits
Metrics - permite ver quantas vezes cada servico foi requerido : http://localhost:8080/actuators/metrics
*Consulte o swagger para visualizar todos os endpoits.

-Testes
  - Teste unitario de funcoes usando Junit
  - Teste de rest utilizando Junit e Mock para simulacao de injecao de obejtos

- Tratamento de execptios
  A aplicacao trata as exceptions

-OBS nao consegui fazer a parte de comentarios do codigo, mas o mesmo eh auto explicativo.

Qualquer duvida estou a disposicao.









