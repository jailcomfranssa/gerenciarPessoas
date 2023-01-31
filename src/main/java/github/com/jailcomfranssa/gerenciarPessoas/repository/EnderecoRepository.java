package github.com.jailcomfranssa.gerenciarPessoas.repository;

import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("from Endereco where principal = true and pessoa.id= :pessoaId")
    Endereco findByPrincipal(Long pessoaId);
}
