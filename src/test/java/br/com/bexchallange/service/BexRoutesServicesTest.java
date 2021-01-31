package br.com.bexchallange.service;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BexRoutesServicesTest {

	@Test
	public void verificarQuantidadeVertice() throws IOException {

		// cenario
		BexRoutesServices bexRoutesServices = new BexRoutesServices();

		// acao
		bexRoutesServices.addVertice("GRU");
		bexRoutesServices.addVertice("BRC");
		bexRoutesServices.addVertice("SCL");
		bexRoutesServices.addVertice("ORL");
		bexRoutesServices.addVertice("CDG");

		bexRoutesServices.addVertice("GRU");
		bexRoutesServices.addVertice("BRC");

		// verificacao
		Assertions.assertTrue(bexRoutesServices.getBexsVertices().size() == 5, () -> "Deveria ter " + bexRoutesServices.getBexsVertices().size() + " vertices");

	}

}
