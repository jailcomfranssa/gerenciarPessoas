package github.com.jailcomfranssa.gerenciarPessoas.service.impl;

import github.com.jailcomfranssa.gerenciarPessoas.model.dto.PessoaDto;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Pessoa;
import github.com.jailcomfranssa.gerenciarPessoas.repository.PessoaRepository;
import github.com.jailcomfranssa.gerenciarPessoas.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PessoaServiceImpl(PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PessoaDto criarPessoa(PessoaDto pessoaDto) {
        Pessoa pessoa = this.modelMapper.map(pessoaDto, Pessoa.class);
        Pessoa pessoaDados = this.pessoaRepository.save(pessoa);
        return this.modelMapper.map(pessoaDados, PessoaDto.class);
    }

    @Override
    public PessoaDto editarPessoa(Long id, PessoaDto pessoaDto) {
        Pessoa pessoa = this.pessoaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pessoa Id: " + id + " não encontrada"));
        pessoa.setNome(pessoaDto.getNome());
        pessoa.setDataDeNascimento(pessoaDto.getDataDeNascimento());

        Pessoa pessoaDados = this.pessoaRepository.save(pessoa);
        return this.modelMapper.map(pessoaDados, PessoaDto.class);
    }

    @Override
    public PessoaDto consultarPessoa(Long id) {
        Pessoa pessoa = this.pessoaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pessoa Id: " + id + " não encontrada"));

        return this.modelMapper.map(pessoa,PessoaDto.class);
    }

    @Override
    public Page<PessoaDto> listarPessoa(Pageable pageable) {
        List<Pessoa> pessoas = this.pessoaRepository.findAll();
        List<PessoaDto> pessoaDados = pessoas.stream().map((p) -> this.modelMapper.map(p, PessoaDto.class)).toList();
        return new PageImpl<>(pessoaDados);
    }
}
