package github.com.jailcomfranssa.gerenciarPessoas.service.impl;


import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Endereco;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Pessoa;
import github.com.jailcomfranssa.gerenciarPessoas.repository.EnderecoRepository;
import github.com.jailcomfranssa.gerenciarPessoas.repository.PessoaRepository;
import github.com.jailcomfranssa.gerenciarPessoas.service.EnderecoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoServico {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }


    @Override
    public EnderecoDto criar(EnderecoDto enderecoDto, Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()){
            Endereco endereco = new Endereco();
            endereco.setPessoa(pessoa.get());
            endereco.setCep(enderecoDto.getCep());
            endereco.setCidade(enderecoDto.getCidade());
            endereco.setNumero(enderecoDto.getNumero());
            endereco.setLogradouro(enderecoDto.getLogradouro());
            endereco.setPrincipal(enderecoDto.getPrincipal());
            endereco = enderecoRepository.save(endereco);
            return new EnderecoDto(endereco);
        }else {
            throw new RuntimeException("Pessoa Id: "+ id + "Não encontrada");
        }

    }

    @Override
    public List<EnderecoDto> listar() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(EnderecoDto::new).toList();
    }

    @Override
    public Optional<EnderecoDto> enderecoPrincipal(Long id) {
        Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByPrincipal(id));
        if(endereco.isPresent()){
            return Optional.of(new EnderecoDto(endereco.get()));
        }
        throw new RuntimeException("Pessoa Id: "+ id + "Não encontrada");
    }
}
