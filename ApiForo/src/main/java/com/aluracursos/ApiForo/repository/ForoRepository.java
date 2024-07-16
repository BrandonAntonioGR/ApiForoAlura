package com.aluracursos.ApiForo.repository;

import com.aluracursos.ApiForo.model.Foro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForoRepository  extends JpaRepository<Foro, Long> {
}
