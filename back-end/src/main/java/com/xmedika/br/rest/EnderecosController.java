package com.xmedika.br.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xmedika.br.domain.Enderecos;
import com.xmedika.br.service.EnderecosService;

@RestController
public class EnderecosController extends BaseController {
	
	@Autowired
	private EnderecosService service;
	
	@RequestMapping(value = "/pessoa/{idPessoa}/endereco", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Enderecos>  createEnrececo(@PathVariable int idPessoa, @RequestBody Enderecos endereco){
		return new ResponseEntity<>(service.create(idPessoa, endereco), HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/pessoa/{idPessoa}/endereco", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Enderecos>> getAllEnderecosByPessoaId (@PathVariable long idPessoa){
		List<Enderecos> listEnderecos = service.findAllByPessoaId(idPessoa);
		if ( listEnderecos.isEmpty())
			return new ResponseEntity<>(listEnderecos, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(listEnderecos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/endereco/{idEndereco}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Enderecos> getEnderecoById(@PathVariable long idEndereco){
		Enderecos endereco = service.findById(idEndereco);
		if ( endereco == null)
			return new ResponseEntity<>(endereco, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(endereco, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/endereco",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Enderecos> updateEndereco(@RequestBody Enderecos endereco){
		return new ResponseEntity<>(service.update(endereco), HttpStatus.OK);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/pessoa/{idPessoa}/endereco/{idEndereco}", method = RequestMethod.DELETE)
	void deletePessoa(@PathVariable long idPessoa, @PathVariable long idEndereco) {
		  service.delete(idPessoa, idEndereco);	  
	}
	
	

}
