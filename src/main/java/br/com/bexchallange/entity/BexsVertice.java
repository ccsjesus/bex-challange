package br.com.bexchallange.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BexsVertice implements Comparable<BexsVertice> {
	
	private String nome;
	
	private int distancia = 0;
	
	private BexsVertice inicio;
	
	private List<BexsAresta> arestasIncidentes = new ArrayList<BexsAresta>();
	
	private List<BexsVertice> verticesVizinhos = new ArrayList<BexsVertice>();
	
	private boolean visitado = false;
	

	public BexsVertice(String nome){
		this.nome = nome;
	}
	
	public List<BexsAresta> getArestasIncidentes() {
		return arestasIncidentes;
	}
	
	public void addArestasIncidentes(BexsAresta incidente) {
		this.arestasIncidentes.add(incidente);
		
		//adicionando vizinhos a lista
		if ( (incidente.getOrigem().getNome().equals(this.getNome())) &&
				(!this.isVizinho(incidente.getDestino())) ){
			
			this.addVerticeVizinhos(incidente.getDestino());
			
		}else if ( (incidente.getDestino().getNome().equals(this.getNome())) &&
				(!this.isVizinho(incidente.getOrigem())) ){
			
			this.addVerticeVizinhos(incidente.getOrigem());
		}
	}
	
	public void addVerticeVizinhos(BexsVertice vizinho) {
		this.verticesVizinhos.add(vizinho);
	}

	public List<BexsVertice> getVerticesVizinhos() {
		return verticesVizinhos;
	}
	
	public boolean isVizinho(BexsVertice vizinho){
		for (BexsVertice vert : this.getVerticesVizinhos()) {
			if (vert.getNome().equals(vizinho.getNome()))
				return true;
		}
		return false;
	}
	

	@Override
	public int compareTo(BexsVertice BexsVertice) {
		
        if(this.getDistancia() < BexsVertice.getDistancia()) 
        	return -1;
        else if(this.getDistancia() == BexsVertice.getDistancia()) 
        	return 0;

        return 1;  
	}

	@Override
	public String toString() {
		return this.getNome();
	}
	 
}
