package github.com.jailcomfranssa.gerenciarPessoas.service;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;

import java.util.List;
import java.util.Optional;

public interface EnderecoServico {

    EnderecoDto criar(EnderecoDto enderecoDto, Long id);
    List<EnderecoDto> listar();
    Optional<EnderecoDto> enderecoPrincipal(Long id);

}
