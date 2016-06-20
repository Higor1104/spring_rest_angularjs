angular.module("pessoasEnderecos").factory("enderecoService", function ($http, config) {

	var _addEndereco = function (endereco,idPessoa) {
		return $http.post(config.baseUrl+"/pessoa/"+idPessoa+"/endereco",endereco);
	};

	var _updateEndereco = function (endereco) {
		return $http.put(config.baseUrl+"/endereco/",endereco);
	};

	var _getEnderecoByPessoaId = function (idPessoa) {
		return $http.get(config.baseUrl+"/pessoa/"+idPessoa+"/endereco");
	};

	var _getEnderecoById = function (idEndereco) {
		return $http.get(config.baseUrl+"/endereco/"+idEndereco);
	};

	var _deleteEndereco = function (idPessoa, idEndereco) {
		return $http.delete(config.baseUrl+"/pessoa/"+idPessoa+"/endereco/"+idEndereco);
	};




	return{
		addEndereco: _addEndereco,
		getEnderecoByPessoaId: _getEnderecoByPessoaId,
		deleteEndereco: _deleteEndereco,
		getEnderecoById: _getEnderecoById,
		updateEndereco: _updateEndereco
	};

});