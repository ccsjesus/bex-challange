package br.com.bexchallange.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.bexchallange.component.RotasComponent;
import br.com.bexchallange.dto.ArestaDTO;
import br.com.bexchallange.entity.BexsAresta;
import br.com.bexchallange.entity.BexsVertice;
import br.com.bexchallange.util.EntidadeResult;
import br.com.bexchallange.util.ResultUtil;

@Service
public class BexRoutesServices {

	private List<BexsAresta> arestas = new ArrayList<BexsAresta>();

	private List<BexsVertice> vertices = new ArrayList<BexsVertice>();

	private boolean hasCycle = false;
	
	@Autowired
	private ResultUtil resultUtil;
	
	@Autowired
	private RotasComponent rotasComponent;

	public void loadFileRoutes() {

		try {

			List<ArestaDTO> beans = new CsvToBeanBuilder(rotasComponent.getReader()).withType(ArestaDTO.class).withSeparator(';')
					.build().parse();

			this.addVertice("GRU");
			this.addVertice("BRC");
			this.addVertice("SCL");
			this.addVertice("ORL");
			this.addVertice("CDG");
			this.addVertice("GRU");

			beans.forEach(c -> {
				this.addAresta(c.getValor(), c.getOrigem(), c.getDestino());
			});

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//printArvore();

		Map<Integer, List<BexsVertice>> mapVertices = this.melhorCaminho(new BexsVertice("BRC"),
				new BexsVertice("CDG"));
		/*mapVertices.entrySet().stream().forEach(e -> {
			e.getValue().forEach(c -> {
				System.out.print(c.getNome() + " ");
			});
			System.out.print("- " + e.getKey());
		});*/
	}

	public ResponseEntity<EntidadeResult> incluirRota(BexsAresta bexAresta) {
		
		this.addAresta(bexAresta.getValor(), bexAresta.getOrigem().getNome(), bexAresta.getDestino().getNome());
		
		this.inputAresta(bexAresta);
		
		this.addVertice(bexAresta.getOrigem().getNome());
		this.addVertice(bexAresta.getDestino().getNome());
		
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	public ResponseEntity<EntidadeResult> consultarMelhorRota(String viagem) {
		String origem = viagem.split("-")[0];
		String destino = viagem.split("-")[1];
		Map<Integer, List<BexsVertice>> mapVertices = this.melhorCaminho(new BexsVertice(origem),new BexsVertice(destino));
		
		Optional<Integer> key = mapVertices.entrySet().stream().map(map -> map.getKey()).findFirst();
		Optional<List<BexsVertice>> lista = mapVertices.entrySet().stream().map(map -> map.getValue()).findFirst();

		//printArvore();
		
		String str = "";
		for(BexsVertice bex : lista.get()) {
			str += " - " + bex.getNome();
		}
		str += " > " + key.get() + "";		
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, str.replaceFirst(" - ", ""));
	}

	public Map<Integer, List<BexsVertice>> melhorCaminho(BexsVertice vertice1, BexsVertice vertice2) {
		this.zerarVerticeVisitado();
		List<BexsVertice> melhorCaminho = new ArrayList<BexsVertice>();

		List<BexsVertice> naoVisitados = new ArrayList<BexsVertice>();

		melhorCaminho.add(vertice1);

		this.getBexsVertices().forEach(c -> {
			if (c.getNome().equals(vertice1.getNome())) {
				c.setDistancia(0);
			} else {
				c.setDistancia(999);
			}
			naoVisitados.add(c);
		});

		Collections.sort(naoVisitados);

		while (!naoVisitados.isEmpty()) {
			BexsVertice atual = naoVisitados.get(0);

			atual.getVerticesVizinhos().forEach(c -> {
				BexsVertice vizinho = c;
				if (!vizinho.isVisitado()) {

					BexsAresta ligacao2 = this.obterAresta(atual, vizinho);
					if (vizinho.getDistancia() > (atual.getDistancia() + ligacao2.getValor())) {
						vizinho.setDistancia(atual.getDistancia() + ligacao2.getValor());
						vizinho.setInicio(atual);

						BexsVertice verticeCaminho2;
						if (vizinho.toString().equals(vertice2.toString())) {
							melhorCaminho.clear();
							verticeCaminho2 = vizinho;
							melhorCaminho.add(vizinho);
							while (verticeCaminho2.getInicio() != null) {
								melhorCaminho.add(verticeCaminho2.getInicio());
								verticeCaminho2 = verticeCaminho2.getInicio();

							}
							Collections.sort(melhorCaminho);

						}
					}
				}
			});

			atual.setVisitado(true);
			naoVisitados.remove(atual);
			Collections.sort(naoVisitados);

		}
		this.cleanVerticesInicio();
		Integer max = melhorCaminho.stream().mapToInt(v -> v.getDistancia()).max()
				.orElseThrow(NoSuchElementException::new);
		Map<Integer, List<BexsVertice>> lista = new HashMap<Integer, List<BexsVertice>>();
		lista.put(max, melhorCaminho);
		return lista;
	}

	public List<BexsVertice> getBexsVertices() {
		return vertices;
	}

	public BexsAresta obterAresta(BexsVertice origem, BexsVertice destino) {

		return this.getBexArestas().stream()
				.filter(a -> (((a.getOrigem().getNome().equals(origem.getNome()))
						&& (a.getDestino().getNome().equals(destino.getNome())))
						|| ((a.getOrigem().getNome().equals(destino.getNome()))
								&& (a.getDestino().getNome().equals(origem.getNome())))))
				.findFirst().orElse(null);

	}

	public void cleanVerticesInicio() {
		this.getBexsVertices().stream().forEach(v -> v.setInicio(null));
	}

	public void addAresta(int peso, String origem, String destino) {
		int i, j, tamanho;

		i = this.addVertice(origem);
		j = this.addVertice(destino);

		BexsAresta a = new BexsAresta(this.getBexsVertices().get(i), this.getBexsVertices().get(j), peso);

		hasCiclo(a);
		this.getBexArestas().add(a);
		tamanho = this.getBexArestas().size();

		this.getBexsVertices().get(i).addArestasIncidentes(this.getBexArestas().get(tamanho - 1));
		this.getBexsVertices().get(j).addArestasIncidentes(this.getBexArestas().get(tamanho - 1));
	}

	public void setArestas(ArrayList<BexsAresta> arestas) {
		this.zerarListas();

		arestas.stream().forEach(c -> {
			this.addAresta(c.getValor(), c.getOrigem().getNome(), c.getDestino().getNome());
		});
	}

	public List<BexsAresta> getBexArestas() {
		return arestas;
	}

	public void zerarArestaVisitada() {
		this.getBexArestas().forEach(a -> a.setVisitado(false));
	}
	
	public void zerarVerticeVisitado() {
		this.getBexsVertices().forEach(v -> v.setVisitado(false));
	}

	public void setVertices(ArrayList<BexsVertice> vertices) {
		this.zerarListas();

		vertices.stream().forEach(v -> {

			if (this.obterPosicaoVertice(v.getNome()) == this.getBexsVertices().size()) {
				v.getArestasIncidentes().stream().forEach(i -> {

					if ((v.getNome().equals(i.getOrigem().getNome()))
							&& (this.obterPosicaoVertice(i.getDestino().getNome()) != this.getBexsVertices().size())) {

						this.addAresta(i.getValor(), i.getOrigem().getNome(), i.getDestino().getNome());

					} else if ((v.getNome().equals(i.getDestino().getNome()))
							&& (this.obterPosicaoVertice(i.getOrigem().getNome()) != this.getBexsVertices().size())) {

						this.addAresta(i.getValor(), i.getOrigem().getNome(), i.getDestino().getNome());

					}
				});
				this.addVertice(v.getNome());
			}
		});
	}

	public int addVertice(String nome) {
		int pos = this.obterPosicaoVertice(nome);

		if (pos == this.getBexsVertices().size()) {
			this.getBexsVertices().add(new BexsVertice(nome));
			return (this.getBexsVertices().size() - 1);
		}
		return pos;
	}

	public int obterPosicaoVertice(String nome) {
		
		return IntStream.range(0, this.getBexsVertices().size())
				.filter(i -> nome.equals(this.getBexsVertices().get(i).getNome()))
				.findFirst().orElse(this.getBexsVertices().size());

	}

	public boolean hasCiclo(BexsAresta aresta) {

		this.getBexArestas().stream().forEach(a -> {
			BexsVertice arestaAnterior = aresta.getDestino();

			if (aresta == a && !a.isVisitado()) {
				a.setVisitado(true);
			} else if (aresta != a) {
				if (arestaAnterior.getNome().equals(a.getOrigem().getNome())) {
					this.zerarArestaVisitada();
					this.hasCycle = true;
				} else {
					arestaAnterior = a.getDestino();
					a.setVisitado(true);
					this.hasCycle = false;

				}
			} else if (arestaAnterior.getNome().equals(a.getDestino().getNome())) {

				if (aresta.getOrigem().getNome().equals(a.getOrigem().getNome())) {
					this.zerarArestaVisitada();
					this.hasCycle = true;
				} else {
					arestaAnterior = a.getOrigem();
					a.setVisitado(true);
					this.hasCycle = false;
				}
			}
		});
		return hasCycle;
	}

	public void printArvore() {
		this.getBexArestas().forEach(a -> {
			System.out.print(a.getOrigem().getNome() + " - "
					+ a.getDestino().getNome() + " - "
					+ a.getValor() + "\n");
		});
	}

	public void zerarListas() {
		this.getBexArestas().clear();
		this.getBexsVertices().clear();
		this.setHasCycle(false);
	}

	public void setHasCycle(boolean hasCycle) {
		this.hasCycle = hasCycle;
	}
	
	public void inputAresta(BexsAresta aresta) {

        try (
        		
            CSVWriter csvWriter = new CSVWriter(rotasComponent.getWriter(),
                    ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        ) {

        	csvWriter.writeNext(new String[] {aresta.getOrigem().getNome(), aresta.getDestino().getNome(), aresta.getValor()+""});
    	    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
