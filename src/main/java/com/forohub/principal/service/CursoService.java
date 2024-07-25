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
    public String crearCurso(DtoCurso cursoDto) {
        Curso curso = new Curso(cursoDto);
        return crearCurso(curso);
    }

    public List<Curso> traerCursos() {
        return cursoRepository.findAll();
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
    public DtoCurso transformarDTO(Curso curso){
        return curso.toDto();
    }
    public List<DtoCurso> transformarDTO(List<Curso> curso){
        return curso.stream().map(Curso::toDto).collect(Collectors.toList());
    }


    public DtoCurso actualizarCurso(DtoCurso cursoDto,Long id) {
        Curso curso = traerPorCursoPorID(id);
        if(curso==null){
            throw new RuntimeException();
        }else{
            return transformarDTO(new Curso());
        }
    }
}
