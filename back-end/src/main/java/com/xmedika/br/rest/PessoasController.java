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

import com.xmedika.br.domain.Pessoas;
import com.xmedika.br.infrastructure.DTO.PessoasEnderecosDTO;
import com.xmedika.br.service.PessoasService;

@RestController
@RequestMapping("/pessoa")

public class PessoasController extends BaseController {
	
	@Autowired
	private PessoasService service;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	ResponseEntity<Pessoas> createPessoa(@RequestBody Pessoas pessoas) {
		  return new ResponseEntity<Pessoas>(service.create(pessoas), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	ResponseEntity<Pessoas> updatePessoa(@RequestBody Pessoas pessoas) {
		  return new ResponseEntity<Pessoas>(service.update(pessoas), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pessoas>> getAllPessoas (){
		List<Pessoas> listPessoas = service.findAll();
		if ( listPessoas.isEmpty())
			return new ResponseEntity<>(listPessoas, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(listPessoas, HttpStatus.OK);
	}
	
	@RequestMapping(value ="/{idPessoa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoas> findByIdPessoa (@PathVariable long idPessoa){
		Pessoas pessoa = service.findById(idPessoa);
		if ( pessoa == null)
			return new ResponseEntity<>(pessoa, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{idPessoa}", method = RequestMethod.DELETE)
	void deletePessoa(@PathVariable long idPessoa) {
		  service.delete(idPessoa);	  
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PessoasEnderecosDTO>> getAllPessoasEnderecos (){
		List<PessoasEnderecosDTO> listPessoasEndereco = service.findAllPessoasEnderecos();
		if ( listPessoasEndereco.isEmpty())
			return new ResponseEntity<>(listPessoasEndereco, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(listPessoasEndereco, HttpStatus.OK);
	}

}
