package br.com.bexchallange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bexchallange.dto.ArestaDTO;
import br.com.bexchallange.service.BexRoutesServices;
import br.com.bexchallange.util.EntidadeResult;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/bexs/rota")
public class BexsController {

	@Autowired
	private BexRoutesServices bexRoutesServices;

	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Inclui uma nova rota "),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping("/")
	public ResponseEntity<EntidadeResult> incluirRota(@RequestBody ArestaDTO arestaDTO) {
		return bexRoutesServices.incluirRota(arestaDTO.toBexAresta());
	}

	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Consuta uma nova rota informando passando origem e destino no seguinte formato ORIG-DEST"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("melhor-rota/{caminho}")
	public ResponseEntity<EntidadeResult> consultaMelhorRota(@PathVariable(value = "caminho") String viagem) {
		return bexRoutesServices.consultarMelhorRota(viagem);
	}

}
