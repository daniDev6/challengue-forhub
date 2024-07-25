package com.forohub.principal.controllers;

import com.forohub.principal.dto.DtoCurso;
import com.forohub.principal.models.Curso;
import com.forohub.principal.models.Usuario;
import com.forohub.principal.repository.ICursoRepository;
import com.forohub.principal.repository.IUsuarioRepository;
import com.forohub.principal.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("curso/")
public class CursoController {
    @Autowired
    CursoService cursoService;
    @GetMapping("traer")
    public List<DtoCurso> traerCurso(){
        return cursoService.transformarDTO(cursoService.traerCursos());
    }
    @GetMapping("traer/{id}")
    public DtoCurso traerCursoPorId(@PathVariable Long id){
        return cursoService.transformarDTO(cursoService.traerPorCursoPorID(id));
    }
    @PostMapping("crear")
    public String crearCurso (@RequestBody DtoCurso dtoCurso){
        return cursoService.crearCurso(dtoCurso);
    }
    @PutMapping("editar/{id}")
    public DtoCurso editarCursoPorId(@RequestBody DtoCurso cursoDto,@PathVariable Long id){
        return cursoService.actualizarCurso(cursoDto,id);
    }



}

















































