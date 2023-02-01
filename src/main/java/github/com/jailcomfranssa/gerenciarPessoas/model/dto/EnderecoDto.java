package github.com.jailcomfranssa.gerenciarPessoas.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {

    private Long id;

    private String logradouro;

    private String cep;

    private String numero;

    private String cidade;
    private Boolean principal;

    private PessoaDto pessoa;

}
