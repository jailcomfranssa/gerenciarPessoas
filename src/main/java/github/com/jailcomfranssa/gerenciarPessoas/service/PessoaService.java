package github.com.jailcomfranssa.gerenciarPessoas.service;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    PessoaDto criarPessoa(PessoaDto pessoaDto);
    PessoaDto editarPessoa(Long id, PessoaDto pessoaDto);
    PessoaDto consultarPessoa(Long id);
    Page<PessoaDto> listarPessoa(Pageable pageable);







}
