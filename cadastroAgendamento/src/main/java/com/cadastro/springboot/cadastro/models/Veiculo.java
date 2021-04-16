package com.cadastro.springboot.cadastro.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;



@Data
@Entity
public class Veiculo {
	
	@Id
	@NotEmpty
	private long idVeiculo;
	
	@NotEmpty
	private String placa;
	
	@ManyToOne
	private Agendamento agendamento;

	
}
