package github.com.jailcomfranssa.gerenciarPessoas.tests;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;
import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Endereco;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Pessoa;

import java.util.Date;

public class Factory {


    public static Pessoa criarPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Jailson");
        pessoa.setDataDeNascimento(new Date(1985, 1, 1));
        return pessoa;
    }

    public static PessoaDto criarPessoaDto(){
        PessoaDto pessoa = new PessoaDto();
        pessoa.setNome("Jailson");
        pessoa.setDataDeNascimento(new Date(1985, 1, 1));
        return pessoa;
    }

    public static PessoaDto criarPessoaId(){
        PessoaDto pessoa = new PessoaDto();
        pessoa.setId(1L);
        pessoa.setNome("Jailson");
        pessoa.setDataDeNascimento(new Date(1985, 1, 1));
        return pessoa;
    }

    public static PessoaDto criarPessoaUpdated(){
        PessoaDto pessoa = new PessoaDto();
        pessoa.setId(1L);
        pessoa.setNome("Atualizado");
        pessoa.setDataDeNascimento(new Date(1985, 1, 1));
        return pessoa;
    }

    public static Endereco criarEndereco(){
        Endereco endereco = new Endereco();
        endereco.setPessoa(criarPessoa());
        endereco.setLogradouro("Rua 01");
        endereco.setCep("000211");
        endereco.setCidade("João Pessoa");
        endereco.setNumero("20B");
        endereco.setPrincipal(false);

        return endereco;
    }

    public static EnderecoDto criarEnderecoDto(){
        EnderecoDto endereco = new EnderecoDto();
        endereco.setPessoa(criarPessoaDto());
        endereco.setId(1L);
        endereco.setLogradouro("Rua 01");
        endereco.setCep("000211");
        endereco.setCidade("João Pessoa");
        endereco.setNumero("20B");
        endereco.setPrincipal(true);

        return endereco;
    }
}
