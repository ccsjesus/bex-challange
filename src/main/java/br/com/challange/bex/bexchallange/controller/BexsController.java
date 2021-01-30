package br.com.challange.bex.bexchallange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.challange.bex.bexchallange.dto.ArestaDTO;
import br.com.challange.bex.bexchallange.service.BexRoutesServices;
import br.com.challange.bex.bexchallange.util.EntidadeResult;

@RestController
@RequestMapping("api/bexs/rota")
public class BexsController {

	@Autowired
	private BexRoutesServices bexRoutesServices;

	@PostMapping("/")
	public ResponseEntity<EntidadeResult> incluirRota(@RequestBody ArestaDTO arestaDTO) {
		return bexRoutesServices.incluirRota(arestaDTO);
	}

	@GetMapping("melhor-rota/{caminho}")
	public ResponseEntity<EntidadeResult> consultaMelhorRota(@PathVariable(value = "caminho") String viagem) {
		return bexRoutesServices.consultarMelhorRota(viagem);
	}

}
