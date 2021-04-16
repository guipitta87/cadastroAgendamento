package com.cadastro.springboot.cadastro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cadastro.springboot.cadastro.models.Agendamento;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, String>{
	Agendamento findByCodigo(long codigo);
}
