/*package com.xmedika.br.infrastructure.actuator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.xmedika.br.infrastructure.DTO.PessoasEnderecosDTO;
import com.xmedika.br.service.PessoasService;

@Component
public class PessoasEnderecosHealthIndicator implements HealthIndicator {

    @Autowired
    private PessoasService service;

    @Override
    public Health health() {

        List<PessoasEnderecosDTO> essoasEnderecosDTO = service.findAllPessoasEnderecos();

        if (essoasEnderecosDTO == null || essoasEnderecosDTO.size() == 0) {
            return Health.down().withDetail("count Pessoas", 0).build();
        }

        return Health.up().withDetail("count Pessoas Enderecos", essoasEnderecosDTO.size()).build();
    }
	
}
*/