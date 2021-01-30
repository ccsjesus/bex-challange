package br.com.challange.bex.bexchallange.dto;

import com.opencsv.bean.CsvBindByPosition;

import br.com.challange.bex.bexchallange.entity.Routes;

public class ArestaDTO extends Routes {
	
	@CsvBindByPosition(position = 0)
	private String origem;
	
    @CsvBindByPosition(position = 1)
    private String destino;
	
    @CsvBindByPosition(position = 2)
	private int valor;

	public ArestaDTO(String origem, String destino, int valor) {
		this.origem = origem;
		this.destino = destino;
		this.valor = valor;
	}
	
	public ArestaDTO() {
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	
}
