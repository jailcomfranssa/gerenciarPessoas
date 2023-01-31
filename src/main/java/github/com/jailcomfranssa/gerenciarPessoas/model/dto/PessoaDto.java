package github.com.jailcomfranssa.gerenciarPessoas.model.dto;

import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PessoaDto {

    private Long id;

    private String nome;

    private Date dataDeNascimento;

    public PessoaDto(Long id, String nome, Date dataDeNascimento){
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;

    }

    public PessoaDto(Pessoa entity){
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.dataDeNascimento = entity.getDataDeNascimento();

    }
}
