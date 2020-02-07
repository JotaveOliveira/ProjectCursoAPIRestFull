package com.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
