package com.xmedika.br.infrastructure.DTO;

import java.util.List;

import com.xmedika.br.domain.Enderecos;
import com.xmedika.br.domain.Pessoas;

public class PessoasEnderecosDTO {
	
	private Pessoas pessoa;
	
	List<Enderecos> listEnderecos;

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	public List<Enderecos> getListEnderecos() {
		return listEnderecos;
	}

	public void setListEnderecos(List<Enderecos> listEnderecos) {
		this.listEnderecos = listEnderecos;
	}
	

}
