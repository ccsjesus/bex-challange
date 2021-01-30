package br.com.bexchallange;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import br.com.bexchallange.service.BexRoutesServices;

@SpringBootTest
class BexChallangeApplicationTests {

	@Autowired
	private BexRoutesServices bexRoutesServices;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Test
	public void verificarCusto() throws IOException {
		//cenario
		Resource resource = resourceLoader.getResource("classpath:input-routes.csv");
		bexRoutesServices.loadFileRoutes();
		
		//acao
		
		//verificacao
	}
	
	@Test
	public void adicionarVertice() throws IOException {
		
		//cenario
			bexRoutesServices.addVertice("GRU");
			bexRoutesServices.addVertice("BRC");
			bexRoutesServices.addVertice("SCL");
			bexRoutesServices.addVertice("ORL");
			bexRoutesServices.addVertice("CDG");
		//acao
		
		//verificacao
	}
}