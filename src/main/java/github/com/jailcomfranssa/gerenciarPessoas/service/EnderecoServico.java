package github.com.jailcomfranssa.gerenciarPessoas.service;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;

import java.util.List;
import java.util.Optional;

public interface EnderecoServico {

    EnderecoDto criarEndereco(EnderecoDto enderecoDto, Long id);
    List<EnderecoDto> listarEndereco();
    EnderecoDto enderecoPrincipal(Long id);

}
