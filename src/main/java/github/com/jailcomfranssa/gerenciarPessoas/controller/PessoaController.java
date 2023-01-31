package github.com.jailcomfranssa.gerenciarPessoas.controller;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
import github.com.jailcomfranssa.gerenciarPessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping()
    public ResponseEntity<PessoaDto> criar(@RequestBody PessoaDto pessoaDto){
        pessoaDto = pessoaService.criar(pessoaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(pessoaDto.getId()).toUri();
        return ResponseEntity.created(uri).body(pessoaDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PessoaDto>> consultar(@PathVariable Long id){
        return new ResponseEntity<>(pessoaService.consultar(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PessoaDto>> listar(){
        return new ResponseEntity<>(pessoaService.listar(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> editar(@RequestBody PessoaDto pessoaDto, @PathVariable Long id){
        return new ResponseEntity<>(pessoaService.editar(id,pessoaDto),HttpStatus.OK);
    }


}
