package github.com.jailcomfranssa.gerenciarPessoas.tests;

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
}
