package br.com.conductor.selecao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import br.com.conductor.selecao.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"br.com.conductor.selecao"})
public class SpringBootRunApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRunApp.class, args);
	}
	
}
