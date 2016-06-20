	angular.module("pessoasEnderecos").controller("pessoasCtrl", function ($scope, pessoasService, $location) {
			$scope.listPessoas = [];
			$scope.pessoa = "";
            var pessoaObj = {
				nome : "",
				cpf : "",
				dataNasc : "",
				sexo:""
		};

			$scope.carregarPessoas = function (id) {
				pessoasService.getPessoasById(id).success(function (data) {
					$scope.listPessoas = [];
					$scope.showIcon = false;
					$scope.pessoa = data;
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
			};

			$scope.carregarAllPessoas = function () {
				pessoasService.getAllPessoas().success(function (data) {
					$scope.pessoa ="";
					$scope.showIcon = false;
					$scope.listPessoas = data;					
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
			}

			$scope.addPessoa = function (pessoa) {
				 var day = pessoa.dataNasc.slice( 0, 2 );
                 var month = pessoa.dataNasc.slice( 2, 4 );
                 var year =  pessoa.dataNasc.slice( 4, 8 );
                 pessoa.dataNasc  = new Date( day + "/" + month + "/" + year);

			   pessoasService.addPessoa(pessoa).success(function (data) {
 		   		alert("Pessoa inserida com sucesso");
 		   		delete $scope.pessoa;
 		   		$scope.pessoaCadastroForm.$setPristine();
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
			}

			$scope.updatePessoa = function (pessoa) {
				 var day = pessoa.dataNasc.slice( 0, 2 );
                 var month = pessoa.dataNasc.slice( 2, 4 );
                 var year =  pessoa.dataNasc.slice( 4, 8 );
                 pessoa.dataNasc  = new Date( day + "/" + month + "/" + year);

			   pessoasService.updatePessoa(pessoa).success(function (data) {
 		   		alert("Pessoa atualizada com sucesso");
 		   		delete $scope.pessoa;
 		   		$scope.pessoaCadastroForm.$setPristine();
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
			}

			$scope.deletePessoa = function (id){
              pessoasService.deletePessoa(id).success(function (data) {
 		   		alert("Pessoa deletada com sucesso");
 		        $scope.carregarAllPessoas();
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});

			}

			$scope.ordenarPor = function (campo) {
				$scope.criterioDeOrdenacao = campo;
				$scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
			};

		if ( $location.search().hasOwnProperty( 'id' ) ) {
	 	     var id = $location.search().id;
     	     $scope.carregarPessoas(id);
		}

			$scope.maskCpf = '999.999.999-99'
			$scope.maskDate = '99/99/9999'
			$scope.showIcon = true;
});