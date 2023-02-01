package github.com.jailcomfranssa.gerenciarPessoas.controller;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
import github.com.jailcomfranssa.gerenciarPessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(pessoaService.criarPessoa(pessoaDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> consultar(@PathVariable Long id){
        return new ResponseEntity<>(pessoaService.consultarPessoa(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PessoaDto>> listar(){
        return new ResponseEntity<>(pessoaService.listarPessoa(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> editar(@RequestBody PessoaDto pessoaDto, @PathVariable Long id){
        return new ResponseEntity<>(pessoaService.editarPessoa(id,pessoaDto),HttpStatus.OK);
    }


}
