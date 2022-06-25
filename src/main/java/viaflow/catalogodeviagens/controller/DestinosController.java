package viaflow.catalogodeviagens.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import viaflow.catalogodeviagens.entities.DestinosEntity;
import viaflow.catalogodeviagens.repositories.DestinosRepository;
import viaflow.catalogodeviagens.useCases.DestinosImagensUsecase;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/destinos")
public class DestinosController {

    private final DestinosRepository destinosRepository;
    private final DestinosImagensUsecase destinosImagensUsecase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastrar")
    public ResponseEntity<DestinosEntity> save(@RequestBody DestinosEntity destinos) {

        return ResponseEntity.ok(destinosRepository.save(destinos));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DestinosEntity>> listarDestinos(){
        return ResponseEntity.ok(destinosRepository.findAll());
    }

    @GetMapping("/listar/{id}")
    public Optional<DestinosEntity> listaDestinosId(@PathVariable(value="id") Integer id){
        return destinosRepository.findById(id);
    }

    @RequestMapping(value="/foto", method=RequestMethod.POST)
    public ResponseEntity<Void> uploadPicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = destinosImagensUsecase.uploadPicture(file);
        return ResponseEntity.created(uri).build();
    }
}
