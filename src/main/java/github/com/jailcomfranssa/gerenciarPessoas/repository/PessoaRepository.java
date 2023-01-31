package github.com.jailcomfranssa.gerenciarPessoas.repository;

import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
