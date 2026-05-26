package com.frigocezar.logistica.repository;

import com.frigocezar.logistica.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,UUID>{

   UserDetails findByLogin(String Login);

}
