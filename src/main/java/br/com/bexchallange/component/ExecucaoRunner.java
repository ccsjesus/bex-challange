package br.com.bexchallange.component;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.com.bexchallange.service.BexRoutesServices;
import br.com.bexchallange.util.EntidadeResult;

@Component
public class ExecucaoRunner implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(ExecucaoRunner.class);
	
	@Autowired
	private BexRoutesServices bexRoutesServices;

    @Override
	public void run(String... args) {
    	while(true) {
    		System.out.print("please enter the route: ");
    		Scanner scanner = new Scanner(System.in);
    		String line = scanner.nextLine();
    		if("".equals(line)) {
    			System.out.println("please pass a route valid in the format ORG-DEST !" + "\n");
    		} else {
    			ResponseEntity<EntidadeResult> resultado = bexRoutesServices.consultarMelhorRota(line);
    			
    			System.out.println("best route: " +resultado.getBody().getResult().getDados());
    			
    		}
    	}
	}

}
