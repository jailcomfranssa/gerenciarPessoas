package github.com.jailcomfranssa.gerenciarPessoas.service;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;

import java.util.List;
import java.util.Optional;

public interface PessoaService {

    PessoaDto criar(PessoaDto pessoaDto);
    PessoaDto editar(Long id, PessoaDto pessoaDto);
    Optional<PessoaDto> consultar(Long id);
    List<PessoaDto> listar();







}
