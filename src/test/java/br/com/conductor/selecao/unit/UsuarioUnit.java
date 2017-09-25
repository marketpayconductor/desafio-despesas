package br.com.conductor.selecao.unit;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.text.RandomStringGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import br.com.conductor.selecao.model.Usuario;

@SuppressWarnings("unchecked")
public class UsuarioUnit {
	private static final String REST_SERVICE_URI = "http://localhost:8080/app/api";

	@Test
	public void listAllUsers() {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> usuariosMap = restTemplate.getForObject(REST_SERVICE_URI + "/usuario/",
				List.class);

		Assert.assertNotNull(usuariosMap);
	}

	@Test
	public void criarUsuario() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		String randomLetters = generator.generate(20);

		RestTemplate restTemplate = new RestTemplate();
		Usuario usuario = new Usuario(null, randomLetters, "123123");
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/usuario/", usuario, Usuario.class);
		System.out.println("Location : " + uri.toASCIIString());

		Usuario usuarioPesquisa = restTemplate.getForObject(uri.toASCIIString(), Usuario.class);
		usuario.setId(usuarioPesquisa.getId());
		
		Assert.assertEquals(usuario, usuarioPesquisa);
	}

	@Test
	public void alterarUsuario() {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		String randomLetters = generator.generate(20);

		RestTemplate restTemplate = new RestTemplate();
		Usuario usuario = new Usuario(null, randomLetters, "123123");
		URI uri = restTemplate.postForLocation(REST_SERVICE_URI + "/usuario/", usuario, Usuario.class);
		System.out.println("Location : " + uri.toASCIIString());
		
		randomLetters = generator.generate(20);
		usuario.setLogin(randomLetters);
		restTemplate.put(uri.toASCIIString(), usuario);

		Usuario usuarioPesquisa = restTemplate.getForObject(uri.toASCIIString(), Usuario.class);
		usuario.setId(usuarioPesquisa.getId());
		
		Assert.assertEquals(usuario, usuarioPesquisa);
	}

}
