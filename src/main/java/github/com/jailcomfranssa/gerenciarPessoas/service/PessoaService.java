package github.com.jailcomfranssa.gerenciarPessoas.service;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    PessoaDto criarPessoa(PessoaDto pessoaDto);
    PessoaDto editarPessoa(Long id, PessoaDto pessoaDto);
    PessoaDto consultarPessoa(Long id);
    List<PessoaDto> listarPessoa();







}
