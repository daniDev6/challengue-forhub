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
    private final ICursoRepository cursoRepository;
    private final ServicioGenerales servicioGenerales;


    @Autowired
    public CursoService(ICursoRepository cursoRepository, ServicioGenerales servicioGenerales) {
        this.cursoRepository=cursoRepository;
        this.servicioGenerales=servicioGenerales;
    }

    public String crearCurso(Curso curso) {
        try {
            cursoRepository.save(curso);
            return "Se creo correctamente";
        } catch (Exception ex) {
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
        if(curso==null){throw new RuntimeException("no existe tal Curso con el id" + id);}
             //Evaluamos si vienen cargados con datos y si son nuevos o no
        boolean nombreValidar = servicioGenerales.controlarEstado(cursoDto.nombre(), curso.getNombre());
        boolean categoriaValidar = servicioGenerales.controlarEstado(cursoDto.categoria(),curso.getCategoria());
        if(nombreValidar){
            curso.setNombre(cursoDto.nombre());
        }
        if(categoriaValidar){
            curso.setCategoria(cursoDto.categoria());
        }
        if(nombreValidar|categoriaValidar){cursoRepository.save(curso);}
        return transformarDTO(curso);
    }




}
