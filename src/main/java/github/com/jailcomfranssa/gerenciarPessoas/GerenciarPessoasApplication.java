package github.com.jailcomfranssa.gerenciarPessoas;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GerenciarPessoasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciarPessoasApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
