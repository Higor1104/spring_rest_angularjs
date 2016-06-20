angular.module("pessoasEnderecos").controller("enderecoCtrl", function ($scope, enderecoService, cepService, $location, pessoasService) {
    $scope.listPessoas = [];
    $scope.carregarAllPessoas = function () {
				pessoasService.getAllPessoas().success(function (data) {
					$scope.listPessoas = data;					
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
			}

    $scope.completeWithCep = function (cep){
        	cepService.getCepApi(cep).success(function (data) {
					$scope.endereco.bairro = data.bairro;
					$scope.endereco.estado = data.uf;
					$scope.endereco.cidade = data.localidade;
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
			}
   

    $scope.addEndereco = function (endereco, idPessoa){
      	enderecoService.addEndereco(endereco,idPessoa).success(function (data) {
				alert("Endereco inserido com sucesso");
 		   		delete $scope.endereco;
 		   		$scope.enderecoCadastroForm.$setPristine();
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
    }

    $scope.updateEndereco = function (idEndereco){
      	enderecoService.updateEndereco(idEndereco).success(function (data) {
				alert("Endereco atualizado com sucesso");
 		   		delete $scope.singleEndereco;
 		   		$scope.enderecoCadastroForm.$setPristine();
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
    }

      $scope.getEnderecoById = function (idPessoa){
      	enderecoService.getEnderecoByPessoaId(idPessoa).success(function (data) {
				$scope.enderecos = data;
				$scope.enderecos.idPessoa = idPessoa;
				console.log(data + "oi");
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
    }

       $scope.deleteEndereco = function (idPessoa, idEndereco){
      	enderecoService.deleteEndereco(idPessoa,idEndereco).success(function (data) {
				alert("Endereco removido com sucesso");
 		   		$scope.getEnderecoById(idPessoa);
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
    }

    if ( $location.search().hasOwnProperty( 'idEndereco' ) ) {
	 	     var idEndereco = $location.search().idEndereco;
	 	     	enderecoService.getEnderecoById(idEndereco).success(function (data) {
				$scope.singleEndereco = data;
				}).error(function (data, status) {
					$scope.message = "Aconteceu um problema: " + data;
				});
		}

    $scope.maskCep = '99999-999';

	$scope.carregarAllPessoas();

});