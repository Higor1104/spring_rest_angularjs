package com.xmedika.br.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xmedika.br.domain.Enderecos;
import com.xmedika.br.domain.PessoaEndereco;
import com.xmedika.br.domain.Pessoas;

public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long> {
	
	@Query("SELECT e from PessoaEndereco pe "
			+ "LEFT JOIN pe.enderecos e "
			+ "where pe.pessoas.id = :idPessoa")
	List<Enderecos> findAllEnderecosByPessoaId(@Param("idPessoa") long idPessoa);
	
	void deleteByPessoas(Pessoas pessoa);
	
}

