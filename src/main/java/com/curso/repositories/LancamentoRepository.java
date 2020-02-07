package com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.entities.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
