package com.cadastro.springboot.cadastro.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cadastro.springboot.cadastro.models.Agendamento;
import com.cadastro.springboot.cadastro.models.Veiculo;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, String>{
	Iterable<Veiculo> findByAgendamento(Agendamento agendamento);
	Veiculo findByVeiculo(long idVeiculo);
}
