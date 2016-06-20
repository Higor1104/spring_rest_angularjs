package com.xmedika.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.xmedika.br.domain.Pessoas;

@Repository
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {

}
