angular.module("pessoasEnderecos").config(function ($routeProvider) {
})
.config(['$routeProvider','$httpProvider', function($routeProvider,$httpProvider) {
  $routeProvider.when('/cadastro_pessoa', {
    templateUrl: 'view/cadastro_pessoa.html'
  });

  $routeProvider.when('/manutencao_pessoas', {
    templateUrl: 'view/manutencao_pessoas.html'
  });

   $routeProvider.when('/editar_pessoa?:id', {
    templateUrl: 'view/editar_pessoa.html'
  });


 $routeProvider.when('/cadastro_endereco', {
    templateUrl: 'view/cadastro_endereco.html'
  });


 $routeProvider.when('/manutencao_endereco', {
    templateUrl: 'view/manutencao_endereco.html'
  });


 $routeProvider.when('/editar_endereco?:idPessoa', {
    templateUrl: 'view/editar_endereco.html'
  });

 $routeProvider.when('/#', {
    templateUrl: 'view/home.html'
  });

 $routeProvider.when('/', {
    templateUrl: 'view/home.html'
  })
  .otherwise({redirectTo: '/'});

}]);