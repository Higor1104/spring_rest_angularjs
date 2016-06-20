package com.xmedika.br.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_endereco")
@IdClass(PessoaEnderecoPk.class)
public class PessoaEndereco {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_pessoa")
	private Pessoas pessoas;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	private Enderecos enderecos;

	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public Enderecos getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Enderecos enderecos) {
		this.enderecos = enderecos;
	}
	
}
