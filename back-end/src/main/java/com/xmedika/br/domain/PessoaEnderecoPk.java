package com.xmedika.br.domain;

import java.io.Serializable;

public class PessoaEnderecoPk implements Serializable {

	private static final long serialVersionUID = 1L;

	private long pessoas;

	private long enderecos;

	public long getPessoas() {
		return pessoas;
	}

	public void setPessoas(long pessoas) {
		this.pessoas = pessoas;
	}

	public long getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(long enderecos) {
		this.enderecos = enderecos;
	}
	
	
}
