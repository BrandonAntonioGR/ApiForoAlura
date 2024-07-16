package com.aluracursos.ApiForo.repository;

import com.aluracursos.ApiForo.model.Foro;
import com.aluracursos.ApiForo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String username);

}
