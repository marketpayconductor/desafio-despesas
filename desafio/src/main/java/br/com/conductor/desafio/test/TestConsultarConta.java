package br.com.conductor.desafio.test;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.conductor.desafio.util.RespostaDesafio;
import junit.framework.TestCase;

@RunWith(JUnit4.class)
public class TestConsultarConta extends TestCase {
	
	private static final String REST_SERVICE_URI = "http://localhost:8080/";
	
	@Test
	public void consultarConta(){
		
		Random gerador = new Random();
		String servicoConsultarConta = "conta/";
		String url = REST_SERVICE_URI + servicoConsultarConta + gerador.nextInt(30);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<RespostaDesafio> response = 
				restTemplate.getForEntity(url, RespostaDesafio.class);
		
		RespostaDesafio respostaDesafio = response.getBody();
		
		assertEquals(true, respostaValida(respostaDesafio));
		
	}

	private boolean respostaValida(RespostaDesafio respostaDesafio) {
		return respostaDesafio != null &&
				(respostaDesafio.getMensagem().equalsIgnoreCase("OK")
						|| respostaDesafio.getMensagem().equalsIgnoreCase("Conta Inexistente"));
	}

}
