package github.com.jailcomfranssa.gerenciarPessoas.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PessoaDto {

    private Long id;

    private String nome;

    private Date dataDeNascimento;

}
