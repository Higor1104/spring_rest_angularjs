angular.module("pessoasEnderecos").factory("cepService", function ($http) {

		var _getCepApi = function (cep) {
		
		return $http.get("https://viacep.com.br/ws/"+cep+"/json/");
	};

	return{
		getCepApi: _getCepApi
	};

});