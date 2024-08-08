package com.forohub.principal.controllers;

import com.forohub.principal.dto.request.DtoCurso;
import com.forohub.principal.dto.response.DtoCursoResponse;
import com.forohub.principal.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("curso/")
public class CursoController {
    @Autowired
    CursoService cursoService;
    @GetMapping("traer")
    public List<DtoCursoResponse> traerCurso(){
        return cursoService.transformarDTO(cursoService.traerCursos());
    }
    @GetMapping("traer/{id}")
    public DtoCursoResponse traerCursoPorId(@PathVariable Long id){
        return cursoService.transformarDTO(cursoService.traerPorCursoPorID(id));
    }
    @PostMapping("crear")
    public String crearCurso (@RequestBody DtoCurso dtoCurso){
        return cursoService.crearCurso(dtoCurso);
    }
    @PutMapping("editar/{id}")
    public DtoCursoResponse editarCursoPorId(@RequestBody DtoCurso cursoDto,@PathVariable Long id){
        return cursoService.actualizarCurso(cursoDto,id);
    }
    @DeleteMapping("eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id){
        return cursoService.eliminarCurso(id);
    }


}

















































