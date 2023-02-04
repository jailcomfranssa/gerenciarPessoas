package github.com.jailcomfranssa.gerenciarPessoas.controller;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;
import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
import github.com.jailcomfranssa.gerenciarPessoas.service.EnderecoServico;
import github.com.jailcomfranssa.gerenciarPessoas.service.PessoaService;
import github.com.jailcomfranssa.gerenciarPessoas.tests.Factory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class EnderecoControllerTest {

    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecoServico enderecoServico;

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    void setUp(){
        PageImpl<EnderecoDto> enderecoDtos = new PageImpl<>(List.of(Factory.criarEnderecoDtoId()));
        BDDMockito.when(enderecoServico.listarEndereco(ArgumentMatchers.any()))
                .thenReturn(enderecoDtos);

        BDDMockito.when(enderecoServico.criarEndereco(ArgumentMatchers.any(EnderecoDto.class),ArgumentMatchers.anyLong()))
                .thenReturn(Factory.criarEnderecoDtoId());

        BDDMockito.when(enderecoServico.enderecoPrincipal(ArgumentMatchers.anyLong()))
                .thenReturn(Factory.criarEnderecoDtoId());

        BDDMockito.when(pessoaService.criarPessoa(ArgumentMatchers.any(PessoaDto.class)))
                .thenReturn(Factory.criarPessoaId());

    }


    @Test
    @DisplayName("Salva Endereço Com Sucesso")
    void criar() {
        var pessoa = pessoaController.criar(Factory.criarPessoaId()).getBody();
        EnderecoDto enderecoDto = enderecoController.criar(Factory.criarEnderecoDtoId(),pessoa.getId()).getBody();
        Assertions.assertThat(enderecoDto).isNotNull();

    }

    @Test
    @DisplayName("Listar Todos Endereços Com Sucesso")
    void listar() {
        var cidadeEsperado = Factory.criarEnderecoDtoId().getCidade();
        Page<EnderecoDto> enderecos = enderecoController.listar(null).getBody();

        Assertions.assertThat(enderecos).isNotNull();
        Assertions.assertThat(enderecos.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(enderecos.toList().get(0).getCidade()).isEqualTo(cidadeEsperado);

    }

    @Test
    @DisplayName("Busca Pelo Endereço Principal De Uma Pessoa Com Sucesso")
    void enderecoPrincipal() {
        var pessoa = pessoaController.criar(Factory.criarPessoaId()).getBody();
        EnderecoDto enderecoDto = enderecoController.enderecoPrincipal(pessoa.getId()).getBody();
        Assertions.assertThat(pessoa).isNotNull();
        Assertions.assertThat(enderecoDto).isNotNull();
        Assertions.assertThat(enderecoDto.getPrincipal()).isEqualTo(true);


    }
}