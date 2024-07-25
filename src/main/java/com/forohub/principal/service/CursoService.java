package com.forohub.principal.service;

import com.forohub.principal.dto.DtoCurso;
import com.forohub.principal.models.Curso;
import com.forohub.principal.repository.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoService {
    @Autowired
    ICursoRepository cursoRepository;

    public CursoService() {
    }

    public String crearCurso(Curso curso) {
        try {
            this.cursoRepository.save(curso);
            return "Se creo correctamente";
        } catch (Exception var3) {
            throw new RuntimeException();
        }
    }

    public List<DtoCurso> traerCursos() {
        List<Curso> listaCursos = cursoRepository.findAll();
        List<DtoCurso> cursos = listaCursos.stream().map(Curso::toDto).collect(Collectors.toList());
        return cursos;
    }

    public Curso traerPorCursoPorID(Long id) {
        Optional<Curso> curso;
        try{
            curso = cursoRepository.findById(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
        return curso.isPresent()?curso.get():new Curso();
    }
}
