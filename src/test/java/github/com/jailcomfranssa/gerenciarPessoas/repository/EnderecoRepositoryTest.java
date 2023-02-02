package github.com.jailcomfranssa.gerenciarPessoas.repository;

import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Endereco;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Pessoa;
import github.com.jailcomfranssa.gerenciarPessoas.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;
    private PessoaRepository pessoaRepository;

    List<Endereco> endereco;

    @BeforeEach
    void setUp() throws Exception{
        endereco = enderecoRepository.findAll();

    }

    @Test
    public void criarEndereco(){
        int total = endereco.size();
        Endereco endereco = Factory.criarEndereco();
        endereco = enderecoRepository.save(endereco);

        Assertions.assertNotNull(endereco.getId());
        Assertions.assertEquals(total+1, endereco.getId());
    }

    @Test
    public void listarEndereco(){
        List<Endereco> enderecos = enderecoRepository.findAll();
        Assertions.assertNotNull(enderecos);

    }

    @Test
    public void enderecoPrincipal(){
        Endereco enderecoPessoa = enderecoRepository.findByPrincipal(1L);
        Assertions.assertEquals(enderecoPessoa.getPrincipal(),true);


    }
}
