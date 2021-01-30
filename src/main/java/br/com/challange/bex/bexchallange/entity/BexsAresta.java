package br.com.challange.bex.bexchallange.entity;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Data;

@Data
public class BexsAresta extends Routes implements Comparable<BexsAresta> {
	
    @CsvBindByPosition(position = 0)
	private BexsVertice origem;
	
    @CsvBindByPosition(position = 1)
    private BexsVertice destino;
	
    @CsvBindByPosition(position = 2)
	private int valor;
    
    private boolean visitado = false;

    
    public BexsAresta(BexsVertice origem, BexsVertice destino, int valor) {
		super();
		this.origem = origem;
		this.destino = destino;
		this.valor = valor;
	}
    
    
    @Override
	public int compareTo(BexsAresta vertice) {
		
        if(this.getValor() < vertice.getValor()) 
        	return -1;
        else if(this.getValor() == vertice.getValor()) 
        	return 0;

        return 1;  
	}

    @Override
	public String toString() {
		return this.getOrigem().getNome() + " - " + this.getDestino().getNome();
	}

	
}
