package github.com.jailcomfranssa.gerenciarPessoas.repository;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
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
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    private Long idValido;
    private List<Pessoa> pessoas;


    @BeforeEach
    void setUp() throws Exception{
        idValido = 1L;
        pessoas = pessoaRepository.findAll();

    }

    @Test
    public void criarPessoa(){
        int qtd = pessoas.size();
        Pessoa pessoa = Factory.criarPessoa();
        pessoa = pessoaRepository.save(pessoa);

        Assertions.assertNotNull(pessoa.getId());
        Assertions.assertEquals(qtd+1,pessoa.getId());
        Assertions.assertEquals(pessoa.getNome(), "Jailson");
    }

    @Test
    public void editarPessoa(){
        Pessoa pessoa = Factory.criarPessoa();
        pessoa = pessoaRepository.save(pessoa);

        pessoa.setNome("Atualizado");

        Pessoa pessoaUpdate = pessoaRepository.save(pessoa);

        Assertions.assertNotNull(pessoaUpdate.getId());
        Assertions.assertEquals("Atualizado",pessoaUpdate.getNome());

    }

    @Test
    public void consultarPessoa(){
        Optional<Pessoa> result = pessoaRepository.findById(idValido);
        Assertions.assertEquals(result.get().getNome(),"Jose");
    }

    @Test
    public void listarPessoa(){
        List<Pessoa> pessoas = pessoaRepository.findAll();
        Assertions.assertNotNull(pessoas);
    }
}
