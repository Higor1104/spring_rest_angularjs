package com.xmedika.br.service;

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
import com.xmedika.br.repository.EnderecosRepository;
import com.xmedika.br.repository.PessoaEnderecoRepository;
import com.xmedika.br.repository.PessoasRepository;

@Service
@Transactional
public class EnderecosService {
	
	@Autowired
	private EnderecosRepository repository;
	
	@Autowired
	private PessoaEnderecoRepository repositoryPE;
	
	@Autowired
	private PessoasRepository repositoryPessoas;
	
	@Autowired
	private CounterService conterService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public Enderecos create(long idPessoa, Enderecos endereco){
		conterService.increment("numeros.requisicoes.method.Endereco.create");
		if (endereco == null | idPessoa == 0) {
			logger.error("Error! Objeto Endereco ou IdPessoa nao pode ser nulo");
			throw new EntityExistsException("Error! Error! Objeto Endereco ou IdPessoa nao pode ser nulo");
		}
		Enderecos enderecos = repository.save(endereco);
		PessoaEndereco pe = new PessoaEndereco();
		pe.setEnderecos(enderecos);
		pe.setPessoas(repositoryPessoas.findOne(idPessoa));
		repositoryPE.save(pe);
		return enderecos;
	}
	
	public List<Enderecos> findAllByPessoaId(long idPessoa){
		conterService.increment("numeros.requisicoes.method.Endereco.findAllByPessoaId");
		if (idPessoa == 0) {
			logger.error("Error!  IdPessoa nao pode ser nulo");
			throw new EntityExistsException("Error!  IdPessoa nao pode ser nulo");
		}
		List<Enderecos> listEnderecos = repositoryPE.findAllEnderecosByPessoaId(idPessoa);
		return listEnderecos;
	}
	
	public Enderecos findById(long idEndereco){
		conterService.increment("numeros.requisicoes.method.Endereco.findById");
		if (idEndereco == 0) {
			logger.error("Error!  IdEndereco nao pode ser nulo");
			throw new EntityExistsException("Error!  IdEndereco nao pode ser nulo");
		}
		
		return repository.findOne(idEndereco);
	}
	
	public Enderecos update(Enderecos enderecos) {
		conterService.increment("numeros.requisicoes.method.Pessoas.update");
		if (enderecos.getId() == 0) {
			logger.error("Error! Objeto Endereco ou IdPessoa nao pode ser nulo");
			throw new EntityExistsException("Error! Objeto Endereco ou IdPessoa nao pode ser nulo");
		}
		return repository.save(enderecos);
	}
	
	public void delete(long idPessoa, long idEndereco){
		conterService.increment("numeros.requisicoes.method.Endereco.delete");
		if (idEndereco == 0 | idPessoa == 0) {
			logger.error("Error! Objeto Endereco ou IdPessoa nao pode ser nulo");
			throw new EntityExistsException("Error!Objeto Endereco ou IdPessoa nao pode ser nulo");
		}
		PessoaEndereco pe = new PessoaEndereco();
		pe.setEnderecos(repository.findOne(idEndereco));
		pe.setPessoas(repositoryPessoas.findOne(idPessoa));
		repositoryPE.delete(pe);
		repository.delete(idEndereco);
	}
	
	
}
