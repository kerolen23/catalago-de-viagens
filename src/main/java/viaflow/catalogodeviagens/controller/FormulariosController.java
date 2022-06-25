package viaflow.catalogodeviagens.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import viaflow.catalogodeviagens.entities.DestinosEntity;
import viaflow.catalogodeviagens.entities.FormularioEntity;
import viaflow.catalogodeviagens.repositories.DestinosRepository;
import viaflow.catalogodeviagens.repositories.FormularioRepository;
import viaflow.catalogodeviagens.useCases.DestinosImagensUsecase;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/formularios")
public class FormulariosController {


    private final FormularioRepository formularioRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public ResponseEntity<FormularioEntity> save(@RequestBody FormularioEntity formulario) {

        return ResponseEntity.ok(formularioRepository.save(formulario));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<FormularioEntity>> listarformulario(){
        return ResponseEntity.ok(formularioRepository.findAll());
    }

    @GetMapping("/listar/{id}")
    public Optional<FormularioEntity> listaFormularioId(@PathVariable(value="id") Integer id){
        return formularioRepository.findById(id);
    }

}
