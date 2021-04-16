package com.cadastro.springboot.cadastro.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Agendamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // gera o id automaticamente
	private long codigo;
	

	@NotEmpty
	private String nomeMotorista;
	

	@NotEmpty
	private String documentoMotorista;
	

	@NotEmpty
	private String local;
	

	@NotEmpty
	private String data;
	

	@NotEmpty
	private String horario;
	
	@OneToMany(mappedBy = "agendamento")
	private List<Veiculo> veiculo;
	
	

}
