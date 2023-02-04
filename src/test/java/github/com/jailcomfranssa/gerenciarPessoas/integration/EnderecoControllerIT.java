package github.com.jailcomfranssa.gerenciarPessoas.integration;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class EnderecoControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Salva Endereço Com Sucesso")
    void criar() {
        var enderecoDados = Factory.criarEnderecoDto();

        ResponseEntity<EnderecoDto> endereco = testRestTemplate.postForEntity("/api/enderecos/pessoa/{id}", enderecoDados, EnderecoDto.class,1);
        Assertions.assertThat(endereco).isNotNull();
        Assertions.assertThat(endereco.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(endereco.getBody()).isNotNull();
    }

    @Test
    @DisplayName("Listar Todos Endereços Com Sucesso")
    void listar() {
        PageableResponse<EnderecoDto> endereco = testRestTemplate.exchange("/api/enderecos", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<EnderecoDto>>() {
                }).getBody();
        Assertions.assertThat(endereco).isNotNull();
        Assertions.assertThat(endereco.toList())
                .isNotEmpty()
                .hasSize(endereco.getSize());
    }

    @Test
    @DisplayName("Busca Pelo Endereço Principal De Uma Pessoa Com Sucesso")
    void enderecoPrincipal() {
        EnderecoDto enderecoDto = testRestTemplate.getForObject("/api/enderecos/principal/pessoa/{id}", EnderecoDto.class,1);
        Assertions.assertThat(enderecoDto).isNotNull();
        Assertions.assertThat(enderecoDto.getPrincipal()).isEqualTo(true);
    }
}
