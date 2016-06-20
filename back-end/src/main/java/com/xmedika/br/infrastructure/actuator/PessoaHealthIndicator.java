package com.xmedika.br.infrastructure.actuator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.xmedika.br.domain.Pessoas;
import com.xmedika.br.service.PessoasService;

@Component
public class PessoaHealthIndicator implements HealthIndicator {

    @Autowired
    private PessoasService service;

    @Override
    public Health health() {

        List<Pessoas> pessoas = service.findAll();

        if (pessoas == null || pessoas.size() == 0) {
            return Health.down().withDetail("count Pessoas", 0).build();
        }

        return Health.up().withDetail("count Pessoas", pessoas.size()).build();
    }

}