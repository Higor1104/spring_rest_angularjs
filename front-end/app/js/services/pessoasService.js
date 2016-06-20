angular.module("pessoasEnderecos").factory("pessoasService", function ($http,config) {

	var _getPessoasById = function (id) {
		
		return $http.get(config.baseUrl+"/pessoa/"+id);
	};

	var _getAllPessoas = function () {
		return $http.get(config.baseUrl+"/pessoa");
	};

	var _addPessoa = function (pessoa) {

		return $http.post(config.baseUrl+"/pessoa",pessoa);
	};

	var _updatePessoa = function (pessoa) {

		return $http.put(config.baseUrl+"/pessoa",pessoa);
	};

	var _deletePessoa = function (id) {
		
		return $http.delete(config.baseUrl+"/pessoa/"+id);
	};

	return {
		getPessoasById: _getPessoasById,
		getAllPessoas: _getAllPessoas,
		addPessoa: _addPessoa,
		deletePessoa: _deletePessoa	,
		updatePessoa: _updatePessoa
	};
});