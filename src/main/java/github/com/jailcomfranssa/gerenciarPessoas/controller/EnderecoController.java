package github.com.jailcomfranssa.gerenciarPessoas.controller;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;
import github.com.jailcomfranssa.gerenciarPessoas.service.EnderecoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoServico enderecoServico;

    @Autowired
    public EnderecoController(EnderecoServico enderecoServico) {
        this.enderecoServico = enderecoServico;
    }

    @PostMapping("pessoa/{id}")
    public ResponseEntity<EnderecoDto> criar(@RequestBody EnderecoDto enderecoDto, @PathVariable Long id){
        return new ResponseEntity<>(enderecoServico.criarEndereco(enderecoDto,id),HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Page<EnderecoDto>> listar(Pageable pageable){
        return new ResponseEntity<>(enderecoServico.listarEndereco(pageable), HttpStatus.OK);
    }

    @GetMapping("/principal/pessoa/{id}")
    public ResponseEntity<EnderecoDto> enderecoPrincipal(@PathVariable Long id){
        return new ResponseEntity<>(enderecoServico.enderecoPrincipal(id),HttpStatus.OK);
    }


}
