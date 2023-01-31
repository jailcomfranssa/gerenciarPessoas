package github.com.jailcomfranssa.gerenciarPessoas.service.impl;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Pessoa;
import github.com.jailcomfranssa.gerenciarPessoas.repository.PessoaRepository;
import github.com.jailcomfranssa.gerenciarPessoas.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    @Override
    @Transactional
    public PessoaDto criar(PessoaDto pessoaDto) {
        Pessoa entity = new Pessoa();
        entity.setNome(pessoaDto.getNome());
        entity.setDataDeNascimento(pessoaDto.getDataDeNascimento());
        entity = pessoaRepository.save(entity);
        return new PessoaDto(entity);
    }

    @Override
    @Transactional
    public PessoaDto editar(Long id, PessoaDto pessoaDto) {
        try{
            Pessoa entity = pessoaRepository.getReferenceById(id);
            entity.setNome(pessoaDto.getNome());
            entity.setDataDeNascimento(pessoaDto.getDataDeNascimento());
            entity = pessoaRepository.save(entity);
            return new PessoaDto(entity);
        }catch (EntityNotFoundException e){
            throw new RuntimeException("Id: "+id+ " não encontrado");
        }
    }

    @Override
    public Optional<PessoaDto> consultar(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        Pessoa entity = pessoa.orElseThrow(() -> new RuntimeException("Pessoa com Id: "+ id+" não encontrado"));
        return Optional.of(new PessoaDto(entity));
    }

    @Override
    public List<PessoaDto> listar() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        List<PessoaDto> pessoaDtos = pessoas.stream().map(PessoaDto::new).toList();
        return pessoaDtos;
    }
}
