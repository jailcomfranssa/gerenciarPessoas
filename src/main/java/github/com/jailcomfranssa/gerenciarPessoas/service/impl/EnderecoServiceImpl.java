package github.com.jailcomfranssa.gerenciarPessoas.service.impl;


import github.com.jailcomfranssa.gerenciarPessoas.model.dto.EnderecoDto;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Endereco;
import github.com.jailcomfranssa.gerenciarPessoas.model.entities.Pessoa;
import github.com.jailcomfranssa.gerenciarPessoas.repository.EnderecoRepository;
import github.com.jailcomfranssa.gerenciarPessoas.repository.PessoaRepository;
import github.com.jailcomfranssa.gerenciarPessoas.service.EnderecoServico;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoServico {

    private final EnderecoRepository enderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public EnderecoDto criarEndereco(EnderecoDto enderecoDto, Long id) {

       Pessoa pessoa = this.pessoaRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Pessoa Id: " + id +" não encontrada"));

       Endereco endereco = this.modelMapper.map(enderecoDto, Endereco.class);
       endereco.setCep(enderecoDto.getCep());
       endereco.setCidade(enderecoDto.getCidade());
       endereco.setNumero(enderecoDto.getNumero());
       endereco.setLogradouro(enderecoDto.getLogradouro());
       endereco.setPrincipal(enderecoDto.getPrincipal());
       endereco.setPessoa(pessoa);

       Endereco addEndereco = this.enderecoRepository.save(endereco);
       return this.modelMapper.map(addEndereco, EnderecoDto.class);

    }

    @Override
    public List<EnderecoDto> listarEndereco() {
        List<Endereco> enderecos = this.enderecoRepository.findAll();
        List<EnderecoDto> enderecoDtos = enderecos.stream().map((e)->
                this.modelMapper.map(e, EnderecoDto.class)).toList();

        return enderecoDtos;
    }

    @Override
    public EnderecoDto enderecoPrincipal(Long pessoa_id) {
        Endereco endereco = this.enderecoRepository.findById(pessoa_id)
                .orElseThrow(()-> new RuntimeException("Pessoa Id: " + pessoa_id + " não encontrada"));
        Endereco _endereco = this.enderecoRepository.findByPrincipal(endereco.getId());

        return this.modelMapper.map(_endereco, EnderecoDto.class);
    }
}
