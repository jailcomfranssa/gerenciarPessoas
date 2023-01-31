package github.com.jailcomfranssa.gerenciarPessoas.controller;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Endereco;
import github.com.jailcomfranssa.gerenciarPessoas.service.EnderecoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
        enderecoDto = enderecoServico.criar(enderecoDto,id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(enderecoDto.getId()).toUri();
        return ResponseEntity.created(uri).body(enderecoDto);
    }

    @GetMapping()
    public ResponseEntity<List<EnderecoDto>> listar(){
        return new ResponseEntity<>(enderecoServico.listar(), HttpStatus.OK);
    }

    @GetMapping("/principal/pessoa/{id}")
    public ResponseEntity<Optional<EnderecoDto>> enderecoPrincipal(@PathVariable Long id){
        return new ResponseEntity<>(enderecoServico.enderecoPrincipal(id),HttpStatus.OK);
    }


}
