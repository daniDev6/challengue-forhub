package com.forohub.principal.repository;

import com.forohub.principal.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<Curso,Long> {
}
