package com.cadastro.springboot.cadastro.repository;

import org.springframework.data.repository.CrudRepository;

import com.cadastro.springboot.cadastro.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{

	Usuario findByLogin(String login);
}
