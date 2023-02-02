package github.com.jailcomfranssa.gerenciarPessoas.service;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EnderecoServico {

    EnderecoDto criarEndereco(EnderecoDto enderecoDto, Long id);
    Page<EnderecoDto> listarEndereco(Pageable pageable);
    EnderecoDto enderecoPrincipal(Long id);

}
