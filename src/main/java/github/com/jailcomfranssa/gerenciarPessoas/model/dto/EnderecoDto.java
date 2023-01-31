package github.com.jailcomfranssa.gerenciarPessoas.model.dto;

import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Endereco;
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
    Boolean principal;

    public EnderecoDto(Long id, String logradouro, String cep, String numero, String cidade, Boolean principal){
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.principal = principal;
    }
    public EnderecoDto(Endereco entity){
        this.id = entity.getId();
        this.logradouro = entity.getLogradouro();
        this.cep = entity.getCep();
        this.numero = entity.getNumero();
        this.cidade = entity.getCidade();
        this.principal = entity.getPrincipal();

    }
}
