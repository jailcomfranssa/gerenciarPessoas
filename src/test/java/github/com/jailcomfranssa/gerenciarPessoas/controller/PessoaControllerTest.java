package github.com.jailcomfranssa.gerenciarPessoas.controller;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
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
public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    void setUp(){

        PageImpl<PessoaDto> pessoas = new PageImpl<>(List.of(Factory.criarPessoaId()));
        BDDMockito.when(pessoaService.listarPessoa(ArgumentMatchers.any()))
                .thenReturn(pessoas);

        BDDMockito.when(pessoaService.consultarPessoa(ArgumentMatchers.anyLong()))
                .thenReturn(Factory.criarPessoaId());

        BDDMockito.when(pessoaService.criarPessoa(ArgumentMatchers.any(PessoaDto.class)))
                .thenReturn(Factory.criarPessoaId());

        BDDMockito.when(pessoaService.editarPessoa(ArgumentMatchers.anyLong(),ArgumentMatchers.any(PessoaDto.class)))
                .thenReturn(Factory.criarPessoaId());
    }

    @Test
    @DisplayName("Listar Todas as Pessoas Com Sucesso")
    void listPessoas(){
        String nomeEsperado = Factory.criarPessoaId().getNome();
        Page<PessoaDto> pessoas =pessoaController.listar(null).getBody();

        Assertions.assertThat(pessoas).isNotNull();
        Assertions.assertThat(pessoas.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(pessoas.toList().get(0).getNome()).isEqualTo(nomeEsperado);

    }

    @Test
    @DisplayName("Buscar Pessoa por Id Com Sucesso")
    void buscarPessoa(){
        Long idEsperado = Factory.criarPessoaId().getId();
        PessoaDto pessoa = pessoaController.consultar(1L).getBody();

        Assertions.assertThat(pessoa).isNotNull();
        Assertions.assertThat(pessoa.getId()).isNotNull().isEqualTo(idEsperado);

    }

    @Test
    @DisplayName("Salva Pessoa Com Sucesso")
    void criarPessoa(){
        PessoaDto pessoa = pessoaController.criar(Factory.criarPessoaId()).getBody();
        Assertions.assertThat(pessoa).isNotNull();
    }

    @Test
    @DisplayName("Atualizar Pessoa Com Sucesso")
    public void editarPessoa(){
        var pessoa = pessoaController.criar(Factory.criarPessoaId()).getBody();

        PessoaDto pessoaEdit = pessoaController.editar(pessoa = Factory.criarPessoaUpdated(),pessoa.getId()).getBody();

        Assertions.assertThat(pessoa.getNome()).isEqualTo("Atualizado");
        Assertions.assertThat(pessoa.getNome()).isNotEqualTo(pessoaEdit.getNome());

    }
}
