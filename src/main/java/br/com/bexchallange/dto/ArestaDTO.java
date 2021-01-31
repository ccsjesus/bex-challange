package br.com.bexchallange.dto;

import com.opencsv.bean.CsvBindByPosition;

import br.com.bexchallange.entity.BexsAresta;
import br.com.bexchallange.entity.BexsVertice;
import br.com.bexchallange.entity.Routes;

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
	
	public BexsAresta toBexAresta(){
	    return new BexsAresta(new BexsVertice(origem), new BexsVertice(origem), valor);
	}
	
	
	
}
