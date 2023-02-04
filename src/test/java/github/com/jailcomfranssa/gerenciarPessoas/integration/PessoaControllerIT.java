package github.com.jailcomfranssa.gerenciarPessoas.integration;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;

import github.com.jailcomfranssa.gerenciarPessoas.settings.wrapper.PageableResponse;
import github.com.jailcomfranssa.gerenciarPessoas.tests.Factory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class PessoaControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    @DisplayName("Listar Pessoas")
    void listarPessoa(){

        PageableResponse<PessoaDto> pessoas = testRestTemplate.exchange("/api/pessoas", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<PessoaDto>> () {
                }).getBody();
        Assertions.assertThat(pessoas).isNotNull();
        Assertions.assertThat(pessoas.toList())
                .isNotEmpty()
                .hasSize(pessoas.getSize());
    }

    @Test
    @DisplayName("Buscar Pessoa por Id Com Sucesso")
    void buscarPessoa(){
        PessoaDto pessoa = testRestTemplate.getForObject("/api/pessoas/{id}",PessoaDto.class,1);

        Assertions.assertThat(pessoa).isNotNull();
        Assertions.assertThat(pessoa.getId()).isNotNull();
        Assertions.assertThat(pessoa.getNome()).isEqualTo("Jose");
    }

    @Test
    @DisplayName("Salva Pessoa Com Sucesso")
    void criarPessoa(){
        var pessoaDados = Factory.criarPessoaDto();
        ResponseEntity<PessoaDto> pessoa = testRestTemplate.postForEntity("/api/pessoas",pessoaDados,PessoaDto.class);

        Assertions.assertThat(pessoa).isNotNull();
        Assertions.assertThat(pessoa.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(pessoa.getBody()).isNotNull();
    }

    @Test
    @DisplayName("Atualizar Pessoa Com Sucesso")
    public void editarPessoa(){

        var pessoaDados = Factory.criarPessoaUpdated();
        ResponseEntity<PessoaDto> pessoa = testRestTemplate.exchange("/api/pessoas/{id}",HttpMethod.PUT,
                new HttpEntity<>(pessoaDados),PessoaDto.class,1);

        Assertions.assertThat(Objects.requireNonNull(pessoa.getBody()).getNome()).isEqualTo("Atualizado");
        Assertions.assertThat(pessoa.getStatusCode()).isEqualTo(HttpStatus.OK);


    }

}
