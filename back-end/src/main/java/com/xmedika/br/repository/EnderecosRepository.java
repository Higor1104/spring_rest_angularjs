package com.xmedika.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xmedika.br.domain.Enderecos;

@Repository
public interface EnderecosRepository extends JpaRepository<Enderecos, Long> {

}
