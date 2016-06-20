package com.xmedika.br.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.stereotype.Service;

import com.xmedika.br.domain.Enderecos;
import com.xmedika.br.domain.PessoaEndereco;
import com.xmedika.br.domain.Pessoas;
import com.xmedika.br.infrastructure.DTO.PessoasEnderecosDTO;
import com.xmedika.br.repository.EnderecosRepository;
import com.xmedika.br.repository.PessoaEnderecoRepository;
import com.xmedika.br.repository.PessoasRepository;

@Service
@Transactional
public class PessoasService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PessoasRepository repository;
	
	@Autowired
	private EnderecosRepository repositoryEndereco;

	@Autowired
	private CounterService conterService;
	
	@Autowired
	private PessoaEnderecoRepository repositoryPE;

	public Pessoas create(Pessoas pessoas) {
		conterService.increment("numeros.requisicoes.method.Pessoas.create");
		if (pessoas == null) {
			logger.error("Error! Objeto Pessoa nao pode ser nulo");
			throw new EntityExistsException("Error! Objeto Pessoa nao pode ser nulo");
		}
		return repository.save(pessoas);
	}

	public List<Pessoas> findAll() {
		conterService.increment("numeros.requisicoes.method.Pessoas.findAll");
		List<Pessoas> listPessoas = repository.findAll();
		return listPessoas;
	}
	
	public Pessoas update(Pessoas pessoas) {
		conterService.increment("numeros.requisicoes.method.Pessoas.update");
		if (pessoas.getId() == 0) {
			logger.error("Error! Id pessoa nao foi encontrado");
			throw new EntityExistsException("Error! Id pessoa nao foi encontrado");
		}
		return repository.save(pessoas);
	}
	
	public void delete(long idPessoa) {
		conterService.increment("numeros.requisicoes.method.Pessoas.delete");
		if (idPessoa == 0) {
			logger.error("Error! Id pessoa nao foi encontrado");
			throw new EntityExistsException("Error! Id pessoa nao foi encontrado");
		}
		
		List<Enderecos> listEnderecos = repositoryPE.findAllEnderecosByPessoaId(idPessoa);
		repositoryPE.deleteByPessoas(repository.findOne(idPessoa));
		repository.delete(idPessoa);
		listEnderecos.forEach(e -> {
			repositoryEndereco.delete(e.getId());
		});
		
	}
	
	public Pessoas findById(long idPessoa) {
		conterService.increment("numeros.requisicoes.method.Pessoas.findById");
		if (idPessoa == 0) {
			logger.error("Error! Id pessoa nao foi encontrado");
			throw new EntityExistsException("Error! Id pessoa nao foi encontrado");
		}
		return repository.findOne(idPessoa);
	}
	
	public List<PessoasEnderecosDTO> findAllPessoasEnderecos(){
		List<PessoasEnderecosDTO> listAll = new ArrayList<>();
		List<PessoaEndereco> listPE = repositoryPE.findAll();
		listPE.forEach(p -> {
			PessoasEnderecosDTO peDTO = new PessoasEnderecosDTO() ;
			peDTO.setListEnderecos(repositoryPE.findAllEnderecosByPessoaId(p.getPessoas().getId()));
			peDTO.setPessoa(p.getPessoas());
			listAll.add(peDTO);
		});
		return listAll;
	}
}
